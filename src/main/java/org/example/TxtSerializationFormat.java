package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TxtSerializationFormat<T> implements SerializationFormat<T> {

    @Override
    public String serialize(T object) {
        StringWriter writer = new StringWriter();
        try (PrintWriter out = new PrintWriter(writer)) {
            if (object instanceof Transport) {
                Transport transport = (Transport) object;
                out.println("Brand: " + transport.getBrand());
                out.println("Model: " + transport.getModel());
                out.println("Transport Weight: " + transport.getWeight());
                out.println("Production Year: " + transport.getProductionYear());
                out.println("Price: " + transport.getPrice());
            }
        }
        return writer.toString();
    }

    @Override
    public T deserialize(String data) throws IOException {
        BufferedReader reader = new BufferedReader(new StringReader(data));
        String brand = getValueFromLine(reader.readLine(), "Brand: ");
        String model = getValueFromLine(reader.readLine(), "Model: ");
        double transportWeight = Double.parseDouble(getValueFromLine(reader.readLine(), "Transport Weight: "));
        int productionYear = Integer.parseInt(getValueFromLine(reader.readLine(), "Production Year: "));
        double price = Double.parseDouble(getValueFromLine(reader.readLine(), "Price: "));

        return (T) new Transport.Builder(brand, model, transportWeight)
                .setProductionYear(productionYear)
                .setPrice(price)
                .build();
    }

    private String getValueFromLine(String line, String prefix) {
        return line.substring(prefix.length());
    }

    @Override
    public void writeToFile(List<T> objects, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            for (T object : objects) {
                String serializedData = serialize(object);
                writer.println(serializedData);
                writer.println(); // Add an empty line between objects
            }
        }
    }

    @Override
    public List<T> readFromFile(String filePath) throws IOException {
        List<T> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            StringBuilder serializedData = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    serializedData.append(line).append("\n");
                } else if (serializedData.length() > 0) {
                    T object = deserialize(serializedData.toString().trim());
                    objects.add(object);
                    serializedData.setLength(0);
                }
            }

            if (serializedData.length() > 0) {
                T object = deserialize(serializedData.toString().trim());
                objects.add(object);
            }
        }

        return objects;
    }
}
