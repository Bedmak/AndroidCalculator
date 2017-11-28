package ru.bedmak.calculator.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.bedmak.calculator.MainViewListener;
import ru.bedmak.calculator.Operations;
import ru.bedmak.calculator.R;


public class NumPanelFragment extends Fragment implements View.OnClickListener {

    private MainViewListener listener;
    private Operations operations;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(getActivity() instanceof  MainViewListener)) {
            throw new AssertionError("MainActivity must implement MainViewListener");
        }
        listener = (MainViewListener) getActivity();

    }

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

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onClick(View view) {

        String text = "";
        if(!listener.getResult().equals("0")) {
            text = listener.getResult();
        }
        if (view.getId() == R.id.buttonAC) {
            listener.setResult("0");
            listener.setSmallResult("");
            operations.setMinus(false);
            operations.setDot(false);
        } else if (view.getId() == R.id.buttonPlusMinus) {
            listener.setResult(operations.setMinusPlus(listener.getResult()));
        } else if (view.getId() == R.id.buttonPercent) {
            listener.setResult(operations.getPercents(Double.parseDouble(listener.getResult())));
        } else if (view.getId() == R.id.buttonAddition) {
            setOperation(1);
        } else if (view.getId() == R.id.buttonSubtraction) {
            setOperation(2);
        } else if (view.getId() == R.id.buttonMultiplication) {
            setOperation(3);
        } else if (view.getId() == R.id.buttonDivision) {
            setOperation(4);
        } else if (view.getId() == R.id.buttonEquality) {
            displayResult();
        } else if(view.getId() == R.id.buttonDot) {
            listener.setResult(operations.setDisplayDot(listener.getResult()));
        } else if(view.getId() == R.id.button_0) {
            listener.setResult(text + "0");
        } else if(view.getId() == R.id.button_1) {
            listener.setResult(text +"1");
        } else if(view.getId() == R.id.button_2) {
            listener.setResult(text + "2");
        } else if(view.getId() == R.id.button_3) {
            listener.setResult(text + "3");
        } else if(view.getId() == R.id.button_4) {
            listener.setResult(text + "4");
        } else if(view.getId() == R.id.button_5) {
            listener.setResult(text + "5");
        } else if(view.getId() == R.id.button_6) {
            listener.setResult(text + "6");
        } else if(view.getId() == R.id.button_7) {
            listener.setResult(text + "7");
        } else if(view.getId() == R.id.button_8) {
            listener.setResult(text + "8");
        } else if(view.getId() == R.id.button_9) {
            listener.setResult(text + "9");
        }
    }

    private void initButtons(View view) {
        view.findViewById(R.id.button_0).setOnClickListener(this);
        view.findViewById(R.id.button_1).setOnClickListener(this);
        view.findViewById(R.id.button_2).setOnClickListener(this);
        view.findViewById(R.id.button_3).setOnClickListener(this);
        view.findViewById(R.id.button_4).setOnClickListener(this);
        view.findViewById(R.id.button_5).setOnClickListener(this);
        view.findViewById(R.id.button_6).setOnClickListener(this);
        view.findViewById(R.id.button_7).setOnClickListener(this);
        view.findViewById(R.id.button_8).setOnClickListener(this);
        view.findViewById(R.id.button_9).setOnClickListener(this);
        view.findViewById(R.id.buttonAC).setOnClickListener(this);
        view.findViewById(R.id.buttonPlusMinus).setOnClickListener(this);
        view.findViewById(R.id.buttonPercent).setOnClickListener(this);
        view.findViewById(R.id.buttonDivision).setOnClickListener(this);
        view.findViewById(R.id.buttonMultiplication).setOnClickListener(this);
        view.findViewById(R.id.buttonAddition).setOnClickListener(this);
        view.findViewById(R.id.buttonSubtraction).setOnClickListener(this);
        view.findViewById(R.id.buttonEquality).setOnClickListener(this);
        view.findViewById(R.id.buttonDot).setOnClickListener(this);
    }

    private void setOperation(int type) {
        listener.setSmallResult(listener.getResult());
        listener.setResult("0");
        operations.setOperation(type);
    }

    private void displayResult() {
        listener.setResult(operations.doOperation(listener.getResult(), listener.getSmallResult()));
        listener.setSmallResult("");
    }


}
