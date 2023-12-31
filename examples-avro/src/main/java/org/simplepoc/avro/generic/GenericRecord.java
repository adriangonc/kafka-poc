package org.simplepoc.avro.generic;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.io.IOException;

public class GenericRecord {
    public static void main(String[] args) {
        //define schema
        Schema.Parser parser =  new Schema.Parser();
        Schema schema = parser.parse("{\n" +
                "     \"type\": \"record\",\n" +
                "     \"namespace\": \"com.example\",\n" +
                "     \"name\": \"Customer\",\n" +
                "     \"doc\": \"Avro Schema for our Customer\",     \n" +
                "     \"fields\": [\n" +
                "       { \"name\": \"first_name\", \"type\": \"string\", \"doc\": \"First Name of Customer\" },\n" +
                "       { \"name\": \"last_name\", \"type\": \"string\", \"doc\": \"Last Name of Customer\" },\n" +
                "       { \"name\": \"age\", \"type\": \"int\", \"doc\": \"Age at the time of registration\" },\n" +
                "       { \"name\": \"height\", \"type\": \"float\", \"doc\": \"Height at the time of registration in cm\" },\n" +
                "       { \"name\": \"weight\", \"type\": \"float\", \"doc\": \"Weight at the time of registration in kg\" },\n" +
                "       { \"name\": \"automated_email\", \"type\": \"boolean\", \"default\": true, \"doc\": \"Field indicating if the user is enrolled in marketing emails\" }\n" +
                "     ]\n" +
                "}");

        //create a generic record
        GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema);
        customerBuilder.set("first_name", "Adriano");
        customerBuilder.set("last_name", "Gonc");
        customerBuilder.set("age", 35);
        customerBuilder.set("height", 173f);
        customerBuilder.set("weight", 79.5f);
        customerBuilder.set("automated_email", false);
        GenericData.Record customer = customerBuilder.build();

        System.out.println(customer);

        //write that generic record to a file
        final DatumWriter<org.apache.avro.generic.GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        try (DataFileWriter<org.apache.avro.generic.GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-generic.avro"));
            dataFileWriter.append(customer);
            System.out.println("Written customer-generic.avro");
            dataFileWriter.close();
        } catch (IOException e) {
            System.out.println("Couldn't write file");
            e.printStackTrace();
        }

        //read a generic record from a file
        final File file = new File("customer-generic.avro");
        final DatumReader<org.apache.avro.generic.GenericRecord> datumReader = new GenericDatumReader<>();
        org.apache.avro.generic.GenericRecord customerRead;
        try (DataFileReader<org.apache.avro.generic.GenericRecord> dataFileReader = new DataFileReader<>(file, datumReader)) {
            customerRead = dataFileReader.next();
            System.out.println("Successfully read avro file");
            System.out.println(customerRead.toString());

            // get the data from the generic record
            System.out.println("First name: " + customerRead.get("first_name"));

            // read a non existent field
            //System.out.println("Non existent field: " + customerRead.get("not_here"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //TODO interpret as a generic record
    }

}
