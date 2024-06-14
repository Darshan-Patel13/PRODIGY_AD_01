package com.darshan.android_devlopment_prodigy_task1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDivide, buttonMultiply, buttonAddition, buttonSubtraction, buttonEqual;
    MaterialButton button7, button8, button9, button4, button5, button6, button1, button2, button3;
    MaterialButton buttonAC, button0, buttonDot;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assignId(buttonC, R.id.button_c);
        assignId(button0, R.id.button_0);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_8);
        assignId(button9, R.id.button_9);
        assignId(buttonAC, R.id.button_AC);
        assignId(buttonDot, R.id.button_DOT);
        assignId(buttonDivide, R.id.button_divide);
        assignId(buttonMultiply, R.id.multiply);
        assignId(buttonAddition, R.id.addition);
        assignId(buttonSubtraction, R.id.subtraction);
        assignId(buttonEqual, R.id.equals);
        assignId(buttonBrackClose, R.id.button_close_bracket);
        assignId(buttonBrackOpen, R.id.button_open_bracket);

        solutionTv.setText("0"); // Set default value to 0
    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String btnText = btn.getText().toString();   // to take button text and store in btnText
        String dataToCalculate = solutionTv.getText().toString(); // storing all text of button which appear in solutionTv

        switch (btnText) {
            case "AC":
                solutionTv.setText("0");             // to avoid ac,c,=, from solutiontv
                resultTv.setText("0");
                return;
            case "=":
                solutionTv.setText(resultTv.getText());
                return;
            case "C":
                if (!dataToCalculate.equals("0") && !dataToCalculate.isEmpty()) {
                    dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
                }
                if (dataToCalculate.isEmpty()) {
                    dataToCalculate = "0"; // Set to 0 if string is empty
                }
                break;
            default:
                if (dataToCalculate.equals("0")) {
                    dataToCalculate = btnText; // Replace 0 with the new input
                } else {
                    dataToCalculate = dataToCalculate + btnText; // adding all data to dataToCalculate including currently pressed button in last of text
                }
                break;
        }

        solutionTv.setText(dataToCalculate);        // display updated text to solutionTv
        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equalsIgnoreCase("error")) {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data) {
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();

            String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
            if (finalResult.endsWith(".0")) {
                finalResult = finalResult.replace(".0", "");
            }
            return finalResult;

        } catch (Exception e) {
            return "error";
        }
    }
}