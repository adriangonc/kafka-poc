package org.simplepoc.avro.producer;

import com.example.Customer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.simplepoc.avro.utils.Constraints;

import java.util.Properties;

public class KafkaAvroProducerV1 {

    public static void main(String[] args) {
        KafkaProducer<String, Customer> kafkaProducer = getStringCustomerKafkaProducer();
        Customer customer = getTestCustomer();
        ProducerRecord<String, Customer> producerRecord = new ProducerRecord<String, Customer>(
                Constraints.TOPIC, customer
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
        properties.setProperty("bootstrap.servers", Constraints.KAFKA_BOOTSTRAP_SERVER);
        properties.setProperty("acks", "1");
        properties.setProperty("retries", "9");

        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", KafkaAvroSerializer.class.getName());

        properties.setProperty("schema.registry.url", Constraints.SCHEMA_REGISTRY_URL);

        KafkaProducer<String, Customer> kafkaProducer = new KafkaProducer<String, Customer>(properties);
        return kafkaProducer;
    }

    private static Customer getTestCustomer() {
        return Customer.newBuilder()
                .setFirstName("Sara")
                .setLastName("Goncalves")
                .setAge(36)
                .setHeight(165.0f)
                .setWeight(80.9f)
                .setAutomatedEmail(false)
                .setIsActive(false)
                .build();
    }

}
