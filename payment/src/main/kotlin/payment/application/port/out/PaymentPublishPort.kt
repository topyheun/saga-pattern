package payment.application.port.out

interface PaymentPublishPort {
    fun publishPaymentEvent(orderNumber: String)
    fun publishPaymentFailEvent(orderNumber: String)
}