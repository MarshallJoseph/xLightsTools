package ca.marshall.xlightstools;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class PropsOrganizer extends AppCompatActivity {

    GridView g = (GridView) findViewById(R.id.grid);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_props_organizer);
    }
}
