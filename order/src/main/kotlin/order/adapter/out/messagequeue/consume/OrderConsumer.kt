package order.adapter.out.messagequeue.consume

import io.github.oshai.kotlinlogging.KotlinLogging
import order.application.port.`in`.OrderUseCase
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class OrderConsumer(
    private val orderUseCase: OrderUseCase,
) {

    private val logger = KotlinLogging.logger {}

    @KafkaListener(topics = ["order-rollback"], groupId = "group-01")
    fun handleOrderCreateRollbackEvent(orderNumber: String) {
        logger.info { "Order Rollback Event - Received orderNumber: $orderNumber" }
        orderUseCase.deleteByOrderNumber(orderNumber)
    }
}