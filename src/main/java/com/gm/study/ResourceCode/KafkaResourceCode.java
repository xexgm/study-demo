package com.gm.study.ResourceCode;

import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

/**
 * @Author: xexgm
 */
public class KafkaResourceCode {
    public static void main(String[] args) {
        Properties props = new Properties();

        KafkaConsumer<Object, Object> consumer = new KafkaConsumer<>(props);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        //producer.send();
    }
}
