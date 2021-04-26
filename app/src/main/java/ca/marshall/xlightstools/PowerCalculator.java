package ca.marshall.xlightstools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class PowerCalculator extends AppCompatActivity {

    EditText NumOfStrings;// = (EditText) findViewById(R.id.editText6);
    EditText LightsPerString;// = (EditText) findViewById(R.id.editText3);
    EditText PowerSupply;// = (EditText) findViewById(R.id.botdiin);
    EditText MaxUsage;// = (EditText) findViewById(R.id.circov);
    EditText Voltage;// = (EditText) findViewById(R.id.sttl);
    EditText AmpsPerLight;// = (EditText) findViewById(R.id.sabl);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_calculator);

        // Set activity window to fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //dont show keyboard on start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Hide the action bar
        getSupportActionBar().hide();



        NumOfStrings = (EditText) findViewById(R.id.editText6);
        LightsPerString = (EditText) findViewById(R.id.editText3);
        PowerSupply = (EditText) findViewById(R.id.botdiin);
        MaxUsage = (EditText) findViewById(R.id.circov);
        Voltage = (EditText) findViewById(R.id.sttl);
        AmpsPerLight = (EditText) findViewById(R.id.sabl);
    }

}
