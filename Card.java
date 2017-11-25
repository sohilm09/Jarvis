package net.knightsys.jarvis;

import android.content.Context;

/**
 * Created by root on 11/23/17.
 */

public class Card {
    public int Type;
    public String Details;
    public String PicUri;
    public Context context;

    public Card(Context pcontext, int pType) {
        context = pcontext;
        Type = pType;
    }

    static class CardType {
        static int Salutation = 0;
        static int Weather = 1;
    }
}
