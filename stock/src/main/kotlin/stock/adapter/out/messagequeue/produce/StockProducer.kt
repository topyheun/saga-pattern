package stock.adapter.out.messagequeue.produce

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import stock.application.port.out.StockPublishPort

@Component
class StockProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) : StockPublishPort {

    private val logger = KotlinLogging.logger {}

    override fun publishStockDecreasedEvent(orderNumber: String) {
        logger.info { "Stock Decrease Event - Publishing orderNumber: $orderNumber" }
        kafkaTemplate.send("stock-decreased", orderNumber)
    }

    override fun rollbackStockDecreaseEvent(orderNumber: String) {
        logger.info { "Stock Rollback Event - Publishing orderNumber: $orderNumber" }
        kafkaTemplate.send("order-rollback", orderNumber)
    }

    override fun rollbackCreatedOrderEvent(orderNumber: String) {
        logger.info { "Order Rollback Event - Publishing orderNumber: $orderNumber" }
        kafkaTemplate.send("order-rollback", orderNumber)
    }
}