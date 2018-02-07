package ru.bedmak.calculator.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.bedmak.calculator.ui.base.BaseFragment;
import ru.bedmak.calculator.utils.Operations;
import ru.bedmak.calculator.R;


public class NumPanelFragment extends BaseFragment {

    private Operations operations;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_num_panel, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initButtons(view);
        operations = listener.getOperations();
    }

    private void initButtons(View view) {
        view.findViewById(R.id.buttonAC).setOnClickListener
                (v -> {
                    listener.showResult("0");
                    listener.showSmallResult("");
                    operations.setMinus(false);
                    operations.setDot(false);
                });
        view.findViewById(R.id.buttonPlusMinus).setOnClickListener
                (v -> listener.showResult(operations.setMinusPlus(listener.getResult())));
        view.findViewById(R.id.buttonPercent).setOnClickListener
                (v -> listener.showResult(operations.getPercents(Double.parseDouble(listener.getResult()))));
        view.findViewById(R.id.buttonAddition).setOnClickListener
                (v -> setOperation(1));
        view.findViewById(R.id.buttonSubtraction).setOnClickListener
                (v -> setOperation(2));
        view.findViewById(R.id.buttonMultiplication).setOnClickListener
                (v -> setOperation(3));
        view.findViewById(R.id.buttonDivision).setOnClickListener
                (v -> setOperation(4));
        view.findViewById(R.id.buttonEquality).setOnClickListener
                (v -> displayResult());
        view.findViewById(R.id.buttonDot).setOnClickListener
                (v -> listener.showResult(operations.setDisplayDot(listener.getResult())));
        view.findViewById(R.id.button_0).setOnClickListener
                (v -> setNumber("0"));
        view.findViewById(R.id.button_1).setOnClickListener
                (v -> setNumber("1"));
        view.findViewById(R.id.button_2).setOnClickListener
                (v -> setNumber("2"));
        view.findViewById(R.id.button_3).setOnClickListener
                (v -> setNumber("3"));
        view.findViewById(R.id.button_4).setOnClickListener
                (v -> setNumber("4"));
        view.findViewById(R.id.button_5).setOnClickListener
                (v -> setNumber("5"));
        view.findViewById(R.id.button_6).setOnClickListener
                (v -> setNumber("6"));
        view.findViewById(R.id.button_7).setOnClickListener
                (v -> setNumber("7"));
        view.findViewById(R.id.button_8).setOnClickListener
                (v -> setNumber("8"));
        view.findViewById(R.id.button_9).setOnClickListener
                (v -> setNumber("9"));
    }

    private void setNumber(String num) {
        String text = "";
        if(!listener.getResult().equals("0")) {
            text = listener.getResult();
        }
        listener.showResult(text + num);
    }

    private void setOperation(int type) {
        listener.showSmallResult(listener.getResult() + " " + operations.getOperation(type));
        listener.showResult("0");
        operations.setOperation(type);
    }

    private void displayResult() {
        listener.showResult(operations.doOperation(listener.getResult(), listener.getSmallResult()));
        listener.showSmallResult("");
    }


}
