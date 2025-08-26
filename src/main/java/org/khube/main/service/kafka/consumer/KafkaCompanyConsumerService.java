package org.khube.main.service.kafka.consumer;

import org.khube.main.dto.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class KafkaCompanyConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCompanyConsumerService.class);

    public Consumer<EmployeeDto> consumeMessage() {
        return employeeDto -> {
            LOGGER.info("Consumed message: {}", employeeDto);
        };
    }
}
