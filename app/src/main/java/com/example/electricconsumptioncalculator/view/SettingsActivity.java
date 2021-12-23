package com.example.electricconsumptioncalculator.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.electricconsumptioncalculator.R;
import com.example.electricconsumptioncalculator.ValidationUtility;
import com.example.electricconsumptioncalculator.model.Consumer;
import com.example.electricconsumptioncalculator.viewmodel.SettingsVM;

public class SettingsActivity extends BaseActivity {
    private SettingsVM settingsVM = new SettingsVM();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        settingsVM.getObserver().observe(SettingsActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String pjsonString) {
                populateSlabs();
            }
        });

        EditText vTvJsonString = findViewById(R.id.tvJsonString);
        vTvJsonString.setText(settingsVM.getGeneratedJsonString());
    }

    private void populateSlabs(){
        settingsVM.parseJson();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu pMenu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, pMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem pItem) {
        int vId = pItem.getItemId();
        if (vId == R.id.done) {
            assignJson();
            finish();
        }
        else {
            finish();
        }

        return super.onOptionsItemSelected(pItem);
    }

    private void assignJson(){
        EditText vTvJsonString = findViewById(R.id.tvJsonString);
        if(vTvJsonString.getText() != null &&
                !ValidationUtility.isStringEmpty(vTvJsonString.getText().toString())) {
            settingsVM.setJsonString(vTvJsonString.getText().toString());
        }
    }
}
