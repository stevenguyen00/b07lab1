import java.io.File;

public class Driver {
    public static void main(String [] args) {
        Polynomial p = new Polynomial();
        double [] c1 = {6,5};
        int [] e1 = {0,3};
        Polynomial p1 = new Polynomial(c1,e1);
        double [] c2 = {-2,-9};
        int [] e2 = {1,4};
        Polynomial p2 = new Polynomial(c2,e2);
        Polynomial s = p1.add(p2);
        System.out.println("s(0.1) = " + s.evaluate(0.1));
        if(s.hasRoot(1))
            System.out.println("1 is a root of s");
        else
            System.out.println("1 is not a root of s");
        Polynomial s2 = p1.multiply(p2);
        System.out.println("s2(1) = " + s2.evaluate(1));
        System.out.println("s2 have " + s2.co.length+ " terms");
        Polynomial s3 = p1.multiply(p);
        if(s3.hasRoot(23.446))
            System.out.println("23.446 is a root of s3");
        else
            System.out.println("23.446 is not a root of s3");
        Polynomial s4 = p1.add(p);
        System.out.println("s4(0) - p1(0) = " + (s4.evaluate(0.234) - p1.evaluate(0.234)));
        double [] c4 = {2,3};
        int [] e4 = {1,3};
        Polynomial p4 = new Polynomial(c4,e4);
        double [] c5 = {-2,4};
        int [] e5 = {1,3};
        Polynomial p5 = new Polynomial(c5,e5);
        Polynomial s5 = p4.add(p5); 
        System.out.println("s5 have " +s5.co.length+ " terms");
        System.out.println("s5(1) = " + s5.evaluate(1));
        File file = new File("text.txt");
        Polynomial p3 = new Polynomial(file);
        System.out.println("p3(2) = " + p3.evaluate(2));
        File output = new File("output.txt");
        p.saveToFile(output.getName());
    }
}
