package com.example.electricconsumptioncalculator.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.electricconsumptioncalculator.R;
import com.example.electricconsumptioncalculator.UIUtility;
import com.example.electricconsumptioncalculator.ValidationUtility;
import com.example.electricconsumptioncalculator.model.Consumer;
import com.example.electricconsumptioncalculator.viewmodel.ElectricityBillVM;

public class MainActivity extends BaseActivity {
    private ElectricityBillVM electricityBillVM = new ElectricityBillVM();
    @Override
    protected void onCreate(Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_main);

        electricityBillVM = new ElectricityBillVM();
        electricityBillVM.getObserver().observe(MainActivity.this, new Observer<Consumer>() {
            @Override
            public void onChanged(Consumer pConsumer) {
                updateUI(pConsumer);
            }
        });

        Button btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcBill();
            }
        });
    }

    private void calcBill(){
        EditText vEtMeterReading = findViewById(R.id.tvMeterReading);
        String vMeterReading = null;

        if (vEtMeterReading.getText() !=null){
            vMeterReading = vEtMeterReading.getText().toString();
        }

        EditText vEtConsumerID = findViewById(R.id.tvConsumerId);
        String vConsumerID = null;

        if (vEtConsumerID.getText() !=null){
            vConsumerID = vEtConsumerID.getText().toString();
        }

        if(!ValidationUtility.isValidConsumerID(vConsumerID)){
            String vMessage = "Please enter valid 10 digit alphanumeric consumer ID.";
            String vlabel = "OK";
            String vTitle = "Electricity Calculator";
            UIUtility.showAlertDialog(MainActivity.this,vMessage,vlabel, vTitle);
            return;
        }

        electricityBillVM.notifyChange(vMeterReading,vConsumerID);
    }

    private void updateUI(Consumer pConsumer){
        TextView tvBill = findViewById(R.id.tvBill);
        tvBill.setText(pConsumer.getBillAmount());

        EditText vEtMeterReading = findViewById(R.id.tvMeterReading);
        vEtMeterReading.setText(pConsumer.getMeterReading());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        int vId = pItem.getItemId();
        if (vId == R.id.settings) {
            // Open Settings Screen
            openSettings();
            return true;
        }

        return super.onOptionsItemSelected(pItem);
    }

    private void openSettings(){
        Intent vIntent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(vIntent);
    }
}