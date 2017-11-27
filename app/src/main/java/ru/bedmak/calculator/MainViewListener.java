package ru.bedmak.calculator;


public interface MainViewListener {

    void setResult(String text);
    String getResult();

    void setSmallResult(String text);
    String getSmallResult();
}
