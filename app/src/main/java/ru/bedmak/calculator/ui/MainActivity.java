package ru.bedmak.calculator.ui;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ru.bedmak.calculator.MainViewListener;
import ru.bedmak.calculator.R;


public class MainActivity extends AppCompatActivity implements MainViewListener {

    private TextView textView;
    private TextView textViewSmall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textViewSmall = findViewById(R.id.textViewSmall);
        if (savedInstanceState != null) {
            setResult(savedInstanceState.getString("onScreen"));
            setSmallResult(savedInstanceState.getString("onSmallScreen"));
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
    }

    @Override
    public void setResult(String value) {
        textView.setText(value);
    }

    @Override
    public String getResult() {
        return textView.getText().toString();
    }

    @Override
    public void setSmallResult(String value) { textViewSmall.setText(value); }

    @Override
    public String getSmallResult() { return textViewSmall.getText().toString(); }
}