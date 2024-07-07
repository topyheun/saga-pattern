package order.adapter.out.messagequeue.produce

import io.github.oshai.kotlinlogging.KotlinLogging
import order.application.port.out.OrderPublishPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OrderProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) : OrderPublishPort {

    private val logger = KotlinLogging.logger {}

    override fun publishOrderCreatedEvent(orderNumber: String) {
        logger.info { "Order Rollback Event - Publishing orderNumber: $orderNumber" }
        kafkaTemplate.send("order-create", orderNumber)
    }
}