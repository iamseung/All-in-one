package com.example.demo.service;

import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import payment.protobuf.EdaMessage;

@Component
public class EventListener {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @KafkaListener(topics = "payment_result")
    public void consumePaymentResult(byte[] message) throws Exception {
        var object = EdaMessage.ProductTags.parseFrom(message);
        logger.info("message consumed : {}" , object);
    }
}
