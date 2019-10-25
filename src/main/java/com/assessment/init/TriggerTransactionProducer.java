package com.assessment.init;

import com.assessment.service.TransactionCreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TriggerTransactionProducer {

    @Autowired
    TransactionCreationService transactionCreationService;

    @PostConstruct
    public void init() {
        String inputFile = "Input.txt";
        if (!inputFile.isEmpty()) {
            transactionCreationService.createTransaction(inputFile);
        }
    }
}
