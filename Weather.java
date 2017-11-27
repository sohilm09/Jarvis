package net.knightsys.jarvis;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by root on 11/23/17.
 */

public class Weather extends Card {

    String TAG = "Weather";
    MainActivity context;
    //TODO: Set format for temperature type
    private String ptempformat;
    private data pdata;
    private FusedLocationProviderClient mFusedLocationClient;
    private String units = "imperial";
    private SharedPreferences sharedPref;

    public Weather(MainActivity pcontext, int pType) {
        super(pcontext, CardType.Weather);
        context = pcontext;
        pdata = new data();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);

        boolean GPS = sharedPref.getBoolean("GPS", true);
        units = sharedPref.getString("UnitType", "Imperial");

        if (units.equals("Imperial")) ptempformat = Character.toString((char) 8457);
        else
            ptempformat = Character.toString((char) 8451);

        if (GPS == true) {
            mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 123);
            }
            mFusedLocationClient.getLastLocation().addOnSuccessListener(context, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        // Logic to handle location object
                        double latitude = location.getLatitude();
                        double longitude = location.getLongitude();
                        GetWeather("lat=" + latitude + "&lon=" + longitude + "&units=" + units);
                    } else {
                        String City = sharedPref.getString("City", "Washington");
                        GetWeather("q=" + City + "&units=" + units);
                    }

                }
            });
        } else {
            String City = sharedPref.getString("City", "Washington");
            GetWeather("q=" + City + "&units=" + units);
        }

    }

    private void GetWeather(String Location) {
        //TODO: Set Weather ICON
        String weatherurl = context.getResources().getString(R.string.openweatherapiurl) + "?" + Location + "&appid=" + context.getResources().getString(R.string.openweatherapikey);
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, weatherurl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    pdata.name = response.getString("name");
                    pdata.description = response.getJSONArray("weather").getJSONObject(0).getString("description");
                    pdata.icon = response.getJSONArray("weather").getJSONObject(0).getString("icon") + ".png";
                    pdata.temp = response.getJSONObject("main").getDouble("temp");
                    pdata.temp_min = response.getJSONObject("main").getDouble("temp_min");
                    pdata.temp_max = response.getJSONObject("main").getDouble("temp_max");

                    Details = pdata.name + " - " + pdata.description + "\r\n" + "Current:" + pdata.temp + ptempformat + " Hi:" + pdata.temp_max + ptempformat + " Low:" + pdata.temp_min + ptempformat;
                    PicUri = context.getString(R.string.openweatherimgurl) + pdata.icon;
                    //Glide.with(context).load(context.getString(R.string.openweatherimgurl) + pdata.icon).placeholder(R.drawable.na).into(Pic);
                    context.DataChanged();

                } catch (JSONException e) {
                    Log.e(TAG, e.toString());
                }
                Log.i(TAG, response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i(TAG, error.toString());
            }
        });
        queue.add(jsObjRequest);
    }

    class data {
        public int lon;
        public int lat;
        public String main;
        public String description;
        public double humidity;
        public double pressure;
        public double temp;
        public double temp_min;
        public double temp_max;
        public double windspeed;
        public double winddir;
        public int clouds;
        public String name;
        public String icon;
    }
}
