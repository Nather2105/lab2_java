package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.Map;

public class HangarTest {
    private Hangar hangar;

    @BeforeMethod
    public void setUp() {
        // Initialize a new hangar with a capacity of 2 airplanes
        hangar = new Hangar(2);
    }

    @Test
    public void testAddAirplane() {
        // Create airplanes
        Airplane airplane1 = new Airplane.Builder("Boeing", "747", 30000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .build();
        Airplane airplane2 = new Airplane.Builder("Airbus", "A380", 50000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .build();

        // Add airplanes to the hangar
        hangar.addAirplane(airplane1);
        hangar.addAirplane(airplane2);

        // Check if the airplanes were added successfully
        Map<Airplane, LocalDate> airplanesInfo = hangar.getInformation();
        Assert.assertEquals(airplanesInfo.size(), 2);
    }

    @Test
    public void testRemoveAirplane() {
        // Create an airplane
        Airplane airplane = new Airplane.Builder("Embraer", "E190", 20000.0)
                .setNumberOfEngines(2)
                .setJet(false)
                .build();

        // Add the airplane to the hangar
        hangar.addAirplane(airplane);

        // Remove the airplane from the hangar
        hangar.removeAirplane(airplane);

        // Check if the airplane was removed successfully
        Map<Airplane, LocalDate> airplanesInfo = hangar.getInformation();
        Assert.assertEquals(airplanesInfo.size(), 0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testAddAirplaneWhenHangarIsFull() {
        // Create airplanes
        Airplane airplane1 = new Airplane.Builder("Boeing", "777", 40000.0)
                .setNumberOfEngines(2)
                .setJet(true)
                .build();
        Airplane airplane2 = new Airplane.Builder("Airbus", "A320", 30000.0)
                .setNumberOfEngines(2)
                .setJet(false)
                .build();
        Airplane airplane3 = new Airplane.Builder("Embraer", "E170", 18000.0)
                .setNumberOfEngines(2)
                .setJet(false)
                .build();

        // Add airplanes to the hangar (hangar capacity is 2, so the third airplane should throw an exception)
        hangar.addAirplane(airplane1);
        hangar.addAirplane(airplane2);
        hangar.addAirplane(airplane3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testRemoveNonexistentAirplane() {
        // Create an airplane
        Airplane airplane = new Airplane.Builder("Boeing", "787", 35000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .build();

        // Try to remove an airplane that doesn't exist in the hangar
        hangar.removeAirplane(airplane);
    }
}
