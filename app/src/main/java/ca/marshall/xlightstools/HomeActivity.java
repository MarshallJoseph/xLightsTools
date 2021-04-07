package ca.marshall.xlightstools;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set activity window to fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the action bar
        getSupportActionBar().hide();

        // Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        // Make sure label always shows
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        // Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.arprops:
                        startActivity(new Intent(getApplicationContext(), ARPropsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.tools:
                        startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        // Assign on click listeners to each of the home page buttons
        assignHomeButtons();

        // Assign clickable hyperlinks to each website on the home page
        assignClickableLinks();


    }

    private void assignHomeButtons() {
        // Initialize and assign on click listener to props button on home page
        Button propsButton = findViewById(R.id.props_button);
        propsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ARPropsActivity.class));
                overridePendingTransition(0,0);
            }
        });

        // Initialize and assign on click listener to tools button on home page
        Button toolsButton = findViewById(R.id.tools_button);
        toolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
                overridePendingTransition(0,0);
            }
        });

        // Initialize and assign on click listener to search button on home page
        Button searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }

    private void assignClickableLinks() {
        // Official xLights Website Hyperlink
        TextView xLightsWebsiteText = findViewById(R.id.xlights_website_text);
        xLightsWebsiteText.setMovementMethod(LinkMovementMethod.getInstance());

        // Holiday Sequences Hyperlink
        TextView holidaySequencesText = findViewById(R.id.holiday_sequences_text);
        holidaySequencesText.setMovementMethod(LinkMovementMethod.getInstance());

        // Zoom Meeting Hyperlink
        TextView zoomMeetingText = findViewById(R.id.zoom_meeting_text);
        zoomMeetingText.setMovementMethod(LinkMovementMethod.getInstance());

        // Official xLights Support Group Hyperlink
        TextView officialxLightsText = findViewById(R.id.official_xlights_group_text);
        officialxLightsText.setMovementMethod(LinkMovementMethod.getInstance());

        // 3D Printing Enthusiasts Group Hyperlink
        TextView printingEnthusiastsText = findViewById(R.id._3D_printing_enthusiasts_text);
        printingEnthusiastsText.setMovementMethod(LinkMovementMethod.getInstance());

        // European Lighting Fanatics Group Hyperlink
        TextView europeanLightingFansText = findViewById(R.id.european_lighting_fanatics_text);
        europeanLightingFansText.setMovementMethod(LinkMovementMethod.getInstance());

        // Extreme Lighting Fanatics Group Hyperlink
        TextView extremeLightingFansText = findViewById(R.id.extreme_lighting_fanatics_text);
        extremeLightingFansText.setMovementMethod(LinkMovementMethod.getInstance());

        // Holiday Think Tank Group Hyperlink
        TextView holidayThinkTankText = findViewById(R.id.holiday_think_tank_text);
        holidayThinkTankText.setMovementMethod(LinkMovementMethod.getInstance());

        // Holiday Light Show Vendors Group Hyperlink
        TextView holidayVendorsText = findViewById(R.id.holiday_vendors_text);
        holidayVendorsText.setMovementMethod(LinkMovementMethod.getInstance());

        // xLights Singing Faces 3 Group Hyperlink
        TextView singingFacesText = findViewById(R.id.singing_faces_text);
        singingFacesText.setMovementMethod(LinkMovementMethod.getInstance());

        // Southern California Enthusiasts Group Hyperlink
        TextView southernCaliforniaText = findViewById(R.id.southern_california_enthusiasts_text);
        southernCaliforniaText.setMovementMethod(LinkMovementMethod.getInstance());

        // Share Your Holiday Ideas Group Hyperlink
        TextView shareYourHolidayIdeasText = findViewById(R.id.share_your_holiday_ideas_text);
        shareYourHolidayIdeasText.setMovementMethod(LinkMovementMethod.getInstance());

        // Pixel Pro University Group Hyperlink
        TextView pixelProUniversityText = findViewById(R.id.pixel_pro_university_text);
        pixelProUniversityText.setMovementMethod(LinkMovementMethod.getInstance());

        // Pixel 2 Things Group Hyperlink
        TextView pixel2thingsText = findViewById(R.id.pixel_2_things_text);
        pixel2thingsText.setMovementMethod(LinkMovementMethod.getInstance());

        // Light O Rama Group Hyperlink
        TextView lightORamaText = findViewById(R.id.light_o_rama_text);
        lightORamaText.setMovementMethod(LinkMovementMethod.getInstance());

        // Pixel Pros Page Hyperlink
        TextView pixelProsText = findViewById(R.id.pixel_pro_displays_text);
        pixelProsText.setMovementMethod(LinkMovementMethod.getInstance());

        // Jdeation Designer Page Hyperlink
        TextView JdeationDesignerText = findViewById(R.id.jdeation_designer_text);
        JdeationDesignerText.setMovementMethod(LinkMovementMethod.getInstance());

        // Holiday Sights And Sounds Page Hyperlink
        TextView holidaySightsSoundsText = findViewById(R.id.holiday_sights_sounds_text);
        holidaySightsSoundsText.setMovementMethod(LinkMovementMethod.getInstance());

    }

}