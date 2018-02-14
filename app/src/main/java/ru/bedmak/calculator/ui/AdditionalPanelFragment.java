package ru.bedmak.calculator.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import ru.bedmak.calculator.ui.base.BaseFragment;
import ru.bedmak.calculator.utils.Operations;
import ru.bedmak.calculator.R;


public class AdditionalPanelFragment extends BaseFragment {

    @BindView(R.id.button_2nd_degree)
    Button button2ndDegree;

    @BindView(R.id.button_3nd_degree)
    Button button3ndDegree;

    @BindView(R.id.button_ynd_degree)
    Button buttonyndDegree;

    private Unbinder unbinder;
    private Operations operations;
    private boolean flagRadDeg = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_additional_panel, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
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
        initDegreeButtons();
        operations = listener.getOperations();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("flagRadDeg", flagRadDeg);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.buttonRand)
    public void setRandomNumber() {
        listener.showResult(operations.getRandomNumber());
    }

    @OnClick(R.id.buttonPi)
    public void setPiNumber() {
        listener.showResult(operations.getPiNumber());
    }

    @OnClick(R.id.buttonE)
    public void setENumber() {
        listener.showResult(operations.getENumber());
    }

    @OnClick(R.id.buttonFact)
    public void setFact() {
        listener.showResult(Integer.toString(operations.getFactorial(Integer.parseInt(listener.getResult()))));
    }

    @OnClick(R.id.buttonSin)
    public void setSin() {
        listener.showResult(operations.getSin(listener.getResult(), flagRadDeg));
    }

    @OnClick(R.id.buttonSinh)
    public void setSinh() {
        listener.showResult(operations.getSinh(listener.getResult()));
    }

    @OnClick(R.id.buttonCos)
    public void setCos() {
        listener.showResult(operations.getCos(listener.getResult(), flagRadDeg));
    }

    @OnClick(R.id.buttonCosh)
    public void setCosh() {
        listener.showResult(operations.getCosh(listener.getResult()));
    }

    @OnClick(R.id.buttonTan)
    public void setTan() {
        listener.showResult(operations.getTan(listener.getResult(), flagRadDeg));
    }

    @OnClick(R.id.buttonTanh)
    public void setTanh() {
        listener.showResult(operations.getTanh(listener.getResult()));
    }

    @OnClick(R.id.buttonRadDeg)
    public void changeRadDeg(Button button) {
        if (flagRadDeg) {
            button.setText(R.string.button_rad);
        } else {
            button.setText(R.string.button_deg);
        }
        flagRadDeg = !flagRadDeg;
    }

    @OnClick(R.id.buttonLn)
    public void setLn() {
        listener.showResult(operations.getLn(listener.getResult()));
    }

    @OnClick(R.id.buttonLog)
    public void setLog() {
        listener.showResult(operations.getLog(listener.getResult()));
    }

    @OnClick(R.id.buttonSQRT)
    public void setSQRT() {
        listener.showResult(operations.getSQRT(listener.getResult()));
    }

    @OnClick(R.id.button_memory_clean)
    public void cleanMemory() {
        operations.cleanMemory();
    }

    @OnClick(R.id.button_memory_sum)
    public void sumMemory() {
        operations.sumMemory(listener.getResult());
    }

    @OnClick(R.id.button_memory_sub)
    public void subMemory() {
        operations.subMemory(listener.getResult());
    }

    @OnClick(R.id.button_memory_display)
    public void displayMemory() {
        listener.showResult(operations.displayMemory());
    }

    private void initDegreeButtons() {

        button2ndDegree.setText(fromHtml("x<sup>2</sup>"));
        button2ndDegree.setOnClickListener(v ->
                listener.showResult(operations.get2ndDegree(listener.getResult())));

        button3ndDegree.setText(fromHtml("x<sup>3</sup>"));
        button3ndDegree.setOnClickListener(v ->
                listener.showResult(operations.get3ndDegree(listener.getResult())));

        buttonyndDegree.setText(fromHtml("x<sup>y</sup>"));
        buttonyndDegree.setOnClickListener(v -> {
            listener.showSmallResult(listener.getResult() + " " + operations.getOperation(5));
            listener.showResult("0");
            operations.setOperation(5);
        });
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


}
