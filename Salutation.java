package net.knightsys.jarvis;

import java.util.Calendar;

import android.content.Context;

/**
 * Created by root on 11/23/17.
 */

public class Salutation extends Card {

    public Salutation(int pType) {
        super(pType);
        this.Details = GetSalutation();
    }

    private String GetSalutation() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        //TODO: Convert to resources
        if (hour < 12) return "Good Morning";
        else if (hour < 18) return "Good Afternoon";
        else return "Good Evening";
    }
}
