package payment.application.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import payment.application.port.`in`.PaymentUseCase
import payment.application.port.out.PaymentPublishPort

@Service
class PaymentService(
    private val paymentPublishPort: PaymentPublishPort,
) : PaymentUseCase {

    private val logger = KotlinLogging.logger {}

    override fun payment(orderNumber: String) {
        try {
            paymentPublishPort.publishPaymentEvent(orderNumber)
            Thread.sleep(1000 * 10) // Payment processing, h2-console 확인용
            throw RuntimeException("Payment failed")
        } catch (e: Exception) {
            logger.error { "Payment Fail - orderNumber: $orderNumber" }
            paymentPublishPort.publishPaymentFailEvent(orderNumber)
            e.printStackTrace()
        }
    }
}