package com.example.androidgezginapp.model;

public class Place {

     int placeImage;
     String place;
     String placeDescription;

    public Place(int placeImage, String place, String placeDescription) {
        this.placeImage = placeImage;
        this.place = place;
        this.placeDescription = placeDescription;
    }

    public int getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(int placeImage) {
        this.placeImage = placeImage;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }
}
