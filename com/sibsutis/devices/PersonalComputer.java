package com.sibsutis.devices;

//import com.sibsutis.devices.Device;

public class PersonalComputer extends com.sibsutis.devices.Device {
    // Constructors
    public PersonalComputer(int id, int price) {
        super(id, price, null);
    }

    public PersonalComputer(int id, int price, String ip) {
        super(id, price, ip);
    }

    @Override
    public String getDeviceType() {
        return "Personal Computer";
    }
}