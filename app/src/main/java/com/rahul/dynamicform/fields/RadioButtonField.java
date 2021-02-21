package com.rahul.dynamicform.fields;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rahul.dynamicform.R;

import org.json.JSONException;
import org.json.JSONObject;

public class RadioButtonField {

    public static void addRadioButtons(Activity mActivity, JSONObject jsonObject, LinearLayout linearLayout) {
        RadioGroup radioGroup = new RadioGroup(mActivity);
        radioGroup.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(radioGroup);
        String[] separated = new String[0];
        try {
            separated = jsonObject.getJSONObject("field_type").getString("values").split(",");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (final String s : separated) {
            RadioButton radioButton = new RadioButton(mActivity);

            radioButton.setText(s.trim());

            radioGroup.addView(radioButton);
            setRadioButtonAttributes(radioButton, mActivity);
        }
    }

    private static void setRadioButtonAttributes(RadioButton radioButton, Activity mActivity) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins((int) mActivity.getResources().getDimension(R.dimen.Two),
                (int) mActivity.getResources().getDimension(R.dimen.Two),
                (int) mActivity.getResources().getDimension(R.dimen.Two),
                (int) mActivity.getResources().getDimension(R.dimen.Two));

        radioButton.setLayoutParams(params);
    }
}
