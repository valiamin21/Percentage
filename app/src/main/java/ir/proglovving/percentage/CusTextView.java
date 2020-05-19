package ir.proglovving.percentage;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CusTextView extends AppCompatTextView {
    public CusTextView(Context context) {
        super(context);
        setTypeface();
    }

    public CusTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface();
    }

    public CusTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface();
    }

    private void setTypeface(){
        setTypeface(Utilities.getAppTypeface(getContext()));
    }
}
