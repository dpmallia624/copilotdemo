package com.debi.copilot.copilotdemo.consumers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.debi.copilot.copilotdemo.requests.PatientRequest;

@Component
public class PatientListener {
    
    private static final Logger LOGGER= LoggerFactory.getLogger(PatientListener.class);


    @KafkaListener(topics = "${kafka.listener.topic}",
                    groupId = "${kafka.listener.group}",
                    concurrency ="${kafka.listener.concurrency}")
    public void listen(@Payload PatientRequest patient, 
                        @Header(KafkaHeaders.OFFSET) Long offset,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                        Acknowledgment acknowledgment){
                           
                             LOGGER.info("[partition={},offset={}] Starting: {}", partition, offset, patient);
                        }
    
}
