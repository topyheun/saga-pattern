package order.application.port.out

interface OrderPublishPort {
    fun publishOrderCreatedEvent(message: String)
}