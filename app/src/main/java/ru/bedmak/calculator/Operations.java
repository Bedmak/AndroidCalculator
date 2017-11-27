package ru.bedmak.calculator;


import java.util.Random;

public class Operations {

    private final Random random = new Random();

    public String getRandomNumber() {
        return Double.toString(random.nextDouble());
    }

    public String getPiNumber() {
        return Double.toString(Math.PI);
    }

    public String getENumber() {
        return Double.toString(Math.E);
    }

    public int getFactorial(int x) {
        if (x == 1) {
            return 1;
        } else {
            return x * getFactorial(x - 1);
        }
    }

    public String getSin(String value, boolean flag) {
        if (flag) {
            return Double.toString(Math.sin(Double.parseDouble(value)));
        } else {
            return Double.toString(Math.sin(Math.toRadians(Double.parseDouble(value))));
        }
    }

    public String getCos(String value, boolean flag) {
        if (flag) {
            return Double.toString(Math.cos(Double.parseDouble(value)));
        } else {
            return Double.toString(Math.cos(Math.toRadians(Double.parseDouble(value))));
        }
    }

    public String getTan(String value, boolean flag) {
        if (flag) {
            return Double.toString(Math.tan(Double.parseDouble(value)));
        } else {
            return Double.toString(Math.cos(Math.toRadians(Double.parseDouble(value))));
        }
    }

    public String getSinh(String value) { return Double.toString(Math.sinh(Double.parseDouble(value))); }

    public String getCosh(String value) { return Double.toString(Math.cosh(Double.parseDouble(value))); }

    public String getTanh(String value) { return Double.toString(Math.tanh(Double.parseDouble(value))); }



    public String getLn(String value) { return Double.toString(Math.log(Double.parseDouble(value))); }

    public String getLog(String value) { return  Double.toString(Math.log10(Double.parseDouble(value)));}

    public String get2ndDegree(String value) {
        return  Double.toString(Math.pow(Double.parseDouble(value), 2));
    }

    public String get3ndDegree(String value) {
        return  Double.toString(Math.pow(Double.parseDouble(value), 3));
    }

    public String getYndDegree(String value) {
        return  "null";
    }


}
