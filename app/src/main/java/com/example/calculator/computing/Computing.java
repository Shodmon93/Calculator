package com.example.calculator.computing;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Computing extends AppCompatActivity {
    int x = 0;
    int y = 0;
    char symbol = ' ';
    double result = 0;
    public Computing(){

    }

    public Computing(int x, int y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public double calculating (double x, double y, char symbol){
        switch (symbol){
            case '+':
                result =  x + y;
            case '-':
                result =  x - y;
                break;
            case '*':
                result =  x * y;
                break;
            case '/':
                result =  x / y;
                break;
            default:
                return 0;
        }
        return result;
    }



    public int addition (int x, int y){
        return Math.addExact(x,y);
    }
    public int subtract (int x, int y){
        return Math.subtractExact(x,y);
    }
    public float multiple (){
        return Math.multiplyExact(x,y);
    }
    public double divide (){
        return x / y;
    }


}
