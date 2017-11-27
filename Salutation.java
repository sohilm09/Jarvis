package net.knightsys.jarvis;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Calendar;

/**
 * Created by root on 11/23/17.
 */

public class Salutation extends Card {

    private SharedPreferences sharedPref;
    private Context context;

    public Salutation(Context context, int pType) {
        super(context, pType);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        String name = sharedPref.getString("Name", "Alpha");
        this.context = context;
        this.Details = GetSalutation() + " " + name;
    }

    private String GetSalutation() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        //TODO: Convert to resources
        if (hour < 12) {
            this.PicUri = "goodmorning";
            return context.getString(R.string.goodmorning);
        } else if (hour < 18) {
            this.PicUri = "goodafternoon";
            return context.getString(R.string.goodafternoon);
        } else {
            this.PicUri = "goodevening";
            return context.getString(R.string.goodevening);
        }
    }
}
