package ir.proglovving.percentage.custom_views;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

import ir.proglovving.percentage.Utilities;

public class CusEditText extends AppCompatEditText {
    public CusEditText(Context context) {
        super(context);
        setTypeface();
    }

    public CusEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface();
    }

    public CusEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface();
    }

    private void setTypeface(){
        setTypeface(Utilities.getAppTypeface(getContext()));
    }
}
