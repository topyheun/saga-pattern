package order.application.port.`in`

interface OrderUseCase {
    fun createOrder(orderCreateCommand: OrderCreateCommand)
}