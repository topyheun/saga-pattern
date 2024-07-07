package payment.adapter.out.messagequeue.consume

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import payment.application.port.`in`.PaymentUseCase

@Component
class PaymentConsumer(
    private val paymentUseCase: PaymentUseCase,
) {

    private val logger = KotlinLogging.logger {}

    @KafkaListener(topics = ["stock-decrease"], groupId = "group-01")
    fun handleStockDecreasedEvent(orderNumber: String) {
        logger.info { "Stock Decrease Event - Received orderNumber: $orderNumber" }
        paymentUseCase.payment(orderNumber)
    }
}