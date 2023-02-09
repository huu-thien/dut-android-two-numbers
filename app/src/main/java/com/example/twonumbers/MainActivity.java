package com.example.twonumbers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.twonumbers.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ArrayAdapter<String> listHistoryAdapter;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        model = new ViewModelProvider(this).get(MainViewModel.class);
        model.getHistoryList().observe(this, list -> {
            listHistoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
            binding.lvHistory.setAdapter(listHistoryAdapter);
        });

        binding.btnPlus.setOnClickListener(e -> {
            expressionResult("+", model, view);
        });
        binding.btnMinus.setOnClickListener(e -> {
            expressionResult("-", model,view);
        });
        binding.btnMultiply.setOnClickListener(e -> {
            expressionResult("*", model,view);
        });
        binding.btnDivide.setOnClickListener(e -> {
            expressionResult("/", model,view);
        });
    }

    private void expressionResult(String expression, MainViewModel model, View v) {
        try {
            String numberA = binding.numberA.getText().toString().trim();
            String numberB = binding.numberB.getText().toString().trim();
            model.btnExpression(expression, numberA, numberB);
            listHistoryAdapter.notifyDataSetChanged();

//            binding.numberA.getText().clear();
//            binding.numberB.getText().clear();

        } catch (NumberFormatException ee) {
            Snackbar snackbar = Snackbar.make(v, "Please check your input number (isNotEmpty or isNotText)!", Snackbar.LENGTH_LONG);
            snackbar.show();
        } catch (Exception ee) {
            ee.printStackTrace();
            Snackbar snackbar = Snackbar.make(v, ee.getMessage() == null ? "Problem occurred, please try again!" : ee.getMessage(), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

}