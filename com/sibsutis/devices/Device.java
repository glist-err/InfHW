package com.sibsutis.devices;

public abstract class Device implements com.sibsutis.Printable {
    public int id;
    public int price;
    public String ip;

    // Constructor
    public Device(int id, int price, String ip) {
        this.id = id;
        this.price = price;
        this.ip = ip;
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getIp() {
        return ip;
    }

    // Print
    @Override
    public String print() {
        return "ID: " + id + ", price: " + price + ", IP: " + ip;
    }

    public abstract String getDevice();
}