package ru.bedmak.calculator;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements MainViewListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        if (savedInstanceState != null) {
            setResult(savedInstanceState.getString("onScreen"));
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
    }

    @Override
    public void setResult(String s) {
        textView.setText(s);
    }

    @Override
    public String getResult() {
        return textView.getText().toString();
    }
}