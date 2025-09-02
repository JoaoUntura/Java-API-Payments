package dev.joaountura.payments_project.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {

    public static final String PAYMENT_QUEUE = "payment.queue";
    public static final String PAYMENT_EXCHANGE = "payment.exchange";
    public static final String PAYMENT_ROUTINGKEY = "payment.created";



    @Bean
    public Queue paymentQueue(){
        return new Queue(PAYMENT_QUEUE);
    }

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public DirectExchange  paymentExchange(){
        return new DirectExchange(PAYMENT_EXCHANGE);
    }

    @Bean
    public Binding paymentBinding(Queue paymentQueue, DirectExchange paymentExchange){
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with(PAYMENT_ROUTINGKEY);
    }


}
