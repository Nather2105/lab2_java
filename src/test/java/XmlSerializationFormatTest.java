
package org.example;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlSerializationFormatTest {
    @Test
    public void testSerializationDeserialization() throws IOException {
        XmlSerialization<Transport> xmlSerializer = new XmlSerialization<>(Transport.class);

        // Create an Airplane object for testing
        Transport transport = new Transport.Builder("Boeing", "747", 30000.0)
                .setProductionYear(2022)
                .setPrice(100000000.0)
                .build();

        // Serialize the object to XML
        String serializedXml = xmlSerializer.serialize(transport);

        // Deserialize XML to an Airplane object
        Transport deserializedAirplane = xmlSerializer.deserialize(serializedXml);

        // Check that the objects match
        Assert.assertEquals(deserializedAirplane, transport);
    }

    @Test
    public void testWriteReadToFile() throws IOException {
        // Create a list of imaginary airplane objects for serialization
        List<Transport> airplanes = new ArrayList<>();

        // Add examples of airplane objects
        airplanes.add(new Transport.Builder("Boeing", "747", 30000.0)
                .setProductionYear(2022)
                .setPrice(100000000.0)
                .build());
        airplanes.add(new Transport.Builder("Airbus", "A380", 50000.0)
                .setProductionYear(2022)
                .setPrice(150000000.0)
                .build());

        // Specify the path to the temporary XML file
        String filePath = "airplanes.xml";

        // Create an instance of XmlSerialization for Airplane objects
        XmlSerialization<Transport> xmlFormat = new XmlSerialization<>(Transport.class);

        // Write the list of airplane objects to a file
        xmlFormat.writeToFile(airplanes, filePath);

        // Read from the file and deserialize
        List<Transport> readAirplanes = xmlFormat.readFromFile(filePath);

        // Compare the original list and the list read from the file
        Assert.assertEquals(airplanes, readAirplanes);
    }
}
