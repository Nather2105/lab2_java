package org.example.interfaces;

import org.example.Airplane;

import java.time.LocalDate;
import java.util.List;

public interface HangarServiceInterface {

    // Default
    List<Airplane> findAirplanesByBrand(String brand);

    List<Airplane> findAirplanesByModel(String model);

    List<Airplane> findAirplanesByProductionYear(int productionYear);

    List<Airplane> getAirplanesAddedBetween(LocalDate startDate, LocalDate endDate);

    // Stream API
    List<Airplane> findAirplanesByBrandStream(String brand);

    List<Airplane> findAirplanesByModelStream(String model);

    List<Airplane> findAirplanesByProductionYearStream(int productionYear);

    List<Airplane> getAirplanesAddedBetweenStream(LocalDate startDate, LocalDate endDate);
}
