package ir.proglovving.percentage;

import android.app.Application;
import android.graphics.Typeface;

public class MyApplication extends Application {
    private static Typeface appTypeface;
    @Override
    public void onCreate() {
        super.onCreate();
        getAppTypeface();
    }

    public Typeface getAppTypeface(){
        if(appTypeface == null)
            appTypeface = Typeface.createFromAsset(getAssets(),"fonts/Vazir.ttf");
        return appTypeface;
    }
}
