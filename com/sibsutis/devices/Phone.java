package com.sibsutis.devices;

public class Phone extends com.sibsutis.devices.Device {
    // Constructors
    public Phone(int id, int price) {
        super(id, price, null);
    }

    public Phone(int id, int price, String ip) {
        super(id, price, ip);
    }

    @Override
    public String getDeviceType() {
        return "Phone";
    }
}