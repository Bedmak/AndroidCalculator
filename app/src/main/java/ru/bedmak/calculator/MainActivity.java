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
        }
        if (savedInstanceState == null) {
            NumPanelFragment numPanel = new NumPanelFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.NumPanel, numPanel);
            ft.commit();
        }
    }

    @Override
    public void setResult(String s) {
        TextView tv = (TextView) findViewById(R.id.textView);
        String text = tv.getText().toString();
        if (s.equals("")) {
            text = "";
        } else if (s.equals("0")) {
            if(!text.isEmpty()) {
                text = text + s;
            }
        } else if (s.equals("-")) {               // корекктно обработывать первый символ если не минус-ставим, если минус-убираем
            text = s + text;
        } else {
            text = text + s;
        }
        tv.setText(text);
    }

    @Override
    public String getResult() {
        return ((TextView) findViewById(R.id.textView)).getText().toString();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("onScreen", getResult());
    }
}
