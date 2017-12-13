package com.example.macbookair.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnDiv;
    Button btnMul;
    Button btnSum;
    Button btnSub;
    Button btnClear;
    boolean OperatorClicked = false;
    TextView txtField;
    String num1 = "";
    String num2 = "";
    int OperatorId = 0;
    double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initId();
    }

    private void initId() {
        btnDiv = findViewById(R.id.btnDiv);
        btnMul = findViewById(R.id.btnMul);
        btnClear = findViewById(R.id.btnClear);
        btnSub = findViewById(R.id.btnSub);
        btnSum = findViewById(R.id.btnSum);
        txtField = findViewById(R.id.txtResult);
    }

    public void getBtnText(View v) {

        if (txtField.getText().toString().equals("0")) {
            txtField.setText("");
        }
        if (OperatorClicked && txtField.getText().equals(num1))
            txtField.setText("");

        if (!OperatorClicked) {
            String oldValue = txtField.getText().toString();
            txtField.setText(oldValue + ((Button) v).getText().toString());
            num1 = txtField.getText().toString();
        }

        if (OperatorClicked) {
            String oldValue = txtField.getText().toString();
            txtField.setText(oldValue + ((Button) v).getText().toString());
            num2 = txtField.getText().toString();
        }

    }


    public void OperationSignedClicked(View v) {
        OperatorClicked = true;
        OperatorId = v.getId();
    }

    public void calculate() {
        switch (OperatorId) {
            case R.id.btnSum:
                result = Float.parseFloat(num1) + Float.parseFloat(num2);
                break;
            case R.id.btnSub:
                result = Float.parseFloat(num1) - Float.parseFloat(num2);
                break;
            case R.id.btnMul:
                result = Float.parseFloat(num1) * Float.parseFloat(num2);
                break;
            case R.id.btnDiv:
                if (num2.equals("0")) {
                    txtField.setText("Can't divide to 0");
                    return;
                }
                result = Float.parseFloat(num1) / Float.parseFloat(num2);
                break;
            default:
                return;
        }
    }

    public void btnClearEvent(View v) {
        hardReset();
    }

    public void btnEqualEvent(View v) {
        calculate();
        String  finalResult;
        finalResult=String.valueOf(result);
        if(finalResult.endsWith(".0"))
        {
            finalResult=finalResult.substring(0,finalResult.length()-2);
        }
        txtField.setText(finalResult);
        OperatorClicked=false;
    }

    private void hardReset()
    {
        OperatorClicked=false;
        txtField.setText("");
        num1="";
        num2="";
    }


}
