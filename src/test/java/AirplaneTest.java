package org.example;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AirplaneTest {
    private Airplane airplane;

    @BeforeClass
    public void setUp() {
        // Create an Airplane instance for testing
        airplane = (Airplane) new Airplane.Builder("Boeing", "747", 30000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .setProductionYear(2022)
                .setPrice(100000000.0)
                .build();
    }

    @Test
    public void testGetNumberOfEngines() {
        int numberOfEngines = airplane.getNumberOfEngines();
        Assert.assertEquals(numberOfEngines, 4, "Number of engines should be 4");
    }

    @Test
    public void testIsJet() {
        boolean isJet = airplane.isJet();
        Assert.assertTrue(isJet, "The airplane should be a jet");
    }

    @Test
    public void testGetBrand() {
        String brand = airplane.getBrand();
        Assert.assertEquals(brand, "Boeing", "Brand should be 'Boeing'");
    }

    @Test
    public void testGetModel() {
        String model = airplane.getModel();
        Assert.assertEquals(model, "747", "Model should be '747'");
    }

    @Test
    public void testGetWeight() {
        double weight = airplane.getWeight();
        Assert.assertEquals(weight, 30000.0, "Weight should be 30000.0");
    }

    @Test
    public void testGetProductionYear() {
        int productionYear = airplane.getProductionYear();
        Assert.assertEquals(productionYear, 2022, "Production year should be 2022");
    }

    @Test
    public void testGetPrice() {
        double price = airplane.getPrice();
        Assert.assertEquals(price, 100000000.0, "Price should be 100000000.0");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithNegativeValue() {
        new Airplane.Builder("Airbus", "A380", 50000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .setProductionYear(2022)
                .setPrice(-150000000.0) // Invalid price
                .build();
    }
}
