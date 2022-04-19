package com.example.aluraviagens.util;

import androidx.annotation.NonNull;

public class DaysUtil {
    @NonNull
    public static String formatToString(int days) {
        String numberOfDays = days + " dia";
        if(days != 1) return numberOfDays + "s";
        return numberOfDays;
    }
}
