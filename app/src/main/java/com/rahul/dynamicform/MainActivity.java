package com.rahul.dynamicform;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.rahul.dynamicform.fields.AutoSearch;
import com.rahul.dynamicform.fields.CustomDropDown;
import com.rahul.dynamicform.fields.FieldHeading;
import com.rahul.dynamicform.fields.RadioButtonField;
import com.rahul.dynamicform.fields.TextField;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    Context context;
    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        mActivity = MainActivity.this;
        linearLayout = findViewById(R.id.mainlayout);
        loadJSONFromAsset();

    }

    public void loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = context.getAssets().open("datafields.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            Constants.Questons = json;
            Log.i("Questions",Constants.Questons );
            if(!Constants.Questons.isEmpty()){
            populateViews(Constants.Questons);
            }else{
                Toast.makeText(context,"No dataFields found",Toast.LENGTH_SHORT).show();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void populateViews(String response) {
        try {
            JSONObject initObj = new JSONObject(response);
            JSONObject obj = initObj.getJSONObject("data");
            JSONArray detailsArray = obj.getJSONArray("details");
            for (int i = 0; i < detailsArray.length(); i++) {
                if (detailsArray.getJSONObject(i).getJSONObject("question").getJSONObject("field_type").get("slug").equals("text_field")) {
                    TextField.getCustomView(mActivity, detailsArray.getJSONObject(i).getJSONObject("question"), linearLayout);

                } else if (detailsArray.getJSONObject(i).getJSONObject("question").getJSONObject("field_type").get("slug").equals("custom_dropdown")) {

                    FieldHeading.getCustomView(mActivity, detailsArray.getJSONObject(i).getJSONObject("question"), linearLayout);
                    CustomDropDown.addSpinner(mActivity, detailsArray.getJSONObject(i).getJSONObject("question"), linearLayout);

                } else if (detailsArray.getJSONObject(i).getJSONObject("question").getJSONObject("field_type").get("slug").equals("radio_button")) {

                    FieldHeading.getCustomView(mActivity, detailsArray.getJSONObject(i).getJSONObject("question"), linearLayout);
                    RadioButtonField.addRadioButtons(mActivity, detailsArray.getJSONObject(i).getJSONObject("question"), linearLayout);

                }
                else if (detailsArray.getJSONObject(i).getJSONObject("question").getJSONObject("field_type").get("slug").equals("auto_search")) {

                    FieldHeading.getCustomView(mActivity, detailsArray.getJSONObject(i).getJSONObject("question"), linearLayout);
                    AutoSearch.addAutoSearch(mActivity, detailsArray.getJSONObject(i).getJSONObject("question"), linearLayout);

                }
            }

        } catch (
                JSONException e) {
            e.printStackTrace();
        }
    }
}