package suan.chan.pzpi_17_8;

import android.renderscript.Element;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalculatorModel extends ViewModel {
    private ArrayList<DataPart> items;
    MutableLiveData<String> calculatedString;
    DecimalFormat df = new DecimalFormat("#.############");

    public CalculatorModel() {
        items = new ArrayList<>();
        calculatedString = new MutableLiveData<>();
    }

    public LiveData<String> getCalculatedString() {
        if (calculatedString == null) {
            calculatedString = new MutableLiveData<>();
            calculatedString.postValue("");
        }
        return calculatedString;
    }

    public void addNewPart(DataPart part) {
        if (items.isEmpty()) {
            if (part.getType() == DataType.Number) {
                items.add(part);
            }
            return;
        }
        DataPart last = items.get(items.size() - 1);
        if (part.getType() == DataType.Number) {
            if (last.getType() == DataType.Operation) {
                items.add(part);
            } else {
                if (part.getData().equals(".")) {
                    if (!last.isFloatNum()) {
                        last.setData(last.getData() + ".");
                        last.setFloatNum(true);
                    }
                } else {
                    last.setData(last.getData() + part.getData());
                }
            }
        } else {
            if (last.getType() == DataType.Number) {
                items.add(part);
            } else {
                items.remove(items.size() - 1);
                items.add(part);
            }
        }
        reconstructString();
    }

    private void reconstructString() {
        StringBuilder str = new StringBuilder();
        for (DataPart part : items) {
            str.append(part.getData());
            str.append(" ");
        }
        calculatedString.postValue(str.toString());
    }

    public void calculateAll(){
        try {
            calculate();
        } catch (Exception ex){
            clear();
            calculatedString.postValue("Error");
        }
    }

    private void calculate() {
        operationTrim();
        primaryOperationCalculate();
        secondaryOperationCalculate();
        reconstructString();
    }

    private void primaryOperationCalculate(){
        boolean primaryOperationSearch = false;
        do {
            primaryOperationSearch = false;
            for (int i = 0; i < items.size(); i++){
                DataPart current = items.get(i);
                if(current.getType() == DataType.Operation
                        && (current.getData().equals("*") || current.getData().equals("/"))){
                    double first = Double.parseDouble(items.get(i - 1).getData());
                    double second = Double.parseDouble(items.get(i + 1).getData());
                    double result;
                    if(current.getData().equals("*")){
                        result = first * second;
                    } else {
                        result = first / second;
                    }
                    items.remove(i+1);
                    items.remove(i);
                    items.remove(i-1);
                    items.add(i-1, new DataPart(df.format(result), DataType.Number, false));
                    primaryOperationSearch = true;
                    break;
                }
            }
        } while (primaryOperationSearch);
    }

    private void secondaryOperationCalculate(){
        boolean operationSearch = false;
        do {
            operationSearch = false;
            for (int i = 0; i < items.size(); i++){
                DataPart current = items.get(i);
                if(current.getType() == DataType.Operation
                        && (current.getData().equals("+") || current.getData().equals("-"))){
                    double first = Double.parseDouble(items.get(i - 1).getData());
                    double second = Double.parseDouble(items.get(i + 1).getData());
                    double result;
                    if(current.getData().equals("+")){
                        result = first + second;
                    } else {
                        result = first - second;
                    }
                    items.remove(i+1);
                    items.remove(i);
                    items.remove(i-1);
                    items.add(i-1, new DataPart(df.format(result), DataType.Number, false));
                    operationSearch = true;
                    break;
                }
            }
        } while (operationSearch);
    }

    private void operationTrim() {
        if (!items.isEmpty() && items.get(items.size() - 1).getType() == DataType.Operation) {
            items.remove(items.size() - 1);
        }
    }

    public void clear() {
        items.clear();
        if (calculatedString != null) {
            calculatedString.postValue("");
        }
    }
}
