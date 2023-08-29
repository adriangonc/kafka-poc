package org.simplepoc.avro.consumer;

import com.example.Customer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.simplepoc.avro.utils.Constraints;

import java.util.Collections;
import java.util.Properties;

public class KafkaConsumerV1 {

    public static void main(String[] args) {
        Properties properties = getProperties();

        KafkaConsumer<String, Customer> consumer = new KafkaConsumer<String, Customer>(properties);

        consumer.subscribe(Collections.singletonList(Constraints.TOPIC));

        System.out.println("Wainting for data...");

        while (true) {
            ConsumerRecords<String, Customer> records = consumer.poll(1000);
            for (ConsumerRecord<String, Customer> record : records){
                Customer customer = record.value();
                System.out.println(customer);
            }
            consumer.commitSync();
        }

    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", Constraints.KAFKA_BOOTSTRAP_SERVER);
        properties.setProperty("group.id", "test-avro-consumer");
        properties.setProperty("enable.auto.commit", "false");
        properties.setProperty("auto.offset.reset", "earliest");

        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", KafkaAvroDeserializer.class.getName());
        properties.setProperty("schema.registry.url", Constraints.SCHEMA_REGISTRY_URL);
        properties.setProperty("specific.avro.reader", "true");
        return properties;
    }

}
