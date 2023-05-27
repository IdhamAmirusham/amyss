package com.example.basicunitconveter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etUnitsUsed, etRebatePercentage;
    Button btnCalculate, btnClear;
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUnitsUsed = findViewById(R.id.editTextNumberDecimal2);
        etRebatePercentage = findViewById(R.id.etRebatePercentage);
        btnCalculate = findViewById(R.id.btnCalculate);
        btnClear = findViewById(R.id.btnClear);
        tvOutput = findViewById(R.id.tvOutput);

        btnCalculate.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnClear) {
            etUnitsUsed.setText("");
            etRebatePercentage.setText("");
            tvOutput.setText("");
            return;
        }

        switch (view.getId()) {
            case R.id.btnCalculate:
                try {
                    int unitsUsed = Integer.parseInt(etUnitsUsed.getText().toString());
                    double rebatePercentage = Double.parseDouble(etRebatePercentage.getText().toString());

                    double totalCharges = 0.0;

                    if (unitsUsed <= 200) {
                        totalCharges = unitsUsed * 0.218;
                    } else if (unitsUsed <= 300) {
                        totalCharges = (200 * 0.218) + ((unitsUsed - 200) * 0.334);
                    } else if (unitsUsed <= 600) {
                        totalCharges = (200 * 0.218) + (100 * 0.334) + ((unitsUsed - 300) * 0.516);
                    } else {
                        totalCharges = (200 * 0.218) + (100 * 0.334) + (300 * 0.516) + ((unitsUsed - 600) * 0.546);
                    }

                    double finalCost = totalCharges - (totalCharges * rebatePercentage);

                    String result = String.format("Total Charges: RM %.2f\nFinal Cost: RM %.2f", totalCharges, finalCost);
                    tvOutput.setText(result);

                } catch (NumberFormatException e) {
                    Toast.makeText(this, "Invalid input: please enter valid numbers", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
