package ca.marshall.xlightstools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    SearchView sv;
    String searchInput;
    Button btn;
    ListView lv;
    WebsiteAdapter adapter;
    private RequestQueue queue;
    List<Website> websiteList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set activity window to fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the action bar
        getSupportActionBar().hide();

        // Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set home selected
        bottomNavigationView.setSelectedItemId(R.id.search);

        // Make sure label always shows
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        // Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        overridePendingTransition(0,0);
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
                        return true;
                }
                return false;
            }
        });

        adapter = new WebsiteAdapter(this, websiteList);
        lv = findViewById(R.id.listView);
        lv.setAdapter(adapter);

        btn = (Button) findViewById(R.id.goBtn);

        sv = findViewById(R.id.searchView);
        sv.setFocusable(false); // make the search view NOT automatically clicked on activity start
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchInput = newText;
                return false;
            }
        });

        btn.setOnClickListener((e) -> makeRestRequest(searchInput));

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void makeRestRequest(String searchInput) {

        hideKeyboard(this);

        assert searchInput != null;
        queue = Volley.newRequestQueue(this);
        adapter.clear();

        // JSon object request
        JSONObject ob = new JSONObject();
        try {
            ob.put("title",searchInput);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, "https://mysterious-coast-36838.herokuapp.com/feed/p", ob, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("JSON","OnResponse" + response);
                    try {

                        JSONArray site = response.getJSONArray("results"); // always the first response
                        for (int i = 0; i < site.length(); i++) {
                           JSONObject siteObj = (JSONObject) site.get(i);
                           Website website = new Website(siteObj.getString("title"),siteObj.getString("url"),siteObj.getString("baseUrl"));
                           adapter.add(website);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("An Error occured");
            }
        });
        queue.add(jsonObjectRequest);
    }

}
