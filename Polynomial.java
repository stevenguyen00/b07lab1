public class Polynomial {
    double [] coefficients;
    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0;
    }
    public Polynomial(double[] arr) {
        coefficients = arr;
    }
    public Polynomial add(Polynomial x) {
        int arr_len = Math.max(coefficients.length, x.coefficients.length);
        double [] new_coefficients = new double[arr_len];
        for (int i = 0; i < arr_len; i++) {
            if (i < Math.min(coefficients.length,x.coefficients.length)) {
                new_coefficients[i] = coefficients[i] + x.coefficients[i];
            }
            else if (coefficients.length < x.coefficients.length) {
                new_coefficients[i] = x.coefficients[i];
            }
            else {
                new_coefficients[i] = coefficients[i];
            }
        }
        return new Polynomial(new_coefficients);
    }
    public double evaluate(double x) {
        double total = 0;
        for (int i = 0; i < coefficients.length; i++) {
            total += coefficients[i]*Math.pow(x, i);
        }
        return total;
    }
    public Boolean hasRoot(double root) {
        return (evaluate(root) == 0);
    }
}