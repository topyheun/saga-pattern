package payment.adapter.out.messagequeue.produce

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component
import payment.application.port.out.PaymentPublishPort

@Component
class PaymentProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
) : PaymentPublishPort {

    private val logger = KotlinLogging.logger {}

    override fun publishPaymentEvent(orderNumber: String) {
        logger.info { "Payment Event - Publishing orderNumber: $orderNumber" }
    }

    override fun publishPaymentFailEvent(orderNumber: String) {
        logger.info { "Payment Event - Publishing orderNumber: $orderNumber" }
        kafkaTemplate.send("stock-rollback", orderNumber)
    }
}