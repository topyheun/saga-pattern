package order.adapter.out.messagequeue.produce

import order.application.port.out.OrderPublishPort
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) : OrderPublishPort {

    private val logger = LoggerFactory.getLogger(OrderProducer::class.java)

    override fun publishOrderCreatedEvent(message: String) {
        logger.info("Order created event published: {}", message)
        kafkaTemplate.send("order-created", message)
    }
}