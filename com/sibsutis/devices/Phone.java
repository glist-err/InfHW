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

    @Override
    public boolean equals(Objects obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Device device = (Device) obj;

        return id == device.id;
    }

    @Override
    public int HashCode() {
        return Objects.hash(id, price, ip);
    }
}