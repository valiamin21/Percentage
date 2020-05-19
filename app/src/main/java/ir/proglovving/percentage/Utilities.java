package ir.proglovving.percentage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Utilities {

    public static void applyFontForAViewGroup(ViewGroup viewGroup, Activity context) {
        View view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            view = viewGroup.getChildAt(i);
            if (view instanceof TextView) {
                ((TextView) view).setTypeface(getAppTypeface(context));
            } else if (view instanceof ViewGroup) {
                applyFontForAViewGroup((ViewGroup) view, context);
            }
        }
    }

    public static Typeface getAppTypeface(Context context){
        return ((MyApplication)context.getApplicationContext()).getAppTypeface();
    }
}
