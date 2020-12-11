package suan.chan.pzpi_17_8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textBox;
    Button num0;
    Button num1;
    Button num2;
    Button num3;
    Button num4;
    Button num5;
    Button num6;
    Button num7;
    Button num8;
    Button num9;
    Button opMult;
    Button opDiv;
    Button opPlus;
    Button opMin;
    Button opCalc;
    Button dot;
    Button clear;
    CalculatorModel calculatorModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textBox = findViewById(R.id.textBox);
        num0 = findViewById(R.id.num0);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        num4 = findViewById(R.id.num4);
        num5 = findViewById(R.id.num5);
        num6 = findViewById(R.id.num6);
        num7 = findViewById(R.id.num7);
        num8 = findViewById(R.id.num8);
        num9 = findViewById(R.id.num9);
        opMult = findViewById(R.id.opMult);
        opDiv = findViewById(R.id.opDiv);
        opPlus = findViewById(R.id.opPlus);
        opMin = findViewById(R.id.opMin);
        opCalc = findViewById(R.id.opCalc);
        dot = findViewById(R.id.dot);
        clear = findViewById(R.id.clear);

        calculatorModel = new ViewModelProvider(this).get(CalculatorModel.class);

        calculatorModel.getCalculatedString().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                textBox.setText(s);
            }
        });

        num0.setOnClickListener(clickListener);
        num1.setOnClickListener(clickListener);
        num2.setOnClickListener(clickListener);
        num3.setOnClickListener(clickListener);
        num4.setOnClickListener(clickListener);
        num5.setOnClickListener(clickListener);
        num6.setOnClickListener(clickListener);
        num7.setOnClickListener(clickListener);
        num8.setOnClickListener(clickListener);
        num9.setOnClickListener(clickListener);
        opMult.setOnClickListener(clickListener);
        opDiv.setOnClickListener(clickListener);
        opPlus.setOnClickListener(clickListener);
        opMin.setOnClickListener(clickListener);
        opCalc.setOnClickListener(clickListener);
        dot.setOnClickListener(clickListener);
        clear.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.num0:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("0"));
                    break;
                case R.id.num1:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("1"));
                    break;
                case R.id.num2:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("2"));
                    break;
                case R.id.num3:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("3"));
                    break;
                case R.id.num4:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("4"));
                    break;
                case R.id.num5:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("5"));
                    break;
                case R.id.num6:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("6"));
                    break;
                case R.id.num7:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("7"));
                    break;
                case R.id.num8:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("8"));
                    break;
                case R.id.num9:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("9"));
                    break;
                case R.id.opMult:
                    calculatorModel.addNewPart(DataPartFactory.GetOperationPart("*"));
                    break;
                case R.id.opDiv:
                    calculatorModel.addNewPart(DataPartFactory.GetOperationPart("/"));
                    break;
                case R.id.opPlus:
                    calculatorModel.addNewPart(DataPartFactory.GetOperationPart("+"));
                    break;
                case R.id.opMin:
                    calculatorModel.addNewPart(DataPartFactory.GetOperationPart("-"));
                    break;
                case R.id.opCalc:
                    calculatorModel.calculateAll();
                    break;
                case R.id.dot:
                    calculatorModel.addNewPart(DataPartFactory.GetNumberPart("."));
                    break;
                case R.id.clear:
                    calculatorModel.clear();
                    break;
                default:
                    break;
            }
        }
    };
}