package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private double firstNum = 0;
    private String operation = "";
    private TextView screen;
    private boolean isOperatorPressed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = findViewById(R.id.screen);
    }

    public void numFunc(View view) {
        Button button = (Button) view;
        String currentText = screen.getText().toString();

        if (currentText.equals("0") || currentText.equals("Error")) {
            screen.setText(button.getText().toString());
        } else {
            screen.append(button.getText().toString());
        }

        isOperatorPressed = false;
    }

    public void operFunc(View view) {
        Button button = (Button) view;
        String currentText = screen.getText().toString();

        if (isOperatorPressed) {
            return ;
        }

        if (!currentText.matches("^-?\\d+(\\.\\d+)?$")) {
            screen.setText("Error");
            return;
        }

        firstNum = Double.parseDouble(currentText);
        operation = button.getText().toString();
        screen.append(" " + operation + " ");
        isOperatorPressed = true;
    }

    public void equalFunc(View view) {
        String currentText = screen.getText().toString().trim();

        String[] parts = currentText.split(" ");

        if (parts.length < 3 || parts[2].isEmpty()) {
            screen.setText("Error");
            return;
        }
        if (!parts[2].matches("^-?\\d+(\\.\\d+)?$")) {
            screen.setText("Error");
            return;
        }

        double secondNum = Double.parseDouble(parts[2]);
        double result = 0;

        switch (operation) {
            case "+":
                result = firstNum + secondNum;
                break;
            case "-":
                result = firstNum - secondNum;
                break;
            case "X":
                result = firstNum * secondNum;
                break;
            case "/":
                if (secondNum == 0) {
                    screen.setText("Error");
                    return;
                }
                result = firstNum / secondNum;
                break;
        }

        screen.setText(String.valueOf(result));
        isOperatorPressed = false;
    }

    public void resetFunc(View view) {
        screen.setText("0");
        firstNum = 0;
        operation = "";
        isOperatorPressed = false;
    }

    public void deleteFunc(View view) {
        String currentText = screen.getText().toString();
        if (currentText.length() > 1) {
            screen.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            screen.setText("0");
        }

        isOperatorPressed = false;
    }
}