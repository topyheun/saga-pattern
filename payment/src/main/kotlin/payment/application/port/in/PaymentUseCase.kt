package payment.application.port.`in`

interface PaymentUseCase {
    fun payment(orderNumber: String)
}