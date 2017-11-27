package ru.bedmak.calculator.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.bedmak.calculator.MainViewListener;
import ru.bedmak.calculator.R;


public class NumPanelFragment extends Fragment implements View.OnClickListener {

    private double value = 0;
    private int typeOperation = 0;
    private boolean flagDot = false;
    private boolean flagMinus = false;
    private MainViewListener listener;

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
        if (savedInstanceState != null) {
            value = savedInstanceState.getDouble("value");
            typeOperation = savedInstanceState.getInt("typeOperation");
            flagDot = savedInstanceState.getBoolean("flagDot");
            flagMinus = savedInstanceState.getBoolean("flagMinus");
        }
        initButtons(view);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putDouble("value", value);
        outState.putInt("typeOperation", typeOperation);
        outState.putBoolean("flagDot", flagDot);
        outState.putBoolean("flagMinus", flagMinus);
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
            value = 0;
            flagMinus = false;
            flagDot = false;
        } else if (view.getId() == R.id.buttonPlusMinus) {
            setMinusPlus();
        } else if (view.getId() == R.id.buttonPercent) {
            value = Double.parseDouble(listener.getResult()) / 100;
            checkForDot();
        } else if (view.getId() == R.id.buttonAddition) {
            setTypeOperation(1);
        } else if (view.getId() == R.id.buttonSubtraction) {
            setTypeOperation(2);
        } else if (view.getId() == R.id.buttonMultiplication) {
            setTypeOperation(3);
        } else if (view.getId() == R.id.buttonDivision) {
            setTypeOperation(4);
        } else if (view.getId() == R.id.buttonEquality) {
            doOperation();
        } else if(view.getId() == R.id.buttonDot) {
            setDot();
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

    protected void initButtons(View view) {
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

    protected void setTypeOperation(int type) {
        value = Double.parseDouble(listener.getResult());
        listener.setSmallResult(listener.getResult());
        listener.setResult("0");
        flagDot = false;
        flagMinus = false;
        typeOperation = type;
    }

    protected void doOperation() {

        String text = listener.getResult();
        if (typeOperation == 0) {
            return;
        } else if (typeOperation == 1) {
            value += Double.parseDouble(text);
        } else if (typeOperation == 2) {
            value -= Double.parseDouble(text);
        } else if (typeOperation == 3) {
            value *= Double.parseDouble(text);
        } else if (typeOperation == 4) {
            if (text.equals("0")) {
                listener.setResult("Error");
                return;
            }
            value /= Double.parseDouble(text);
        }
        flagMinus = value < 0;
        listener.setResult(Double.toString(checkForDot()));
        listener.setSmallResult("");
    }

    protected Double checkForDot() {
        if (value % 1 == 0) {
            value = (long) value;
            flagDot = false;
        } else {
            flagDot = true;
        }
        return value;
    }

    protected void setDot() {
        if(!flagDot) {
            listener.setResult(listener.getResult() + ".");
            flagDot = true;
        }
    }

    protected void setMinusPlus() {
        if (flagMinus) {
            listener.setResult(listener.getResult().replaceFirst("-", ""));
        } else {
            listener.setResult("-" + listener.getResult());
        }
        flagMinus = !flagMinus;
    }
}
