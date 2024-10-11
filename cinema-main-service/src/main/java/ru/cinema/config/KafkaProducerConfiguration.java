package ru.cinema.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Artem Chernikov
 * @version 1.0
 * @since 15.09.2024
 */
@Configuration
public class KafkaProducerConfiguration {

//    @Value("${spring.kafka.producer.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Bean
//    public ProducerFactory<String, RequestAddFilms> producerFactory() {
//        Map<String, Object> configProps = new HashMap<>();
//        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//        return new DefaultKafkaProducerFactory<>(configProps);
//    }
//
//    @Bean
//    public KafkaTemplate<String, RequestAddFilms> kafkaTemplate() {
//        return new KafkaTemplate<>(producerFactory());
//    }

    @Bean
    public NewTopic createTopic() {
        return new NewTopic("films", 5, (short) 1);
    }
}
