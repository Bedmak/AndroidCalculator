package ru.example.bedmak.calculator;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.TextView;

import values.NumPanelFragment;

public class MainActivity extends AppCompatActivity {

    private float value = 0;
    private int typeOperation = 0;
    private boolean flagDot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            value = savedInstanceState.getFloat("value");
            flagDot = savedInstanceState.getBoolean("flagDot");
            ((TextView) findViewById(R.id.textView)).setText(savedInstanceState.getString("onScreen"));
        }
        NumPanelFragment numPanel = new NumPanelFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.NumPanel, numPanel);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    public void onClick(View view) {
        
        if (view.getId() == R.id.buttonAC) {
           setText("");
        } else if(view.getId() == R.id.buttonPlusMinus) {
            return;
        } else if(view.getId() == R.id.buttonPercent) {         //Сделать коректное деление на 100
            return;
        } else if(view.getId() == R.id.buttonAddition) {
            setTypeOperation(1);
        } else if(view.getId() == R.id.buttonSubtraction) {
            setTypeOperation(2);
        } else if(view.getId() == R.id.buttonMultiplication) {
            setTypeOperation(3);
        } else if(view.getId() == R.id.buttonDivision) {
            setTypeOperation(4);
        } else if(view.getId() == R.id.buttonEquality) {
            doOperation();
        } else if(view.getId() == R.id.buttonDot){
            setFlagDot();
        } else if(view.getId() == R.id.button_0){
            setText("0");
        } else if(view.getId() == R.id.button_1){
            setText("1");
        } else if(view.getId() == R.id.button_2){
            setText("2");
        } else if(view.getId() == R.id.button_3){
            setText("3");
        } else if(view.getId() == R.id.button_4){
            setText("4");
        } else if(view.getId() == R.id.button_5){
            setText("5");
        } else if(view.getId() == R.id.button_6){
            setText("6");
        } else if(view.getId() == R.id.button_7){
            setText("7");
        } else if(view.getId() == R.id.button_8){
            setText("8");
        } else if(view.getId() == R.id.button_9) {
            setText("9");
        }

    }

    protected void setFlagDot() {
        if(!flagDot) {
            if(((TextView) findViewById(R.id.textView)).getText().toString().isEmpty()) {
                setText("0.");
            }else {
                setText(".");
            }
            flagDot = true;
        }
    }

    protected void setText(String s) {
        TextView tv = (TextView) findViewById(R.id.textView);
        String text = tv.getText().toString();
        if (s.equals("")) {
            text = "";
        }else if(s.equals("0")) {
            if(!text.isEmpty()) {
                text = text + s;
            }
        }else if(s.equals("-")) {               // корекктно обработывать первый символ если не минус-ставим, если минус-убираем
            text = s + text;
        }else {
            text = text + s;
        }
        tv.setText(text);
    }

    protected void setTypeOperation(int type) {
        TextView tv = (TextView) findViewById(R.id.textView);
        value = Float.parseFloat(tv.getText().toString());
        tv.setText("");
        flagDot = false;
        typeOperation = type;
    }

    protected void doOperation() {
        TextView tv = (TextView) findViewById(R.id.textView);
        String text = tv.getText().toString();
        if(typeOperation == 0) {
            return;
        }else if(typeOperation == 1) {
            value += Float.parseFloat(text);
        }else if(typeOperation == 2) {
            value -= Float.parseFloat(text);
        }else if(typeOperation == 3) {
            value *= Float.parseFloat(text);
        }else if(typeOperation == 4) {
            value /= Float.parseFloat(text);
        }
        if(value % 1 == 0) {
            tv.setText(Integer.toString((int) value));
            flagDot = false;
        }else{
            tv.setText(Float.toString(value));
            flagDot = true;
        }

    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putFloat("value", value);
        savedInstanceState.putBoolean("flagDot", flagDot);
        savedInstanceState.putString("onScreen", ((TextView) findViewById(R.id.textView)).getText().toString());
    }
}
