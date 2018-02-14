package ru.bedmak.calculator.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.bedmak.calculator.utils.Operations;
import ru.bedmak.calculator.R;


public class MainActivity extends AppCompatActivity implements MvpView {

    @BindView(R.id.textView)
    TextView textView;

    @BindView(R.id.textViewSmall)
    TextView textViewSmall;

    private Operations operations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        operations = new Operations();
        if (savedInstanceState != null) {
            showResult(savedInstanceState.getString("onScreen"));
            showSmallResult(savedInstanceState.getString("onSmallScreen"));
            operations.setTypeOperation(savedInstanceState.getInt("typeOperation"));
            operations.setDot(savedInstanceState.getBoolean("isDot"));
            operations.setMinus(savedInstanceState.getBoolean("isMinus"));
            operations.setMemory(savedInstanceState.getDouble("memory"));
        } else {
            NumPanelFragment numPanel = new NumPanelFragment();
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.replace(R.id.NumPanel, numPanel);
            ft1.commit();
        }
        View addPanel = findViewById(R.id.AdditionalPanel);
        if(addPanel != null && getSupportFragmentManager().findFragmentById(R.id.AdditionalPanel) == null) {
            AdditionalPanelFragment additionalPanel = new AdditionalPanelFragment();
            FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
            ft2.replace(R.id.AdditionalPanel, additionalPanel);
            ft2.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("onScreen", getResult());
        outState.putString("onSmallScreen", getSmallResult());
        outState.putInt("typeOperations", operations.getTypeOperation());
        outState.putBoolean("isDot", operations.getDot());
        outState.putBoolean("isMinus", operations.getMinus());
        outState.putDouble("memory", operations.getMemory());
    }

    @Override
    public void showResult(String value) {
        textView.setText(value);
    }

    @Override
    public String getResult() {
        return textView.getText().toString();
    }

    @Override
    public void showSmallResult(String value) { textViewSmall.setText(value); }

    @Override
    public String getSmallResult() { return textViewSmall.getText().toString().split(" ")[0]; }

    @Override
    public Operations getOperations() {
        return operations;
    }
}