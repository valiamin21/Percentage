package ir.proglovving.cfviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class CfTextView extends AppCompatTextView {
    public CfTextView(Context context) {
        super(context);
    }

    public CfTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupViews(attrs);
    }

    public CfTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupViews(attrs);
    }

    private void setupViews(AttributeSet attrs) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.CfViewsAttributes);
        try {
            int font = attributes.getInteger(R.styleable.CfViewsAttributes_cusfont, CTypefaceProvider.NORMAL_VAZIR_FONT);
            setTypeface(CTypefaceProvider.getTypefaceOfAttr(font, getContext()));
        } finally {
            invalidate();
            requestLayout();
            attributes.recycle();
        }
    }
}
