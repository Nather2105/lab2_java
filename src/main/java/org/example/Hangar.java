package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Hangar {
    private final Map<Airplane, LocalDate> airplaneMap;
    private final int capacity;

    public Hangar(int capacity) {
        airplaneMap = new HashMap<>();
        this.capacity = capacity;
    }

    public void addAirplane(Airplane airplane, LocalDate localDate) {
        if (airplaneMap.size() >= capacity) {
            throw new IllegalArgumentException("The hangar is full. Cannot add more airplanes.");
        }

        airplaneMap.put(airplane, localDate);
    }

    public void removeAirplane(Airplane airplane) {
        if (!airplaneMap.containsKey(airplane)) {
            throw new IllegalArgumentException("Airplane not found in the hangar.");
        }

        airplaneMap.remove(airplane);
    }

    public Map<Airplane, LocalDate> getInformation() {
        return airplaneMap;
    }
}
