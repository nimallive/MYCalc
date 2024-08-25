package com.example.mit_15;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private double firstNumber = 0;
    private double secondNumber = 0;
    private String operator = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                String buttonText = button.getText().toString();
                String currentText = display.getText().toString();

                switch (buttonText) {
                    case "C":
                        display.setText("");
                        firstNumber = 0;
                        secondNumber = 0;
                        operator = "";
                        break;
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        if (!currentText.isEmpty()) {
                            firstNumber = Double.parseDouble(currentText);
                            operator = buttonText;
                            display.setText("");
                        }
                        break;
                    case "=":
                        if (!operator.isEmpty() && !currentText.isEmpty()) {
                            secondNumber = Double.parseDouble(currentText);
                            display.setText(calculateResult(firstNumber, secondNumber, operator));
                            operator = "";
                        }
                        break;
                    default: // Digits 0-9
                        display.setText(currentText + buttonText);
                        break;
                }
            }
        };

        int[] buttonIds = {
                R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
                R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
                R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
                R.id.btnEqual, R.id.btnClear
        };

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private String calculateResult(double first, double second, String operator) {
        double result;
        switch (operator) {
            case "+":
                result = first + second;
                break;
            case "-":
                result = first - second;
                break;
            case "*":
                result = first * second;
                break;
            case "/":
                result = first / second;
                break;
            default:
                return "";
        }
        return String.valueOf(result);
    }
}
