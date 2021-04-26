package ca.marshall.xlightstools;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PowerCalculator extends AppCompatActivity {

    EditText NumOfStrings;// = (EditText) findViewById(R.id.editText6);
    EditText LightsPerString;// = (EditText) findViewById(R.id.editText3);
    EditText PowerSupply;// = (EditText) findViewById(R.id.botdiin);
    EditText MaxUsage;// = (EditText) findViewById(R.id.circov);
    EditText Voltage;// = (EditText) findViewById(R.id.sttl);
    EditText AmpsPerLight;// = (EditText) findViewById(R.id.sabl);

    Button toReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_calculator);

        // Set activity window to fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the action bar
        getSupportActionBar().hide();

        /*
        toReturn = (Button) findViewById(R.id.button);
        // Initialize and assign on click listener to search button on home page
        toReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
                overridePendingTransition(0,0);
            }
        });
        */




        NumOfStrings = (EditText) findViewById(R.id.editText6);
        LightsPerString = (EditText) findViewById(R.id.editText3);
        PowerSupply = (EditText) findViewById(R.id.botdiin);
        MaxUsage = (EditText) findViewById(R.id.circov);
        Voltage = (EditText) findViewById(R.id.sttl);
        AmpsPerLight = (EditText) findViewById(R.id.sabl);
    }

}
