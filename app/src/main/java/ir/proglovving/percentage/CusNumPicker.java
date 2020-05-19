package ir.proglovving.percentage;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

public class CusNumPicker extends NumberPicker {


    public CusNumPicker(Context context) {
        super(context);
    }

    public CusNumPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CusNumPicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CusNumPicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        setTypeFace(child);
    }

    @Override
    public void addView(View child, int index) {
        super.addView(child, index);
        setTypeFace(child);
    }

    @Override
    public void addView(View child, int width, int height) {
        super.addView(child, width, height);
        setTypeFace(child);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        super.addView(child, params);
        setTypeFace(child);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        setTypeFace(child);
    }

    private void setTypeFace(View view) {
        if (view instanceof TextView) {
            ((TextView) view).setTypeface(Utilities.getAppTypeface(getContext()));
        }
    }
}
