package ru.bedmak.calculator;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;


public class AdditionalPanelFragment extends Fragment implements View.OnClickListener {

    private MainViewListener listener;
    private boolean flagRadDeg = false;
    private final Random random = new Random();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(!(getActivity() instanceof  MainViewListener)) {
            throw new AssertionError("MainActivity must implement MainViewListener");
        }
        listener = (MainViewListener) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_additional_panel, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null)  {
            flagRadDeg = savedInstanceState.getBoolean("flagRadDeg");
            if (flagRadDeg) {
                ((Button) view.findViewById(R.id.buttonRadDeg)).setText(R.string.button_deg);
            }
        }
        initAdditionalButtons(view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("flagRadDeg", flagRadDeg);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonRand) {
            listener.setResult(getRandomNumber());
        } else if (view.getId() == R.id.buttonPi) {
            listener.setResult(getPiNumber());
        } else if (view.getId() == R.id.buttonE) {
            listener.setResult(getENumber());
        } else if (view.getId() == R.id.buttonFact) {
            listener.setResult(Integer.toString(getFactorial(Integer.parseInt(listener.getResult()))));
        } else if (view.getId() == R.id.buttonSin) {
            listener.setResult(getSin());
        } else if (view.getId() == R.id.buttonSinh) {
            listener.setResult(getSinh());
        } else if (view.getId() == R.id.buttonCos) {
            listener.setResult(getCos());
        } else if (view.getId() == R.id.buttonCosh) {
            listener.setResult(getCosh());
        } else if (view.getId() == R.id.buttonTan) {
            listener.setResult(getTan());
        } else if (view.getId() == R.id.buttonTanh) {
            listener.setResult(getTanh());
        } else if (view.getId() == R.id.buttonRadDeg) {
            changeRadForDeg(view);
        } else if (view.getId() == R.id.buttonLn) {
            listener.setResult(getLn());
        }
    }

    protected void initAdditionalButtons(View view) {
        view.findViewById(R.id.buttonRand).setOnClickListener(this);
        view.findViewById(R.id.buttonPi).setOnClickListener(this);
        view.findViewById(R.id.buttonE).setOnClickListener(this);
        view.findViewById(R.id.buttonFact).setOnClickListener(this);
        view.findViewById(R.id.buttonSin).setOnClickListener(this);
        view.findViewById(R.id.buttonSinh).setOnClickListener(this);
        view.findViewById(R.id.buttonCos).setOnClickListener(this);
        view.findViewById(R.id.buttonCosh).setOnClickListener(this);
        view.findViewById(R.id.buttonTan).setOnClickListener(this);
        view.findViewById(R.id.buttonTanh).setOnClickListener(this);
        view.findViewById(R.id.buttonRadDeg).setOnClickListener(this);
        view.findViewById(R.id.buttonLn).setOnClickListener(this);
    }

    protected String getRandomNumber() {
        return Double.toString(random.nextDouble());
    }

    protected String getPiNumber() {
        return Double.toString(Math.PI);
    }

    protected String getENumber() {
        return Double.toString(Math.E);
    }

    protected int getFactorial(int x) {
        if (x == 1) {
            return 1;
        } else {
            return x * getFactorial(x - 1);
        }
    }

    protected String getSin() {
        if (flagRadDeg) {
            return Double.toString(Math.sin(Double.parseDouble(listener.getResult())));
        } else {
            return Double.toString(Math.sin(Math.toRadians(Double.parseDouble(listener.getResult()))));
        }
    }

    protected String getCos() {
        if (flagRadDeg) {
            return Double.toString(Math.cos(Double.parseDouble(listener.getResult())));
        } else {
            return Double.toString(Math.cos(Math.toRadians(Double.parseDouble(listener.getResult()))));
        }
    }

    protected String getTan() {
        if (flagRadDeg) {
            return Double.toString(Math.tan(Double.parseDouble(listener.getResult())));
        } else {
            return Double.toString(Math.cos(Math.toRadians(Double.parseDouble(listener.getResult()))));
        }
    }

    protected String getSinh() { return Double.toString(Math.sinh(Double.parseDouble(listener.getResult()))); }

    protected String getCosh() { return Double.toString(Math.cosh(Double.parseDouble(listener.getResult()))); }

    protected String getTanh() { return Double.toString(Math.tanh(Double.parseDouble(listener.getResult()))); }

    protected void changeRadForDeg(View view) {
        Button RadDegButton =  view.findViewById(R.id.buttonRadDeg);
        if (flagRadDeg) {
            RadDegButton.setText(R.string.button_rad);
        } else {
            RadDegButton.setText(R.string.button_deg);
        }
        flagRadDeg = !flagRadDeg;
    }

    protected String getLn() { return Double.toString(Math.log(Double.parseDouble(listener.getResult()))); }

}
