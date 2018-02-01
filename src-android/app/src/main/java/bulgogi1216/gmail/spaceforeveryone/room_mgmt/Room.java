package bulgogi1216.gmail.spaceforeveryone.room_mgmt;

import java.util.UUID;

/**
 * Created by pys on 2017. 10. 28..
 */

public class Room {

    private UUID id;
    private String title;
    private int imageId;
    private String address;
    private String money;
    private int localCode;
    private double latitude;
    private double longitude;
    private String date="";

    public Room(){}
    public Room(String title, String address, String money,int local,int image,double lat,double lng){
        id = UUID.randomUUID();
        this.title = title;
        this.money = money;
        this.address = address;
        this.localCode = local;
        this.imageId = image;
        this.latitude = lat;
        this.longitude = lng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getLocalCode() {
        return localCode;
    }

    public void setLocalCode(int localCode) {
        this.localCode = localCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
