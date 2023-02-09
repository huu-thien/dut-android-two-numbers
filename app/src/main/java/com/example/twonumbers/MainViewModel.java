package com.example.twonumbers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<String>> histotyList;
    public LiveData<List<String>> getHistoryList() {
        if(histotyList == null) {
            histotyList = new MutableLiveData<>();
            histotyList.setValue(new ArrayList<String>());
        }
        return histotyList;
    }
//    public static String fmt(double d) {
//        if(d == (int)d)
//            return String.format("%d", (int)d);
//        else
//            return String.format("%s", d);
//    }
    public void btnExpression(String button, String numberA, String numberB) {
        ArrayList<String> currentList = (ArrayList<String>) histotyList.getValue();
        double result = 0;
        switch (button) {
            case "+":
                result = Double.parseDouble(numberA) + Double.parseDouble(numberB);
                currentList.add(numberA + " " + button + " " + numberB + " = " + result);
                histotyList.setValue(currentList);
                break;
            case "-":
                result = Double.parseDouble(numberA) - Double.parseDouble(numberB);
                currentList.add(numberA + " " + button + " " + numberB + " = " + result);
                histotyList.setValue(currentList);
                break;
            case "*":
                result = Double.parseDouble(numberA) * Double.parseDouble(numberB);
                currentList.add(numberA + " " + button + " " + numberB + " = " + result);
                histotyList.setValue(currentList);
                break;
            case "/":
                result = Double.parseDouble(numberA) / Double.parseDouble(numberB);
                currentList.add(numberA + " " + button + " " + numberB + " = " + result);
                histotyList.setValue(currentList);
                break;
            default:
                break;
        }
    }
}
