package com.example.android.getusers;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import com.example.android.getusers.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jyoti on 8/27/15.
 */
public class NetworkConnection extends AsyncTask<String,Void,Boolean> {

    private final String NETWORK_TAG = NetworkConnection.class.getSimpleName();
    private final Context mContext;
    private JSONObject data;
    private String responseJsonStr = null;
    private Listener listener;

    public NetworkConnection(Context c){
        mContext = c;
        listener = (Listener) mContext;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        Uri requestURL = null;
        String BASE_URL = "https://api.car.ma/v2/users/nearby?client_id=test&";
        String parameters = "&onlineSince=-1&orderByOnlineMs=-1&userId=-1&originRadius=10000&pageNum=1&pageSize=20&userFields=LAST_SEEN_TIMESTAMP%2CALIAS%2CPHOTO_URL";
        String Latitude = "originLat=" + params[0];
        String Longitude = "originLon="+ params[1];
        final String path = BASE_URL + Latitude + "&" + Longitude + parameters;
        requestURL = Uri.parse(path);
        Log.w(NETWORK_TAG, requestURL.toString());
        return retrieveData(requestURL);
    }

    private boolean retrieveData(Uri requestedURL){
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try{

            //Final URL for request
            URL url = new URL(requestedURL.toString());

            //Creation for the request of movies data
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null){
                return false; //Nothing to do.
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null){
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0){
                return false; //Has no lines. String empty.
            }

            responseJsonStr = buffer.toString();
            Log.w(NETWORK_TAG,responseJsonStr);
            return true;
        }catch (IOException e){
            Log.e(NETWORK_TAG,e.toString());
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            try {
                data = new JSONObject(responseJsonStr);
                listener.retrivedInformation(JSONParser.getUsersData(data));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public static enum Request {
        videoRequest,dataRequest,reviewsRequest
    }


}