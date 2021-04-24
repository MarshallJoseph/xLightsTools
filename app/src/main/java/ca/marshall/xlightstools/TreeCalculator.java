package ca.marshall.xlightstools;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;

public class TreeCalculator extends AppCompatActivity {

    EditText Height = (EditText) findViewById(R.id.editText6);
    EditText TopDia = (EditText) findViewById(R.id.editText3);
    EditText BotDia = (EditText) findViewById(R.id.botdiin);
    EditText CircCovered = (EditText) findViewById(R.id.circov);
    EditText SpaceToTop = (EditText) findViewById(R.id.sttl);
    EditText SpaceAfterBot = (EditText) findViewById(R.id.sabl);
    EditText SpaceBetween = (EditText) findViewById(R.id.sbl);
    EditText NumOfStrings = (EditText) findViewById(R.id.nos);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_calculator);



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


    //    myTextBox.addTextChangedListener(new TextWatcher() {

    //        public void afterTextChanged(Editable s) {
    //        }

    //        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    //        }
    //        public void onTextChanged(CharSequence s, int start, int before, int count) {
    //            TextView myOutputBox = (TextView) findViewById(R.id.myOutputBox);
    //            myOutputBox.setText(s);
    //        }
    //    });

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

        TextView h = (TextView) findViewById(R.id.ResultHeightOut);
        TextView tc = (TextView) findViewById(R.id.ResultTopCircOut);
        TextView bc = (TextView) findViewById(R.id.ResultBotCircOut);
        TextView ts = (TextView) findViewById(R.id.ResultBotSpaceOut);
        TextView bs = (TextView) findViewById(R.id.ResultLightsPerOut);
        TextView lps = (TextView) findViewById(R.id.ResultNumOfOut);
        TextView nol = (TextView) findViewById(R.id.numOfLightsOut);



    }

    private boolean isEmpty(EditText etText) {
        if (etText.getText().toString().trim().length() > 0)
            return false;

        return true;
    }
}
