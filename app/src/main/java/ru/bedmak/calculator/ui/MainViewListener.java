package ru.bedmak.calculator.ui;


import ru.bedmak.calculator.utils.Operations;

public interface MainViewListener {

    void setResult(String text);
    String getResult();

    void setSmallResult(String text);
    String getSmallResult();

    Operations getOperations();
}
