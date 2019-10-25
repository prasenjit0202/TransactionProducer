package com.assessment.service;

import com.assessment.producer.KafkaStringMessageProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Component
@Slf4j
public class TransactionCreationService {

    @Resource
    KafkaStringMessageProducer KafkaStringMessageProducer;
    @Value("${kafka.topic-name}")
    private String topicName;

    public void createTransaction(String fileName) {

        try (Stream<String> lines = Files.lines(Paths.get(getClass().getClassLoader()
                .getResource(fileName).toURI()));
        ) {
            long numberOfMessages = lines.map(s -> {
                KafkaStringMessageProducer.sendMessage(s, topicName);
                return 1;
            }).count();
            log.info("Number of Messages sent " + numberOfMessages);
        } catch (Exception e) {
            log.error("Exception while processing file" + e.getMessage());
            e.printStackTrace();
        }
    }
}
