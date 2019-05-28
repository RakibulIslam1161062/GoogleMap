package com.example.googlemap;

public class ListItem {


    private String busName;
    private String adminName;
    private String adminDesig;
    private String message;

    public ListItem(String busName, String adminName, String adminDesig, String message) {
        this.busName = busName;
        this.adminName = adminName;
        this.adminDesig = adminDesig;
        this.message = message;
    }

    public String getBusName() {
        return busName;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminDesig() {
        return adminDesig;
    }

    public String getMessage() {
        return message;
    }
}
