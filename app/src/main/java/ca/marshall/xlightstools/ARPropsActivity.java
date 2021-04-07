package ca.marshall.xlightstools;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.CamcorderProfile;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class ARPropsActivity extends AppCompatActivity implements View.OnClickListener {

    private ArFragment arFragment;
    private VideoRecorder videoRecorder;

    private ModelRenderable archRenderable,
                            treeRenderable,
                            matrixRenderable,
                            starRenderable,
                            candycaneRenderable;

    ImageView arch, tree, matrix, star, candycane;

    View[] arrayView;

    Boolean objectsHidden;

    int selected = 1; // Default arch is chosen
    int activity = 1;
    int numObjects = 0;

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

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder
                .setMessage("Switching activities will result in losing your current AR Props scene.")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (activity == 0) {
                            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        } else if (activity == 2) {
                            startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
                        } else if (activity == 3) {
                            startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                        }
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        bottomNavigationView.setSelectedItemId(R.id.arprops);
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = alertBuilder.create();
        alert.setTitle("Are you sure?");

        // Set arprops selected
        bottomNavigationView.setSelectedItemId(R.id.arprops);

        // Make sure label always shows
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        // Perform ItemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
            if (numObjects > 0)
                alert.show();
            switch (menuItem.getItemId()) {
                case R.id.home:
                    activity = 0;
                    if (numObjects == 0)
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    return true;
                case R.id.arprops:
                    activity = 1;
                    return true;
                case R.id.tools:
                    activity = 2;
                    if (numObjects == 0)
                        startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
                    return true;
                case R.id.search:
                    activity = 3;
                    if (numObjects == 0)
                        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
                    return true;
                }
            return false;
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
        // Button for showing and hiding the horizontal props view
        Button showHidePropsButton = findViewById(R.id.show_hide_props_button);
        showHidePropsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (objectsHidden) {
                    for (View v : arrayView) {
                        v.setVisibility(View.VISIBLE);
                        showHidePropsButton.setText("Hide Props");
                    }
                    objectsHidden = false;
                } else {
                    for (View v : arrayView) {
                        v.setVisibility(View.GONE);
                        showHidePropsButton.setText("Show Props");
                    }
                    objectsHidden = true;
                }
            }
        });
        // Button for recording the screen
        Button recordVideoButton = findViewById(R.id.record_button);
        recordVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (videoRecorder == null) {
                    videoRecorder = new VideoRecorder();
                    videoRecorder.setSceneView(arFragment.getArSceneView());
                    int orientation = getResources().getConfiguration().orientation;
                    videoRecorder.setVideoQuality(CamcorderProfile.QUALITY_HIGH, orientation);
                }
                boolean isRecording = videoRecorder.onToggleRecord();
                if (isRecording) {
                    recordVideoButton.setText("Stop Recording");
                    for (View v : arrayView) {
                        v.setVisibility(View.GONE);
                        showHidePropsButton.setText("Show Window");
                    }
                    objectsHidden = true;
                    Toast.makeText(view.getContext(), "Started Recording", Toast.LENGTH_SHORT).show();
                } else {
                    recordVideoButton.setText("Start Recording");
                    for (View v : arrayView) {
                        v.setVisibility(View.VISIBLE);
                        showHidePropsButton.setText("Hide Window");
                    }
                    objectsHidden = false;
                    Toast.makeText(view.getContext(), "Recording Stopped", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button clearPropsButton = findViewById(R.id.clear_props_button);
        clearPropsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ARPropsActivity.class));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check to make sure the write to external storage permission is active
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
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
            numObjects++;
        } else if (selected == 2) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(treeRenderable);
            node.select();
            numObjects++;
        } else if (selected == 3) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(matrixRenderable);
            node.select();
            numObjects++;
        } else if (selected == 4) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(starRenderable);
            node.select();
            numObjects++;
        } else if (selected == 5) {
            TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
            node.setParent(anchorNode);
            node.setRenderable(candycaneRenderable);
            node.select();
            numObjects++;
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
