package com.websarva.wings.android.dentaku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            textView.setText(editText.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.edittext);
        button = (Button) findViewById(R.id.button_clear);
        button.setOnClickListener(buttonListener);

        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener);

        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_sub).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_mul).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_div).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_clear).setOnClickListener(buttonOperatorListener);

        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener);
    }

    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;
            String but1 =  button.getText().toString();
            String str1 =  editText.getText().toString();
            if (isOperatorKeyPushed == true) {
                if (but1.equals("clear")) {
                    textView.setText("");
                    editText.setText("");
                    result = 0;
                    isOperatorKeyPushed = false;
                    recentOperator = R.id.button_equal;
                }else{
                    editText.setText(button.getText());}
            } else if (but1.equals(".")) {
                if(str1.contains(".")){
                } else {
                    editText.append(button.getText());
                }
            } else if (but1.equals("clear")) {
                textView.setText("");
                editText.setText("");
                result = 0;
                isOperatorKeyPushed = false;
                recentOperator = R.id.button_equal;
            }else if(but1.equals("0")){
                if(str1 == "0"){
                } else {
                    editText.append(button.getText());
                }
            } else if (but1.equals("+")) {
                textView.setText("");
                editText.setText("");
                result = 0;
                isOperatorKeyPushed = false;
                recentOperator = R.id.button_equal;
            } else {
                editText.append(button.getText());
            }
            isOperatorKeyPushed = false;
        }
    };

    int recentOperator = R.id.button_equal; // 最近押された計算キー
    double result;  // 計算結果
    boolean isOperatorKeyPushed;    // 計算キーが押されたことを記憶

    View.OnClickListener buttonOperatorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button operatorButton = (Button) view;
            double value = Double.parseDouble(editText.getText().toString());
            String but2 =  button.getText().toString();
            String str2 =  editText.getText().toString();
            if (recentOperator == R.id.button_equal) {
                result = value;
            } else {
                result = calc(recentOperator, result, value);
                editText.setText(String.valueOf(result));
            }
            recentOperator = operatorButton.getId();
            textView.setText(operatorButton.getText());
            isOperatorKeyPushed = true;
        }
    };
    double calc(int operator, double value1, double value2) {
        switch (operator) {
            case R.id.button_add:
                return value1 + value2;
            case R.id.button_sub:
                return value1 - value2;
            case R.id.button_mul:
                return value1 * value2;
            case R.id.button_div:
                return value1 / value2;
            default:
                return value1;
        }
    }
}
