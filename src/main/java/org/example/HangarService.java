package org.example;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import org.example.interfaces.HangarServiceInterface;

public class HangarService implements HangarServiceInterface {
    private final Hangar hangar;

    public HangarService(Hangar hangar) {
        this.hangar = hangar;
    }

    private static class BrandComparator implements Comparator<Airplane> {
        @Override
        public int compare(Airplane airplane1, Airplane airplane2) {
            return airplane1.getBrand().compareTo(airplane2.getBrand());
        }
    }

    public List<Airplane> findAirplanesByBrand(String brand) {
        List<Airplane> foundAirplanes = new ArrayList<>();
        for (Airplane airplane : hangar.getInformation().keySet()) {
            if (airplane.getBrand().equalsIgnoreCase(brand)) {
                foundAirplanes.add(airplane);
            }
        }
        foundAirplanes.sort(new BrandComparator()); // Сортування за брендом
        return foundAirplanes;
    }

    public List<Airplane> findAirplanesByModel(String model) {
        List<Airplane> foundAirplanes = new ArrayList<>();
        for (Airplane airplane : hangar.getInformation().keySet()) {
            if (airplane.getModel().equalsIgnoreCase(model)) {
                foundAirplanes.add(airplane);
            }
        }
        foundAirplanes.sort(Comparator.comparing(Airplane::getModel)); // Сортування за моделлю
        return foundAirplanes;
    }

    public List<Airplane> findAirplanesByProductionYear(int productionYear) {
        List<Airplane> foundAirplanes = new ArrayList<>();
        for (Airplane airplane : hangar.getInformation().keySet()) {
            if (airplane.getProductionYear() == productionYear) {
                foundAirplanes.add(airplane);
            }
        }
        foundAirplanes.sort(Comparator.comparingInt(Airplane::getProductionYear)); // Сортування за роком виробництва
        return foundAirplanes;
    }

    public List<Airplane> getAirplanesAddedBetween(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        return hangar.getInformation().entrySet().stream()
                .filter(entry -> {
                    LocalDate airplaneAddDate = entry.getValue();
                    return airplaneAddDate.isEqual(startDate) || (airplaneAddDate.isAfter(startDate) && airplaneAddDate.isBefore(endDate));
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }


    @Override
    public List<Airplane> getAirplanesAddedBetweenStream(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        return hangar.getInformation().entrySet().stream()
                .filter(entry -> {
                    LocalDate airplaneAddDate = entry.getValue();
                    return airplaneAddDate.isEqual(startDate) || (airplaneAddDate.isAfter(startDate) && airplaneAddDate.isBefore(endDate));
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    @Override
    public List<Airplane> findAirplanesByProductionYearStream(int productionYear) {
        return hangar.getInformation().keySet().stream()
                .filter(airplane -> airplane.getProductionYear() == productionYear)
                .sorted(Comparator.comparingInt(Airplane::getProductionYear))
                .collect(Collectors.toList());
    }

    @Override
    public List<Airplane> findAirplanesByModelStream(String model) {
        return hangar.getInformation().keySet().stream()
                .filter(airplane -> airplane.getModel().equalsIgnoreCase(model))
                .sorted(Comparator.comparing(Airplane::getModel))
                .collect(Collectors.toList());
    }

    @Override
    public List<Airplane> findAirplanesByBrandStream(String brand) {
        return hangar.getInformation().keySet().stream()
                .filter(airplane -> airplane.getBrand().equalsIgnoreCase(brand))
                .sorted(Comparator.comparing(Airplane::getBrand))
                .collect(Collectors.toList());
    }
    public static class ModelComparator implements Comparator<Airplane> {
        @Override
        public int compare(Airplane airplane1, Airplane airplane2) {
            return airplane1.getModel().compareTo(airplane2.getModel());
        }
    }
}

