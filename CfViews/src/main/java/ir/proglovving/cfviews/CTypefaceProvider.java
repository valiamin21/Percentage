package ir.proglovving.cfviews;

import android.content.Context;
import android.graphics.Typeface;

public class CTypefaceProvider {
    public static final int NORMAL_VAZIR_FONT = 0;
    public static final int LIGHT_VAZIR_FONT = 1;
    public static final int BOLD_VAZIR_FONT = 2;

    private static Typeface normalVazir;
    private static Typeface lightVazir;
    private static Typeface boldVazir;

    public static Typeface getNormalVazir(Context context) {
        if (normalVazir == null) {
            normalVazir = Typeface.createFromAsset(context.getAssets(), "fonts/Vazir.ttf");
        }
        return normalVazir;
    }

    public static Typeface getLightVazir(Context context) {
        if (lightVazir == null) {
            lightVazir = Typeface.createFromAsset(context.getAssets(), "fonts/Vazir-Light.ttf");
        }
        return lightVazir;
    }

    public static Typeface getBoldVazir(Context context) {
        if (boldVazir == null) {
            boldVazir = Typeface.createFromAsset(context.getAssets(), "fonts/Vazir-Bold.ttf");
        }
        return boldVazir;
    }

    public static Typeface getTypefaceOfAttr(int cusfontAttr, Context context) {
        switch (cusfontAttr) {
            case CTypefaceProvider.NORMAL_VAZIR_FONT:
                return getNormalVazir(context);
            case CTypefaceProvider.LIGHT_VAZIR_FONT:
                return getLightVazir(context);
            case CTypefaceProvider.BOLD_VAZIR_FONT:
                return getBoldVazir(context);
        }
        return null;
    }
}
