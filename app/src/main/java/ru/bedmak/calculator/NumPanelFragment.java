package ru.bedmak.calculator;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class NumPanelFragment extends Fragment implements View.OnClickListener {

    private float value = 0;
    private int typeOperation = 0;
    private boolean flagDot = false;
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
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_num_panel, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            value = savedInstanceState.getFloat("value");
            typeOperation = savedInstanceState.getInt("typeOperation");
            flagDot = savedInstanceState.getBoolean("flag");
        }
        initButtons(view);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.buttonAC) {
            listener.setResult("");
        } else if (view.getId() == R.id.buttonPlusMinus) {

        } else if (view.getId() == R.id.buttonPercent) {         //Сделать коректное деление на 100

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
        } else if(view.getId() == R.id.buttonDot){
            setFlagDot();
        } else if(view.getId() == R.id.button_0){
            listener.setResult("0");
        } else if(view.getId() == R.id.button_1){
            listener.setResult("1");
        } else if(view.getId() == R.id.button_2){
            listener.setResult("2");
        } else if(view.getId() == R.id.button_3){
            listener.setResult("3");
        } else if(view.getId() == R.id.button_4){
            listener.setResult("4");
        } else if(view.getId() == R.id.button_5){
            listener.setResult("5");
        } else if(view.getId() == R.id.button_6){
            listener.setResult("6");
        } else if(view.getId() == R.id.button_7){
            listener.setResult("7");
        } else if(view.getId() == R.id.button_8){
            listener.setResult("8");
        } else if(view.getId() == R.id.button_9) {
            listener.setResult("9");
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
        view.findViewById(R.id.buttonEquality).setOnClickListener(this);
    }

    protected void setTypeOperation(int type) {
        value = Float.parseFloat(listener.getResult());
        listener.setResult("");
        flagDot = false;
        typeOperation = type;
    }

    protected void doOperation() {

        String text = listener.getResult();
        if(typeOperation == 0) {
            return;
        }else if(typeOperation == 1) {
            value += Float.parseFloat(text);
        }else if(typeOperation == 2) {
            value -= Float.parseFloat(text);
        }else if(typeOperation == 3) {
            value *= Float.parseFloat(text);
        }else if(typeOperation == 4) {
            value /= Float.parseFloat(text);
        }
        if(value % 1 == 0) {
            listener.setResult(Integer.toString((int) value));
            flagDot = false;
        }else{
            listener.setResult(Float.toString(value));
            flagDot = true;
        }

    }

    protected void setFlagDot() {
        if(!flagDot) {
            if(listener.getResult().isEmpty()) {
                listener.setResult("0.");
            }else {
               listener.setResult(".");
            }
            flagDot = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putFloat("value", value);
        outState.putInt("typeOperation", typeOperation);
        outState.putBoolean("flag", flagDot);
    }
}
