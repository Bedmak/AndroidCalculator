package ru.bedmak.calculator;


import java.util.Random;

public class Operations {


    private int typeOperation = 0;
    private boolean flagDot = false;
    private boolean flagMinus = false;


    private final Random random = new Random();

    public String doOperation(String textValue, String smallTextValue) {

        int typeOperation = getTypeOperation();
        double value = Double.parseDouble(smallTextValue);

        if (typeOperation == 0) {
            return "";
        } else if (typeOperation == 1) {
            value += Double.parseDouble(textValue);
        } else if (typeOperation == 2) {
            value -= Double.parseDouble(textValue);
        } else if (typeOperation == 3) {
            value *= Double.parseDouble(textValue);
        } else if (typeOperation == 4) {
            if (textValue.equals("0")) {
                return "Error";
            }
            value /= Double.parseDouble(textValue);
        } else if (typeOperation == 5) {
            value = getYndDegree(smallTextValue, textValue);
        }
        flagMinus = value < 0;
        return checkForDot(value);
    }

    private String checkForDot(double value) {
        if (value % 1 == 0) {
            flagDot = false;
            return Long.toString((long) value);
        } else {
            flagDot = true;
            return Double.toString(value);
        }
    }

    public String getPercents(double value) { return checkForDot(value / 100); }

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

    public Double getYndDegree(String value, String y) {
        return Math.pow(Double.parseDouble(value), Integer.parseInt(y));
    }

    public String setDot(String text) {
        if(!flagDot) {
            flagDot = true;
            return text + ".";
        }
        return "";
    }

    public void setTypeOperation(int type) {
        typeOperation = type;
        flagDot = false;
        flagMinus = false;
    }

    public int getTypeOperation() {
        return typeOperation;
    }

    public void setFlagDot(boolean flag) {
        flagDot = flag;
    }

    public void setFlagMinus(boolean flag) {
        flagMinus = flag;
    }

    public String setMinusPlus(String text) {
        if (flagMinus) {
            flagMinus = false;
            return text.replaceFirst("-", "");
        } else {
            flagMinus = true;
            return "-" + text;
        }

    }




}
