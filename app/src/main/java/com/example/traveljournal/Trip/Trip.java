package com.example.traveljournal.Trip;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class Trip {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "destination")
    private String destination;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "price")
    private Double price;

    @ColumnInfo(name = "startdate")
    private String startdate;

    @ColumnInfo(name = "enddate")
    private String enddate;

    @ColumnInfo(name = "rating")
    private Double rating;
/*
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    Byte [] image;

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }*/

    public Trip(String name, String destination, String type, Double price , String startdate, String enddate, Double rating) {

        this.name = name;
        this.destination = destination;
        this.type = type;
        this.price = price;
        this.startdate = startdate;
        this.enddate = enddate;
        this.rating = rating;

    }

    public String getTrip(){return this.name;}

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}