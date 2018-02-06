package ru.bedmak.calculator.utils;

import java.util.Random;

public class Operations {

    private int typeOperation = 0;
    private boolean isDot = false;
    private boolean isMinus = false;
    private double memory = 0;

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
        isMinus = value < 0;
        return checkForDot(value);
    }

    public String getOperation(int type) {
        String operator = "";
        switch (type) {
            case 1:
                operator = "+";
                break;
            case 2:
                operator = "-";
                break;
            case 3:
                operator = "*";
                break;
            case 4:
                operator = "/";
                break;
            case 5:
                operator = "^";
                break;
        }
        return operator;
    }

    private String checkForDot(double value) {
        if (value % 1 == 0) {
            isDot = false;
            return Long.toString((long) value);
        } else {
            isDot = true;
            return Double.toString(value);
        }
    }

    public void setOperation(int type) {
        typeOperation = type;
        isDot = false;
        isMinus = false;
    }

    public void setTypeOperation(int type) {
        typeOperation = type;
    }

    public int getTypeOperation() {
        return typeOperation;
    }

    public void setMinus(boolean flag) {
        isMinus = flag;
    }

    public boolean getMinus() {
        return isMinus;
    }

    public String setMinusPlus(String text) {
        if (getMinus()) {
            setMinus(false);
            return text.replaceFirst("-", "");
        } else {
            setMinus(true);
            return "-" + text;
        }
    }

    public void setDot(boolean flag) {
        isDot = flag;
    }

    public boolean getDot() {
        return isDot;
    }

    public String setDisplayDot(String text) {
        if(!isDot) {
            setDot(true);
            return text + ".";
        }
        return text;
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

    public String getLog(String value) { return Double.toString(Math.log10(Double.parseDouble(value))); }

    public String get2ndDegree(String value) {
        return  checkForDot(Math.pow(Double.parseDouble(value), 2));
    }

    public String get3ndDegree(String value) {
        return  checkForDot(Math.pow(Double.parseDouble(value), 3));
    }

    public Double getYndDegree(String value, String y) {
        return Math.pow(Double.parseDouble(value), Integer.parseInt(y));
    }

    public String getSQRT(String value) {
        return checkForDot(Math.sqrt(Double.parseDouble(value)));
    }

    public void sumMemory(String value) {
        memory += Double.parseDouble(value);
    }

    public void subMemory(String value) {
        memory -= Double.parseDouble(value);
    }

    public String displayMemory() {
        return checkForDot(memory);
    }

    public void cleanMemory() {
        memory = 0;
    }

    public double getMemory() {
        return memory;
    }

    public void setMemory(double value) {
        memory = value;
    }
}
