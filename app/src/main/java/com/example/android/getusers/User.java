package com.example.android.getusers;

/**
 * Created by jyoti on 8/27/15.
 */
public class User {

    private String name;
    private String photo;
    private long lastTimeStamp;

    public User() {
        this.name = "";
        this.photo = "https://cloudinary-a.akamaihd.net/avego/image/upload/mmsklldbehgnsgetzozh.png";
        this.lastTimeStamp = 0l;
    }

    public User(String name, String photo, long lastTimeStamp) {
        this.name = name;
        this.photo = photo;
        this.lastTimeStamp = lastTimeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getLastTimeStamp() {
        return lastTimeStamp;
    }

    public void setLastTimeStamp(long lastTimeStamp) {
        this.lastTimeStamp = lastTimeStamp;
    }
}
