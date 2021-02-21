package com.rahul.dynamicform.fields;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rahul.dynamicform.R;

import org.json.JSONException;
import org.json.JSONObject;

public class TextField {

    public static void getCustomView(Activity mActivity, JSONObject jsonObject, LinearLayout linearLayout) throws JSONException {
        LinearLayout headingLayout = new LinearLayout(mActivity);
        headingLayout.setOrientation(LinearLayout.VERTICAL);

        try {
            linearLayout.addView(headingLayout);
            TextView textView = new TextView(mActivity);
            try {
                textView.setText(jsonObject.getString("name"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            setTextViewAttributes(textView, mActivity);
            headingLayout.addView(textView);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void setTextViewAttributes(TextView textView, Activity mActivity) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, (int) mActivity.getResources().getDimension(R.dimen.TwentyFour), 0, (int) mActivity.getResources().getDimension(R.dimen.Four));
        textView.setTextSize(14);
        textView.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
        textView.setLayoutParams(params);
    }

}
