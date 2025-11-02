package com.sibsutis.devices;

//import com.sibsutis.devices.Device;
import java.util.Objects;

public class PersonalComputer extends com.sibsutis.devices.Device {
    // Constructors
    public PersonalComputer(int id, int price) {
        super(id, price, null);
        // super для инициализации из род. класса
    }

    public PersonalComputer(int id, int price, String ip) {
        super(id, price, ip);
    }

    @Override
    public String getDeviceType() {
        return "Personal Computer";
    }

    @Override
    public boolean equals(Objects obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        PersonalComputer device = (PersonalComputer) obj;

        return id == device.id;
    }

    @Override
    public int HashCode() {
        return Objects.hash(id, price, ip);
    }
}