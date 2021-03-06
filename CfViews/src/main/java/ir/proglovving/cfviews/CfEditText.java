package ir.proglovving.cfviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class CfEditText extends AppCompatEditText {
    public CfEditText(Context context) {
        super(context);
    }

    public CfEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupView(attrs);
    }

    public CfEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupView(attrs);
    }

    private void setupView(AttributeSet attrs){
        TypedArray attributes = getContext().obtainStyledAttributes(attrs,R.styleable.CfViewsAttributes);
        try{
            int font = attributes.getInteger(R.styleable.CfViewsAttributes_cusfont,CTypefaceProvider.NORMAL_VAZIR_FONT);
            setTypeface(CTypefaceProvider.getTypefaceOfAttr(font,getContext()));
        }finally {
            invalidate();
            requestLayout();
            attributes.recycle();
        }
    }
}
