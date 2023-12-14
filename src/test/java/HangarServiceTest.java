import org.example.Airplane;
import org.example.Hangar;
import org.example.interfaces.HangarServiceInterface;
import org.example.HangarService;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.time.LocalDate;
import java.util.*;

public class HangarServiceTest {
    private HangarService hangarService;

    @BeforeMethod
    public void beforeMethod() {
        Hangar hangar = new Hangar(10);
        hangarService = new HangarService(hangar);

        Airplane airplane1 = (Airplane) new Airplane.Builder("Boeing", "747", 3000)
                .setProductionYear(2015)
                .setPrice(250000000)
                .build();

        Airplane airplane2 = (Airplane) new Airplane.Builder("Airbus", "A380", 3200)
                .setProductionYear(2018)
                .setPrice(300000000)
                .build();

        Airplane airplane3 = (Airplane) new Airplane.Builder("Embraer", "E190", 1200)
                .setProductionYear(2022)
                .setPrice(50000000)
                .build();

        hangar.addAirplane(airplane1, LocalDate.of(2012, 3, 1));
        hangar.addAirplane(airplane2, LocalDate.of(2023, 3, 1));
        hangar.addAirplane(airplane3, LocalDate.of(2023, 6, 13));
    }

    @Test
    public void testFindAirplanesByBrand() {
        List<Airplane> foundAirplanesByBrand = hangarService.findAirplanesByBrand("Boeing");
        Assert.assertEquals(foundAirplanesByBrand.size(), 1);
    }

    @Test
    public void testFindAirplanesByModel() {
        List<Airplane> foundAirplanesByModel = hangarService.findAirplanesByModel("747");
        Assert.assertEquals(foundAirplanesByModel.size(), 1);
    }

    @Test
    public void testFindAirplanesByProductionYear() {
        List<Airplane> foundAirplanesByProductionYear = hangarService.findAirplanesByProductionYear(2018);
        Assert.assertEquals(foundAirplanesByProductionYear.size(), 1);
    }

    @Test
    public void testGetAirplanesAddedBetween() {
        LocalDate startDate = LocalDate.of(2023, 1, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);
        List<Airplane> foundAirplanesAddedBetween = hangarService.getAirplanesAddedBetween(startDate, endDate);
        Assert.assertEquals(foundAirplanesAddedBetween.size(), 2);
    }
}
