package com.example.demo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import payment.protobuf.EdaMessage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    KafkaTemplate<String, byte[]> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/test/kafka")
    public void kafka_test() {

        var message = EdaMessage.ProductTags.newBuilder()
                .setProductId(1L)
                .addTags("A")
                .build();

        kafkaTemplate.send("payment_result", message.toByteArray());

        logger.info("kafka_send : {}" , message);
    }
}
