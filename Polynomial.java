import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Polynomial {
    double [] co;
    int [] ex;
    public Polynomial() {
        co = null;
        ex = null;
    }
    public Polynomial(double[] arr, int[] exp) {
        co = new double[arr.length];
        for (int i = 0; i < arr.length; i++) {
            co[i] = arr[i];
	    }
        ex = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ex[i] = exp[i];
	    }
    }
    public Polynomial(File file) {
        try {
            Scanner reader = new Scanner(file);
            String str = reader.nextLine();
            String[] terms = str.split("([+]|(?=-))");
            co = new double[terms.length];
            ex = new int[terms.length];
            for (int i = 0; i < terms.length; i++) {
                String term = terms[i];
                if (term.indexOf("x") == -1) {
                    co[i] = Double.parseDouble(term);
                    ex[i] = 0;
                } else {
                    String[] parts = term.split("x");           
                    if (parts.length == 0) {
                        co[i] = 1;
                        ex[i] = 1;
                    } 
                    else if (term.startsWith("x")) {
                        co[i] = 1; 
                        ex[i] = Integer.parseInt(parts[1]);
                    }
                    else if (term.endsWith("x")) {
                        if (parts[0].equals("-")){
                            co[i] = -1;
                        } else {
                            co[i] = Double.parseDouble(parts[0]);
                        }
                        ex[i] = 1;
                    } else {                        
                        if (parts[0].equals("-")) {
                            co[i] = -1;
                        } else {
                            co[i] = Double.parseDouble(parts[0]);
                        }
                        ex[i] = Integer.parseInt(parts[1]);
                    }
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Error reading files");
        }
    }
    public void saveToFile(String filename) {    
        try {
            FileWriter writer = new FileWriter(filename);
            if (co == null || ex == null) {
                writer.write("0");
            } else {
                for (int i = 0; i < co.length; i++) {
                    if (co[i] >= 0 && i != 0) {
                        writer.write("+");
                    }
                    writer.write(co[i]+"x"+ex[i]);
                }
            }
            writer.close();
        } catch (Exception e) {
            System.err.println("Error writing files");
        }
    }
    private static int index (int x, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (x == arr[i]) return i;
        }
        return -1;
    }
    private void remove_zero() {
        int l = co.length;
        for (int i = 0; i < co.length; i++) {
            if (co[i] == 0) {
                l -= 1;
            }
        }
        double[] temp_co = new double[l];
        int[] temp_ex = new int[l];  
        int k = 0;  
        for (int i = 0; i < co.length; i++) {
            if (co[i] != 0) {
                temp_co[k] = co[i];
                temp_ex[k] = ex[i];
                k++;
            }
        }   
        co = temp_co;
        ex = temp_ex;
    }
    public Polynomial add(Polynomial x) {
        if (x.co == null) {
            return new Polynomial(co,ex);
        }
        if (co == null) {
            return new Polynomial(x.co,x.ex);
        }
        int l1 = ex.length;
        int l2 = x.ex.length;
	    int max_l = l1+l2; 
        double [] new_co = new double[max_l];
        int [] new_ex = new int[max_l];
        for (int i = 0; i < l1; i++) {
            new_co[i] = co[i];
            new_ex[i] = ex[i];
        }
        for (int i = 0; i < l2; i++) {
            int index = index(x.ex[i], new_ex);
            if (index != -1) {
                new_co[index] += x.co[i];
            } else {
                new_co[l1+i] = x.co[i];
                new_ex[l1+i] = x.ex[i];
            }
        }
        Polynomial newp = new Polynomial(new_co, new_ex);
        newp.remove_zero();
        return newp;
    }
    public Polynomial multiply(Polynomial x) {
        if (x.co == null || co == null) {
            return new Polynomial();
        }
        int l1 = ex.length;
        int l2 = x.ex.length;
	    int max_l = l1*l2; 
        double [] new_co = new double[max_l];
        int [] new_ex = new int[max_l];
        int k=0;
        for (int i = 0; i < l1; i++) {
            for (int j = 0; j < l2; j++) {
                int index = index(ex[i]+x.ex[j],new_ex);
                if (index != -1) {
                    new_co[index] += co[i] * x.co[j];
                } else {
                    new_ex[k] = ex[i] + x.ex[j];
                    new_co[k] = co[i] * x.co[j];
                    k++;
                }
            }
        }
        Polynomial newp = new Polynomial(new_co, new_ex);
        newp.remove_zero();
        return newp;      
    }
    public double evaluate(double x) {
        if (co == null) return 0;
        double total = 0;
        for (int i = 0; i < co.length; i++) {
            total += co[i]*Math.pow(x, ex[i]);
        }
        return total;
    }
    public Boolean hasRoot(double root) {
        return (evaluate(root) == 0);
    }
}