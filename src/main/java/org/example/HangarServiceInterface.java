package org.example;

import java.time.LocalDate;
import java.util.List;

public interface HangarServiceInterface {
    List<Airplane> getAirplanesAddedBetweenStream(LocalDate startDate, LocalDate endDate);

    List<Airplane> findAirplanesByBrandStream(String brand);

    List<Airplane> findAirplanesByProductionYearStream(int productionYear);

    List<Airplane> findAirplanesByModelStream(String model);
}
