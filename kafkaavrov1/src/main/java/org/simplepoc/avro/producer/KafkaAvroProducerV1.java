package org.simplepoc.avro.producer;

import com.example.Customer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaAvroProducerV1 {

    public static final String SCHEMA_REGISTRY_URL = "http://127.0.0.1:8081";
    public static final String KAFKA_BOOTSTRAP_SERVER = "127.0.0.1:9092";
    public static final String TOPIC = "customer-avro";

    public static void main(String[] args) {
        KafkaProducer<String, Customer> kafkaProducer = getStringCustomerKafkaProducer();
        Customer customer = getTestCustomer();
        ProducerRecord<String, Customer> producerRecord = new ProducerRecord<String, Customer>(
                TOPIC, customer
        );

        kafkaProducer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception exception) {
                if (exception == null) {
                    System.out.println("Event send to Kafka");
                    System.out.println(recordMetadata.toString());
                } else {
                    System.out.println("Failed to send to Kafka");
                    exception.printStackTrace();
                }
            }
        });

        kafkaProducer.flush();
        kafkaProducer.close();
    }

    private static KafkaProducer<String, Customer> getStringCustomerKafkaProducer() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", KAFKA_BOOTSTRAP_SERVER);
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "9");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());

        properties.setProperty("schema.registry.url", SCHEMA_REGISTRY_URL);

        KafkaProducer<String, Customer> kafkaProducer = new KafkaProducer<String, Customer>(properties);
        return kafkaProducer;
    }

    private static Customer getTestCustomer() {
        return Customer.newBuilder()
                .setFirstName("Adriano")
                .setLastName("Goncalves")
                .setAge(35)
                .setHeight(173.4f)
                .setWeight(80.9f)
                .setAutomatedEmail(false)
                .setIsActive(true)
                .build();
    }

}