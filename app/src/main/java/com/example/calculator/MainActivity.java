package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView txtDisplay;
    private double firstValue = 0;
    private double secondValue = 0;
    private String operator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDisplay = findViewById(R.id.txtDisplay);

        setNumberButtonListeners();
        setOperationButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtonIds = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDecimal};
        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            if (isNewOp) txtDisplay.setText("");
            isNewOp = false;
            txtDisplay.append(b.getText().toString());
        };
        for (int id : numberButtonIds) findViewById(id).setOnClickListener(listener);
    }

    private void setOperationButtonListeners() {
        findViewById(R.id.btnAdd).setOnClickListener(v -> operationClick("+"));
        findViewById(R.id.btnSubtract).setOnClickListener(v -> operationClick("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> operationClick("*"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> operationClick("/"));
        findViewById(R.id.btnSqrt).setOnClickListener(v -> sqrtClick());
        findViewById(R.id.btnEquals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.btnClear).setOnClickListener(v -> clearDisplay());
        findViewById(R.id.btnDelete).setOnClickListener(v -> deleteLast());
        findViewById(R.id.btnSignChange).setOnClickListener(v -> changeSign());
    }

    private void operationClick(String op) {
        firstValue = Double.parseDouble(txtDisplay.getText().toString());
        operator = op;
        isNewOp = true;
    }

    private void sqrtClick() {
        firstValue = Double.parseDouble(txtDisplay.getText().toString());
        txtDisplay.setText(String.valueOf(Math.sqrt(firstValue)));
        isNewOp = true;
    }

    private void calculateResult() {
        secondValue = Double.parseDouble(txtDisplay.getText().toString());
        double result = 0;
        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                if (secondValue != 0) {
                    result = firstValue / secondValue;
                } else {
                    txtDisplay.setText("Error");
                    isNewOp = true;
                    return;
                }
                break;
        }
        txtDisplay.setText(String.valueOf(result));
        isNewOp = true;
    }

    private void clearDisplay() {
        txtDisplay.setText("0");
        isNewOp = true;
    }

    private void deleteLast() {
        String currentText = txtDisplay.getText().toString();
        if (currentText.length() > 1) {
            txtDisplay.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            txtDisplay.setText("0");
        }
    }

}