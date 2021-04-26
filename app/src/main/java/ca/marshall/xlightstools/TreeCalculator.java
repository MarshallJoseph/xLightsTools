package ca.marshall.xlightstools;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TreeCalculator extends AppCompatActivity {

    EditText Height;
    EditText TopDia;
    EditText BotDia;
    EditText CircCovered;
    EditText SpaceToTop;
    EditText SpaceAfterBot;
    EditText SpaceBetween;
    EditText NumOfStrings;
    Button toReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_calculator);

        // Set activity window to fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //dont show keyboard on start
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        // Hide the action bar
        getSupportActionBar().hide();

        Height = (EditText) findViewById(R.id.editText6);
        TopDia = (EditText) findViewById(R.id.editText3);
        BotDia = (EditText) findViewById(R.id.botdiin);
        CircCovered = (EditText) findViewById(R.id.circov);
        SpaceToTop = (EditText) findViewById(R.id.sttl);
        SpaceAfterBot = (EditText) findViewById(R.id.sabl);
        SpaceBetween = (EditText) findViewById(R.id.sbl);
        NumOfStrings = (EditText) findViewById(R.id.nos);

        //Check if we can update the function once they are done entering values
        Height.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                completeFunc();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
        TopDia.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                completeFunc();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
        BotDia.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                completeFunc();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
        CircCovered.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                completeFunc();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
        SpaceToTop.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                completeFunc();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
        SpaceAfterBot.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                completeFunc();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
        SpaceBetween.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                completeFunc();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });
        NumOfStrings.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                completeFunc();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });



    }

    private void completeFunc() {
        if (isEmpty(Height)) {return;}
        if (isEmpty(TopDia)) {return;}
        if (isEmpty(BotDia)) {return;}
        if (isEmpty(CircCovered)) {return;}
        if (isEmpty(SpaceToTop)) {return;}
        if (isEmpty(SpaceAfterBot)) {return;}
        if (isEmpty(SpaceBetween)) {return;}
        if (isEmpty(NumOfStrings)) {return;}

//        TextView h = (TextView) findViewById(R.id.ResultHeightOut);
        TextView tc = (TextView) findViewById(R.id.ResultTopCircOut);
        TextView bc = (TextView) findViewById(R.id.ResultBotCircOut);
//        TextView ts = (TextView) findViewById(R.id.ResultBotSpaceOut);
//        TextView bs = (TextView) findViewById(R.id.ResultLightsPerOut);
//        TextView lps = (TextView) findViewById(R.id.ResultNumOfOut);
//        TextView nol = (TextView) findViewById(R.id.numOfLightsOut);

        System.out.println(topCircumferance());
        System.out.println(bottomCircumferance());


    }


    private double topSpacing(){

        if (!isEmpty(this.TopDia) && (!isEmpty(this.NumOfStrings)) && (!isEmpty(this.CircCovered))){
            return  (Double.parseDouble(String.valueOf(this.TopDia))* Double.parseDouble(String.valueOf(this.CircCovered))) / Double.parseDouble(String.valueOf(this.NumOfStrings));
        }


        return 0;
    }


    private double bottomSpacing(){

        if (!isEmpty(this.BotDia) && (!isEmpty(this.NumOfStrings)) && (!isEmpty(this.CircCovered))){
            return  (Double.parseDouble(String.valueOf(this.BotDia))* Double.parseDouble(String.valueOf(this.CircCovered))) / Double.parseDouble(String.valueOf(this.NumOfStrings));
        }


        return 0;
    }


    private double amps(){

        if (!isEmpty(this.BotDia) && (!isEmpty(this.NumOfStrings)) && (!isEmpty(this.CircCovered))){
            return  (Double.parseDouble(String.valueOf(this.BotDia))* Double.parseDouble(String.valueOf(this.CircCovered))) / Double.parseDouble(String.valueOf(this.NumOfStrings));
        }


        return 0;
    }


    // length of cord / - the space after bottom light - space bottom light / spacing between the lights
    //
    private double numberOfLights(){

        if (!isEmpty(this.BotDia) && (!isEmpty(this.NumOfStrings)) && (!isEmpty(this.CircCovered))){
            return  (Double.parseDouble(String.valueOf(this.BotDia))* Double.parseDouble(String.valueOf(this.CircCovered))) / Double.parseDouble(String.valueOf(this.NumOfStrings));
        }


        return 0;
    }


    private double triangularBottom(){
        double b = (Double.parseDouble(String.valueOf(this.BotDia)) - Double.parseDouble(String.valueOf(this.TopDia)))/2;
        return b;
    }

    private double eqDistance(){
        return Math.sqrt(Math.pow(Double.parseDouble(this.Height.getText().toString()),2) + Math.pow(triangularBottom(),2));
    }

    private double numLights(){
        double topPart = (eqDistance() - Double.parseDouble(this.SpaceToTop.getText().toString())) - Double.parseDouble(this.SpaceAfterBot.getText().toString());

        return topPart / Double.parseDouble(this.SpaceBetween.getText().toString());
    }



    private double topCircumferance(){
        if (!isEmpty(this.TopDia)){
            return  2 * Math.PI * (Double.parseDouble(this.TopDia.getText().toString())/2.0);

        }
        return 0;
    }

    private double bottomCircumferance(){
        if(!isEmpty(this.BotDia)){
            return 2 * Math.PI * (Double.parseDouble(this.BotDia.getText().toString())/2);
        }
        return 0;
    }


    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() <= 0;
    }
}
