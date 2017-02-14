package com.sadique.converter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.os.Bundle;;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button btConvert;
    EditText data;
    TextView view_result,viewAllResult;
    RadioButton radioButton;
    RadioGroup radioGroup;
    DecimalFormat decFormat = new DecimalFormat("#.#");      //decimal formatting for 1 decimal place
    String arrow = new String(Character.toChars(0x2794));   //unicode for arrow
    String cel = new String(Character.toChars(0x2103));     // unicode for Celsius
    String fah = new String(Character.toChars(0x2109));     //unicode for Fahrenheit


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = (EditText) findViewById( R.id.conv_id );
        view_result = (EditText) findViewById(R.id.conview_id);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        viewAllResult = (TextView) findViewById(R.id.Hist_Id);
        viewAllResult.setMovementMethod(new ScrollingMovementMethod());
        RadioButton b = (RadioButton) findViewById(R.id.FtoC);
        b.setChecked(true);
        data.setText("");

    }
    private double tempCal( int radioButton )
    {
        Context context = getApplicationContext();
        CharSequence text = "Please Select Celsius to Fahrenheit OR Fahrenheit to Celsius";
        CharSequence text1 = "Please enter data in Input Text";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText( context, text, duration );
        Toast toast1 = Toast.makeText(context, text1, duration );

        double result;
        String fResult;

        if ( radioButton == R.id.CtoF )
        {
            if ( data.getText().toString().equals("") ) {
                toast1.show();
                return 0;
            }
            result = ((Double.parseDouble(data.getText().toString())) * 9.0/5.0) + 32.0;
            fResult = decFormat.format( result );
            CharSequence text2 = viewAllResult.getText();
            //viewAllResult.setText("C to F: "+ Double.parseDouble(data.getText().toString())+" -> "+fResult+"\n");
            viewAllResult.setText(" C to F:   "+ Double.parseDouble(data.getText().toString())+cel+"   "+arrow+"   "+fResult+fah+"\n");
            viewAllResult.append(text2);

        }
        else if ( radioButton == R.id.FtoC ){
            if( data.getText().toString().equals("") ) {
                toast1.show();
                return 0;
            }
            result = ((Double.parseDouble(data.getText().toString())) - 32.0)* 5.0/9.0;
            fResult = decFormat.format( result );
            CharSequence text2 = viewAllResult.getText();
            //viewAllResult.setText("F to C: "+ Double.parseDouble(data.getText().toString())+" -> "+fResult+"\n");
            viewAllResult.setText(" F to C:   "+ Double.parseDouble(data.getText().toString())+fah+"   "+arrow+"   "+fResult+cel+"\n");

            viewAllResult.append(text2);

        }
        else{
            toast.show();
            return 0;
        }
        String fresult = decFormat.format(result);
        view_result.setText(fresult);
        return result;
    }
    public void onConvertbutton(View view) {
        int radButtonID = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(radButtonID);
        double result;
        result=tempCal(radButtonID);


    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewAllResult.setText(savedInstanceState.getString("HISTORY"));
        // Read values from the "savedInstanceState"-object and put them in your textview
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("HISTORY", viewAllResult.getText().toString());
        // Call super last
        super.onSaveInstanceState(outState);
        // Save the values you need from your textview into "outState"-objec
    }
}
