package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonSerializationFormatTest {
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testSerializationInvalidObject() {
        // Створюємо об'єкт Airplane для тестування
        Airplane airplane = new Airplane.Builder("Boeing", "747", 30000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .setProductionYear(2022)
                .setPrice(-100000000.0)
                .build();
        String serializedJson = jsonSerializer.serialize(airplane);
        // Якщо код досяг цього місця, тест не пройшов
    }

    private final JsonSerializationFormat<Airplane> jsonSerializer = new JsonSerializationFormat<>(Airplane.class);

    @Test
    public void testSerializationDeserialization() {
        // Створюємо об'єкт Airplane для тестування
        Airplane airplane = new Airplane.Builder("Boeing", "747", 30000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .setProductionYear(2022)
                .setPrice(100000000.0)
                .build();

        // Серіалізуємо об'єкт в JSON
        String serializedJson = jsonSerializer.serialize(airplane);

        // Десеріалізуємо JSON в об'єкт
        Airplane deserializedAirplane = jsonSerializer.deserialize(serializedJson);

        // Перевіряємо, що об'єкти збігаються
        Assert.assertEquals(deserializedAirplane, airplane);
    }

    @Test
    public void testWriteReadToFile() throws IOException {
        // Створюємо список уявних об'єктів літаків для серіалізації
        List<Airplane> airplanes = new ArrayList<>();

        // Додаємо об'єкти літаків для тестування
        airplanes.add(new Airplane.Builder("Boeing", "747", 30000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .setProductionYear(2022)
                .setPrice(100000000.0)
                .build());
        airplanes.add(new Airplane.Builder("Airbus", "A380", 50000.0)
                .setNumberOfEngines(4)
                .setJet(true)
                .setProductionYear(2022)
                .setPrice(150000000.0)
                .build());
        airplanes.add(new Airplane.Builder("Embraer", "E190", 20000.0)
                .setNumberOfEngines(2)
                .setJet(false)
                .setProductionYear(2021)
                .setPrice(50000000.0)
                .build());

        // Вказуємо шлях до тимчасового JSON-файлу
        String filePath = "airplanes.json";

        // Створюємо екземпляр JsonSerializationFormat для об'єктів Airplane
        JsonSerializationFormat<Airplane> jsonFormat = new JsonSerializationFormat<>(Airplane.class);

        // Записуємо список об'єктів літаків у файл
        jsonFormat.writeToFile(airplanes, filePath);

        // Зчитуємо з файлу та десеріалізуємо
        List<Airplane> readAirplanes = jsonFormat.readFromFile(filePath);

        // Порівнюємо оригінальний список і список, прочитаний з файлу
        Assert.assertEquals(airplanes, readAirplanes);
    }
}
