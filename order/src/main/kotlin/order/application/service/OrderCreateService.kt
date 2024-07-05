package order.application.service

import order.application.port.`in`.OrderCreateCommand
import order.application.port.`in`.OrderUseCase
import order.application.port.out.OrderPersistencePort
import order.application.port.out.OrderPublishPort
import order.domain.Order
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderCreateService(
    private val orderPersistencePort: OrderPersistencePort,
    private val orderPublishPort: OrderPublishPort
) : OrderUseCase {

    @Transactional
    override fun createOrder(orderCreateCommand: OrderCreateCommand) {
        val order = orderPersistencePort.save(Order.of(orderCreateCommand.productId))
        orderPublishPort.publishOrderCreatedEvent(order.getOrderNumber())
    }
}