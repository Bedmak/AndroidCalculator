package ru.bedmak.calculator.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.bedmak.calculator.ui.base.BaseFragment;
import ru.bedmak.calculator.utils.Operations;
import ru.bedmak.calculator.R;


public class AdditionalPanelFragment extends BaseFragment {

    private Operations operations;
    private boolean flagRadDeg = false;

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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("flagRadDeg", flagRadDeg);
    }

    private void initAdditionalButtons(View view) {
        view.findViewById(R.id.buttonRand).setOnClickListener
                (v -> listener.setResult(operations.getRandomNumber()));
        view.findViewById(R.id.buttonPi).setOnClickListener
                (v -> listener.setResult(operations.getPiNumber()));
        view.findViewById(R.id.buttonE).setOnClickListener
                (v -> listener.setResult(operations.getENumber()));
        view.findViewById(R.id.buttonFact).setOnClickListener
                (v -> listener.setResult(Integer.toString(operations.getFactorial(Integer.parseInt(listener.getResult())))));
        view.findViewById(R.id.buttonSin).setOnClickListener
                (v -> listener.setResult(operations.getSin(listener.getResult(), flagRadDeg)));
        view.findViewById(R.id.buttonSinh).setOnClickListener
                (v -> listener.setResult(operations.getSinh(listener.getResult())));
        view.findViewById(R.id.buttonCos).setOnClickListener
                (v -> listener.setResult(operations.getCos(listener.getResult(), flagRadDeg)));
        view.findViewById(R.id.buttonCosh).setOnClickListener
                (v -> listener.setResult(operations.getCosh(listener.getResult())));
        view.findViewById(R.id.buttonTan).setOnClickListener
                (v -> listener.setResult(operations.getTan(listener.getResult(), flagRadDeg)));
        view.findViewById(R.id.buttonTanh).setOnClickListener
                (v -> listener.setResult(operations.getTanh(listener.getResult())));
        view.findViewById(R.id.buttonRadDeg).setOnClickListener(this::changeRadForDeg);
        view.findViewById(R.id.buttonLn).setOnClickListener
                (v -> listener.setResult(operations.getLn(listener.getResult())));
        view.findViewById(R.id.buttonLog).setOnClickListener
                (v -> listener.setResult(operations.getLog(listener.getResult())));

        view.findViewById(R.id.button_2nd_degree).setOnClickListener
                (v -> listener.setResult(operations.get2ndDegree(listener.getResult())));
        ((Button) view.findViewById(R.id.button_2nd_degree)).setText(fromHtml("x<sup>2</sup>"));

        view.findViewById(R.id.button_3nd_degree).setOnClickListener
                (v -> listener.setResult(operations.get3ndDegree(listener.getResult())));
        ((Button) view.findViewById(R.id.button_3nd_degree)).setText(fromHtml("x<sup>3</sup>"));

        view.findViewById(R.id.button_ynd_degree)
                .setOnClickListener(v -> {
                    listener.setSmallResult(listener.getResult() + " " + operations.getOperation(5));
                    listener.setResult("0");
                    operations.setOperation(5);
                });
        ((Button) view.findViewById(R.id.button_ynd_degree)).setText(fromHtml("x<sup>y</sup>"));

        view.findViewById(R.id.buttonSQRT).setOnClickListener
                (v -> listener.setResult(operations.getSQRT(listener.getResult())));
        view.findViewById(R.id.button_memory_clean).setOnClickListener
                (v -> operations.cleanMemory());
        view.findViewById(R.id.button_memory_sum).setOnClickListener
                (v -> operations.sumMemory(listener.getResult()));
        view.findViewById(R.id.button_memory_sub).setOnClickListener
                (v -> operations.subMemory(listener.getResult()));
        view.findViewById(R.id.button_memory_display).setOnClickListener
                (v -> listener.setResult(operations.displayMemory()));
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
