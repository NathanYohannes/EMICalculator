package com.example.test2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class EMI_Page extends AppCompatActivity {

    private TextInputLayout mortgageAmount;
    private TextInputLayout amortizationPeriod;
    private TextInputLayout interestRate;

    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_emi_page);

        mortgageAmount = findViewById(R.id.mortgageAmountInputLayout);
        amortizationPeriod = findViewById(R.id.amortizationPeriodInputLayout);
        interestRate =  findViewById(R.id.interestRateInputLayout);

        //Button bt_calculate = (Button) findViewById(R.id.bt_calculate);
        tv_result = (TextView) findViewById(R.id.tv_result);



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void makeCalculations(View v){
        String princpStr = mortgageAmount.getEditText().getText().toString();
        String amortzStr = amortizationPeriod.getEditText().getText().toString();
        String intrstStr = interestRate.getEditText().getText().toString();

        if(princpStr.isEmpty() || amortzStr.isEmpty() || intrstStr.isEmpty()){
            return;
        }

        double principal = Double.parseDouble(princpStr);
        double amortz = Double.parseDouble(amortzStr);
        double intrst = Double.parseDouble(intrstStr);

        double n = amortz*12;
        double r =  intrst/12/100;
        double modifiedInterest = Math.pow((1+r),n);
        double emi = principal * r * (modifiedInterest/(modifiedInterest-1));
        emi = Math.round(emi*100.0)/100.0;

        tv_result.setText("EMI: $"+ emi);
    }
}