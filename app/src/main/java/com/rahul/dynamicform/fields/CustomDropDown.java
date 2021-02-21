package com.rahul.dynamicform.fields;

import android.app.Activity;
import android.graphics.Typeface;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.rahul.dynamicform.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CustomDropDown {

    public static void addSpinner(Activity mActivity, JSONObject jsonObject, LinearLayout linearLayout) {

        Spinner spinner = new Spinner(mActivity);
        LinearLayout editTextLayout = new LinearLayout(mActivity);
        LinearLayout.LayoutParams editTextLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) mActivity.getResources().getDimension(R.dimen.Fourty));
        editTextLayout.setLayoutParams(editTextLayoutParam);
        editTextLayout.setOrientation(LinearLayout.VERTICAL);

            editTextLayout.setId(View.generateViewId());
            linearLayout.addView(editTextLayout);

        String[] separated = new String[0];
        try {
            separated = jsonObject.getJSONObject("field_type").getString("values").split(",");
            Log.i("separate", String.valueOf(separated.length));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> creditTypeList = new ArrayList<>();
        for (final String s : separated) {
            creditTypeList.add(s);
            Log.i("values",s);

        }
            final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mActivity, R.layout.my_spinner, creditTypeList);
            adapter1.setDropDownViewResource(R.layout.my_spinner);

            spinner.setSelection(0);
            spinner.setAdapter(adapter1);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (position > 0) {
                        String selectedItem = parent.getItemAtPosition(position).toString();

                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    String selectedItem = parent.getItemAtPosition(1).toString();
                }
            });


        editTextLayout.setPadding(0, (int) mActivity.getResources().getDimension(R.dimen.Two), 0, 0);
        editTextLayout.setGravity(Gravity.CENTER_VERTICAL);
        editTextLayout.addView(spinner);

    }

    public static ArrayList getCompanyList() {
        ArrayList<String> companyList = new ArrayList<>();
        companyList.add("Select the Company type");
        companyList.add("Private Limited");
        companyList.add("Public Limited");

        return companyList;
    }

}
