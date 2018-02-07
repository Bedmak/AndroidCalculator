package ru.bedmak.calculator.ui;


import ru.bedmak.calculator.utils.Operations;

public interface MvpView {

    void showResult(String text);
    String getResult();

    void showSmallResult(String text);
    String getSmallResult();

    Operations getOperations();
}
