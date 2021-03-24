package ca.marshall.xlightstools;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
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
import com.android.volley.toolbox.JsonRequest;
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
     btn = (Button)this.findViewById(R.id.goBtn);
        sv = this.findViewById(R.id.searchView);



        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchInput = query;
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchInput += newText;
                return true;
            }
        });


        btn.setOnClickListener((e) ->makeRestRequest(searchInput));
         this.adapter = new WebsiteAdapter(this,websiteList);
        this.lv = (ListView) this.findViewById(R.id.listView);
        assert lv != null;
        lv.setAdapter(adapter);
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

    }

    private void makeRestRequest(String searchInput) {
        assert searchInput != null;
        queue = Volley.newRequestQueue(this);

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
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject site = response.getJSONArray("results").getJSONObject(i);
                        Website website = new Website(site.getString("title"),site.getString("url"),site.getString("baseUrl"));
                        websiteList.add(website);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
