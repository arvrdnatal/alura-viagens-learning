package com.example.aluraviagens.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;

public class ResourcesUtil {
    @Nullable
    public static Drawable getDrawable(Context context, String drawableText) {
        Resources resources = context.getResources();
        int idDrawable = resources.getIdentifier(drawableText, "drawable", context.getPackageName());
        return ResourcesCompat.getDrawable(resources, idDrawable, null);
    }
}
