package ca.marshall.xlightstools;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARPropsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArFragment arFragment;

    private ModelRenderable archRenderable,
                            treeRenderable,
                            matrixRenderable,
                            starRenderable,
                            candycaneRenderable;

    ImageView arch, tree, matrix, star, candycane;

    View[] arrayView;

    Boolean objectsHidden;

    int selected = 1; // Default arch is chosen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arprops);

        // Set activity window to fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Hide the action bar
        getSupportActionBar().hide();

        // Initialize and assign variables
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Set home selected
        bottomNavigationView.setSelectedItemId(R.id.arprops);

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

        // By default, the prop window is showing, therefore they are NOT hidden by default
        objectsHidden = false;

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.arFragment);

        arch = findViewById(R.id.arch);
        tree = findViewById(R.id.tree);
        matrix = findViewById(R.id.matrix);
        star = findViewById(R.id.star);
        candycane = findViewById(R.id.candycane);

        setArrayView();
        setClickListener();
        setUpModel();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            // When the user taps on the plane, the model will instantiate
            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());
            createModel(anchorNode, selected);
        });
        initializeButtons();
    }

    private void initializeButtons() {
        Button showHidePropsButton = findViewById(R.id.show_hide_props_button);
        showHidePropsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideArrayView();
            }
        });
    }

    private void setUpModel() {
        ModelRenderable.builder()
                .setSource(this, Uri.parse("Arch.sfb"))
                .build()
                .thenAccept(renderable -> archRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unable to load arch model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this, Uri.parse("Spiral Tree.sfb"))
                .build()
                .thenAccept(renderable -> treeRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unable to load spiral tree model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this, Uri.parse("Matrix.sfb"))
                .build()
                .thenAccept(renderable -> matrixRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unable to load matrix model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this, Uri.parse("Star.sfb"))
                .build()
                .thenAccept(renderable -> starRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unable to load star model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
        ModelRenderable.builder()
                .setSource(this, Uri.parse("CandyCane.sfb"))
                .build()
                .thenAccept(renderable -> candycaneRenderable = renderable)
                .exceptionally(
                        throwable -> {
                            Toast.makeText(this, "Unable to load candy cane model", Toast.LENGTH_SHORT).show();
                            return null;
                        }
                );
    }

    private void createModel(AnchorNode anchorNode, int selected) {
        if (selected == 1) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(archRenderable);
            node.select();
        } else if (selected == 2) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(treeRenderable);
            node.select();
        } else if (selected == 3) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(matrixRenderable);
            node.select();
        } else if (selected == 4) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(starRenderable);
            node.select();
        } else if (selected == 5) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(candycaneRenderable);
            node.select();
        }
    }

    private void setClickListener() {
        for (int i = 0; i < arrayView.length; i++) {
            arrayView[i].setOnClickListener(this);
        }
    }

    private void setArrayView() {
        arrayView = new View[] {
                arch, tree, matrix, star, candycane
        };
    }

    private void hideArrayView() {
        if (objectsHidden) {
            for (View v : arrayView) {
                v.setVisibility(View.VISIBLE);
            }
            objectsHidden = false;
        } else {
            for (View v : arrayView) {
                v.setVisibility(View.GONE);
            }
            objectsHidden = true;
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.arch) {
            selected = 1;
            setBackground(view.getId());
        } else if (view.getId() == R.id.tree) {
            selected = 2;
        } else if (view.getId() == R.id.matrix) {
            selected = 3;
        } else if (view.getId() == R.id.star) {
            selected = 4;
        } else if (view.getId() == R.id.candycane) {
            selected = 5;
        }
    }

    private void setBackground(int id) {
        for (int i = 0; i < arrayView.length; i++) {
            if (arrayView[i].getId() == id)
                arrayView[i].setBackgroundColor(Color.parseColor("#80333639")); // set background for selected object
            else
                arrayView[i].setBackgroundColor(Color.TRANSPARENT);
        }
    }

}
