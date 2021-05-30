package com.henvealf.learn.avro;

/**
 * <p>
 *
 * <p>
 *
 * @author hongliang.yin/Henvealf on 2019-06-01
 */
public class UserRaw {

    private String name;
    private int favoriteNumber;
    private String favoriteColor;

    public UserRaw(String name, int favoriteNumber, String favoriteColor) {
        this.name = name;
        this.favoriteNumber = favoriteNumber;
        this.favoriteColor = favoriteColor;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserRaw{" +
                "name='" + name + '\'' +
                ", favoriteNumber=" + favoriteNumber +
                ", favoriteColor='" + favoriteColor + '\'' +
                '}';
    }

    public void setFavoriteNumber(int favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public void setFavoriteColor(String favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public String getName() {
        return name;
    }

    public int getFavoriteNumber() {
        return favoriteNumber;
    }

    public String getFavoriteColor() {
        return favoriteColor;
    }
}
