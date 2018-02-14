package ru.bedmak.calculator.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.bedmak.calculator.ui.base.BaseFragment;
import ru.bedmak.calculator.utils.Operations;
import ru.bedmak.calculator.R;


public class NumPanelFragment extends BaseFragment {

    @BindViews({R.id.buttonAddition, R.id.buttonSubtraction, R.id.buttonMultiplication, R.id.buttonDivision})
    List<Button> operationButtons;

    @BindViews({R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9})
    List<Button> numButtons;

    private Operations operations;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_num_panel, container, false);;
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        operations = listener.getOperations();
        ButterKnife.apply(operationButtons, (ButterKnife.Action<Button>) (view1, index) ->
                view1.setOnClickListener(v -> setOperation(index + 1)));
        ButterKnife.apply(numButtons, (ButterKnife.Action<Button>) (view1, index) ->
                view1.setOnClickListener(v -> setNumber(Integer.toString(index))));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void setOperation(int type) {
        listener.showSmallResult(listener.getResult() + " " + operations.getOperation(type));
        listener.showResult("0");
        operations.setOperation(type);
    }

    private void setNumber(String num) {
        String text = "";
        if(!listener.getResult().equals("0")) {
            text = listener.getResult();
        }
        listener.showResult(text + num);
    }

    @OnClick(R.id.buttonAC)
    public void allClear() {
        listener.showResult("0");
        listener.showSmallResult("");
        operations.setMinus(false);
        operations.setDot(false);
    }

    @OnClick(R.id.buttonPlusMinus)
    public void setPlusMinus() {
        listener.showResult(operations.setMinusPlus(listener.getResult()));
    }

    @OnClick(R.id.buttonPercent)
    public void setPercent() {
        listener.showResult(operations.getPercents(Double.parseDouble(listener.getResult())));
    }

    @OnClick(R.id.buttonEquality)
    public void displayResult() {
        listener.showResult(operations.doOperation(listener.getResult(), listener.getSmallResult()));
        listener.showSmallResult("");
    }

    @OnClick(R.id.buttonDot)
    public void setDot() {
        listener.showResult(operations.setDisplayDot(listener.getResult()));
    }


}
