package stock.adapter.out.messagequeue.consume

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import stock.application.port.`in`.StockUsecase

@Component
class StockConsumer(
    private val stockUseCase: StockUsecase,
) {

    private val logger = KotlinLogging.logger {}

    @KafkaListener(topics = ["order-create"], groupId = "group-01")
    fun handleOrderCreatedEvent(orderNumber: String) {
        logger.info { "Order Create Event - Received orderNumber: $orderNumber" }
        stockUseCase.decreaseStock(orderNumber)
    }

    @KafkaListener(topics = ["stock-rollback"], groupId = "group-01")
    fun handleStockDecreaseRollbackEvent(orderNumber: String) {
        logger.info { "Stock Rollback Event - Received orderNumber: $orderNumber" }
        stockUseCase.increaseStock(orderNumber)
        stockUseCase.rollbackCreatedOrderEvent(orderNumber)
    }
}