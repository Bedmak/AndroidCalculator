package ru.bedmak.calculator.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.bedmak.calculator.MainViewListener;
import ru.bedmak.calculator.Operations;
import ru.bedmak.calculator.R;


public class AdditionalPanelFragment extends Fragment implements View.OnClickListener {

    private MainViewListener listener;
    private Operations operations;
    private boolean flagRadDeg = false;

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
        operations = listener.getOperations();
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
            listener.setResult(operations.getRandomNumber());
        } else if (view.getId() == R.id.buttonPi) {
            listener.setResult(operations.getPiNumber());
        } else if (view.getId() == R.id.buttonE) {
            listener.setResult(operations.getENumber());
        } else if (view.getId() == R.id.buttonFact) {
            listener.setResult(Integer.toString(operations.getFactorial(Integer.parseInt(listener.getResult()))));
        } else if (view.getId() == R.id.buttonSin) {
            listener.setResult(operations.getSin(listener.getResult(), flagRadDeg));
        } else if (view.getId() == R.id.buttonSinh) {
            listener.setResult(operations.getSinh(listener.getResult()));
        } else if (view.getId() == R.id.buttonCos) {
            listener.setResult(operations.getCos(listener.getResult(), flagRadDeg));
        } else if (view.getId() == R.id.buttonCosh) {
            listener.setResult(operations.getCosh(listener.getResult()));
        } else if (view.getId() == R.id.buttonTan) {
            listener.setResult(operations.getTan(listener.getResult(), flagRadDeg));
        } else if (view.getId() == R.id.buttonTanh) {
            listener.setResult(operations.getTanh(listener.getResult()));
        } else if (view.getId() == R.id.buttonRadDeg) {
            changeRadForDeg(view);
        } else if (view.getId() == R.id.buttonLn) {
            listener.setResult(operations.getLn(listener.getResult()));
        } else if (view.getId() == R.id.buttonLog){
            listener.setResult(operations.getLog(listener.getResult()));
        } else if (view.getId() == R.id.button_2nd_degree) {
            listener.setResult(operations.get2ndDegree(listener.getResult()));
        } else if (view.getId() == R.id.button_3nd_degree) {
            listener.setResult(operations.get3ndDegree(listener.getResult()));
        } else if (view.getId() == R.id.button_ynd_degree) {
            listener.setSmallResult(listener.getResult());
            listener.setResult("0");
            operations.setOperation(5);
        }
    }

    private void initAdditionalButtons(View view) {
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
        view.findViewById(R.id.buttonLog).setOnClickListener(this);
        initButton(view, R.id.button_2nd_degree, "x<sup>2</sup>");
        initButton(view, R.id.button_3nd_degree, "x<sup>3</sup>");
        initButton(view, R.id.button_ynd_degree, "x<sup>y</sup>");
    }

    private void initButton(View view, int id, String html) {
        Button button = view.findViewById(id);
        button.setOnClickListener(this);
        button.setText(fromHtml(html));
    }

    @SuppressWarnings("deprecation")
    private static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    private void changeRadForDeg(View view) {
        Button RadDegButton =  view.findViewById(R.id.buttonRadDeg);
        if (flagRadDeg) {
            RadDegButton.setText(R.string.button_rad);
        } else {
            RadDegButton.setText(R.string.button_deg);
        }
        flagRadDeg = !flagRadDeg;
    }

}
