package ca.marshall.xlightstools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;

public class PropsOrganizer extends AppCompatActivity {

    GridView g;
    Button toReturn;
    Button addItem,removeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_props_organizer);

        g = (GridView) findViewById(R.id.grid);
        addItem = (Button) findViewById(R.id.addItem);
        removeItem = (Button) findViewById(R.id.removeItem);

        // Set activity window to fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the action bar
        getSupportActionBar().hide();

        toReturn = (Button) findViewById(R.id.back);
        // Initialize and assign on click listener to search button on home page
        toReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
                overridePendingTransition(0,0);
            }
        });

        //add item to grid when clicked
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
                overridePendingTransition(0,0);
            }
        });


    }
}
