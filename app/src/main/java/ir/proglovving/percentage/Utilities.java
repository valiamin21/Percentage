package ir.proglovving.percentage;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;

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

    public static void showErrorToast(Context context, String message, int length){
        Toast appToast = new Toast(context);

        appToast.setView(LayoutInflater.from(context).inflate(R.layout.layout_error_toast, null));
        TextView appToastTextView = appToast.getView().findViewById(R.id.txt_message);
        appToast.setDuration(length);
        appToastTextView.setText(message);
            appToastTextView.setBackgroundColor(ContextCompat.getColor(context, R.color.error_toast_background));

        appToast.show();
    }

    public static void showErrorToast(Context context, @StringRes int message, int length) {
        showErrorToast(context,context.getString(message),length);
    }


}
