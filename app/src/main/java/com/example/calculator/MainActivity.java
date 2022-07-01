package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

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
    Button dotButton,
            equalButton,
            clearButton,
            delButton;
    boolean decimalPoint;
    boolean lastNumericInput;
    boolean result;
    int [] opButton = {R.id.decimalPoint,R.id.equalButton,R.id.clearButton, R.id.delButton};


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

        findViewById(R.id.equalButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEqual();
            }
        });
        findViewById(R.id.decimalPoint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDecimalPoint();
            }
        });
        findViewById(R.id.delButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDelButton();
            }
        });
        findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setClearButton();
            }
        });
    }

    public void setEqual() {
        String getExpression = txtScreen.getText().toString();
        try {

            Expression expression = new ExpressionBuilder(getExpression).build();
            double lResult = expression.evaluate();
            String finalResult = String.valueOf(lResult);
            txtScreen.setText(finalResult);
            result = true;


        } catch (IllegalArgumentException ex) {
            txtScreen.setText("");
        }

    }

    public void setDecimalPoint() {
        dotButton = findViewById(R.id.decimalPoint);

        if (lastNumericInput && !result) {
            txtScreen.append(dotButton.getText().toString());
            lastNumericInput = false;
        }
    }

    public void setDelButton() {
        if (!result) {
            try {
                String getText = txtScreen.getText().toString();
                StringBuffer newText = new StringBuffer(getText);
                newText.deleteCharAt(newText.length() - 1);
                txtScreen.setText(newText);
            } catch (StringIndexOutOfBoundsException ex) {
                txtScreen.setText("");
            }
        } else {
            txtScreen.setText("");
        }
        lastNumericInput = true;
    }

    public void setClearButton() {
        txtScreen.setText("");
    }
}