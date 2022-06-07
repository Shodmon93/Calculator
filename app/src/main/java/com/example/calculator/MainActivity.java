package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView txtScreen;
    int[] operationButton = {
            R.id.addButton,
            R.id.subButton,
            R.id.multiButton,
            R.id.divButton
    };
    int[] numberButton = {
            R.id.numOne,
            R.id.numTwo,
            R.id.numThree,
            R.id.numFour,
            R.id.numFive,
            R.id.numSix,
            R.id.numSeven,
            R.id.numEight,
            R.id.numNine
    };
    //    Button dotButton,
//            equalButton,
//            clearButton;
    boolean decimalPoint;
    boolean lastNumericInput;
    boolean result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getValidNumberFromUser();
        setOperatorOnClick();

    }


    public void getValidNumberFromUser() {
        txtScreen = findViewById(R.id.txtScreen);
        View.OnClickListener onNumberClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (result) {
                    //if the result has already calculated replace the result with new number clicked
                    txtScreen.setText(button.getText().toString());
                    result = false;
                }
                // if not append the next number
                else {
                    txtScreen.append(button.getText().toString());
                }
                //reset the input number
                lastNumericInput = true;
            }
        };
        //set the click listener to all numeric button
        for (int numberId : numberButton) {
            findViewById(numberId).setOnClickListener(onNumberClick);
        }
    }

    public void setOperatorOnClick() {
        View.OnClickListener onExpressionClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                /* check if the last input is numeric,
                if yes do not append the operator if not append the operator */
                if (!lastNumericInput) {
                    return;
                }
                txtScreen.append(button.getText().toString());
                lastNumericInput = false;
            }
        };
        //set the listener to all operators button
        for (int expressionId : operationButton) {
            findViewById(expressionId).setOnClickListener(onExpressionClick);
        }
    }























//    public void getNumber() {
//        View.OnClickListener digitListener = v -> {
//            Button button = (Button) v;
//            txtScreen.append(button.getText().toString());
//            lastNumericInput = true;
//        };
//        for (int id : numberButton) {
//            findViewById(id).setOnClickListener(digitListener);
//        }
//    }
//
//    public void setLastDot() {
//        dotButton = findViewById(R.id.dote);
//        View.OnClickListener dotClickListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (lastNumericInput && !dot) {
//                    txtScreen.append(".");
//                    lastNumericInput = false;
//                    dot = true;
//                }
//            }
//        };
//        dotButton.setOnClickListener(dotClickListener);
//
//    }
//
//    public void getOperator() {
//        View.OnClickListener listener = v -> {
//            Button button = (Button) v;
//            if (lastNumericInput) {
//                txtScreen.append(button.getText().toString());
//                lastNumericInput = false;
//                dot = false;
//            }
//
//        };
//        for (int b : operationButton) {
//            findViewById(b).setOnClickListener(listener);
//        }
//    }
//
//    public void equal() {
//        View.OnClickListener listener = v -> {
//            String input = txtScreen.getText().toString();
//            try {
//                Expression expression = new ExpressionBuilder(input).build();
//                Double result = expression.evaluate();
//                String finalResult = String.valueOf(result);
//                txtScreen.setText(finalResult);
//            } catch (IllegalArgumentException ex) {
//                txtScreen.setText("");
//            }
//
//        };
//        equalButton.setOnClickListener(listener);
//    }
//
//    public void clear() {
//        clearButton = findViewById(R.id.clearButton);
//        View.OnClickListener clearListener = v -> txtScreen.setText("");
//        clearButton.setOnClickListener(clearListener);
//    }
}