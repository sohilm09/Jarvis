package net.knightsys.jarvis;

import android.content.Context;

import java.util.Calendar;

/**
 * Created by root on 11/23/17.
 */

public class Salutation extends Card {

    public Salutation(Context context, int pType) {
        super(context, pType);
        this.Details = GetSalutation();
    }

    private String GetSalutation() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        //TODO: Convert to resources
        if (hour < 12) {
            this.PicUri = "goodmorning";
            return "Good Morning";
        } else if (hour < 18) {
            this.PicUri = "goodafternoon";
            return "Good Afternoon";
        } else {
            this.PicUri = "goodevening";
            return "Good Evening";
        }
    }
}
