package ru.bedmak.calculator;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Random;


public class AdditionalPanelFragment extends Fragment implements View.OnClickListener {

    private MainViewListener listener;
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
        initAdditionalButtons(view);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
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
        }
    }

    protected void initAdditionalButtons(View view) {
        view.findViewById(R.id.buttonRand).setOnClickListener(this);
        view.findViewById(R.id.buttonPi).setOnClickListener(this);
        view.findViewById(R.id.buttonE).setOnClickListener(this);
        view.findViewById(R.id.buttonFact).setOnClickListener(this);
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

}
