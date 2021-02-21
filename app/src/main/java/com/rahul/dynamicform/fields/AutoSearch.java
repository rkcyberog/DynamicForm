package com.rahul.dynamicform.fields;

import android.app.Activity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.rahul.dynamicform.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AutoSearch {

    public static void addAutoSearch(Activity mActivity, JSONObject jsonObject, LinearLayout linearLayout) {

        AutoCompleteTextView autoCompleteTextView = new AutoCompleteTextView(mActivity);

        LinearLayout editTextLayout = new LinearLayout(mActivity);
        LinearLayout.LayoutParams editTextLayoutParam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (int) mActivity.getResources().getDimension(R.dimen.FourtyFive));
        editTextLayout.setLayoutParams(editTextLayoutParam);
        editTextLayout.setOrientation(LinearLayout.VERTICAL);

        editTextLayout.setId(View.generateViewId());
        linearLayout.addView(editTextLayout);
        autoCompleteTextView.setTextSize(16);
        String[] separated = new String[0];
        try {
            separated = jsonObject.getJSONObject("field_type").getString("values").split(",");
            Log.i("separate", String.valueOf(separated.length));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayList<String> stateList = new ArrayList<>();
        for (final String s : separated) {
            stateList.add(s);
            Log.i("values",s);

        }
        final ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(mActivity, R.layout.my_spinner,stateList);
        adapter1.setDropDownViewResource(R.layout.my_spinner);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter1);

        editTextLayout.setPadding(0, (int) mActivity.getResources().getDimension(R.dimen.Two), 0, 0);
        editTextLayout.setGravity(Gravity.CENTER_VERTICAL);
        editTextLayout.addView(autoCompleteTextView);

    }

}
