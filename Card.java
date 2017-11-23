package net.knightsys.jarvis;

import android.media.Image;

/**
 * Created by root on 11/23/17.
 */

public class Card {
    static class CardType{
        static int Salutation = 0;
        static int Weather = 1;
    }
    public int Type;
    public String Details;
    public Image Pic;
    public Card(int pType)
    {
        Type = pType;
    }
}
