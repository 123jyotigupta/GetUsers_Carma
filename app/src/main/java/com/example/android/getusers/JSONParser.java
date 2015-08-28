package com.example.android.getusers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.android.getusers.R;
import java.util.ArrayList;

/**
 * Created by jyoti on 8/27/15.
 */
public class JSONParser {

    public static ArrayList<User> getUsersData(JSONObject object){

        final String RESULT_ARRAY = "users";
        final String NAME = "alias";
        final String TIME_STAMP = "lastSeen";
        final String PHOTO = "photoUrl";

        ArrayList<User> users;

        try {
            JSONArray usersArray = object.getJSONArray(RESULT_ARRAY);
            users = new ArrayList<>();

            for (int i = 0 ; i < usersArray.length() ; i++){
                User user = new User();
                JSONObject obj = usersArray.getJSONObject(i);
                String name = obj.getString(NAME);
                user.setName(name);
                long time = obj.getLong(TIME_STAMP);
                user.setLastTimeStamp(time);
                String photo = obj.getString(PHOTO);
                if(!photo.equalsIgnoreCase("") || photo != null){
                    user.setPhoto(photo);
                }
                users.add(user);
            }
            return users;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

}