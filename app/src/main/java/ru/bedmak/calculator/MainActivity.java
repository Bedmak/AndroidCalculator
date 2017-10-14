package ru.bedmak.calculator;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements MainViewListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            setResult(savedInstanceState.getString("onScreen"));
        } else {
            NumPanelFragment numPanel = new NumPanelFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.NumPanel, numPanel);
            ft.commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("onScreen", getResult());
    }

    @Override
    public void setResult(String s) {
        ((TextView) findViewById(R.id.textView)).setText(s);
    }

    @Override
    public String getResult() {
        return ((TextView) findViewById(R.id.textView)).getText().toString();
    }
}