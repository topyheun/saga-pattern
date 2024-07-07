package order.application.port.out

import order.domain.Order

interface OrderPersistencePort {
    fun save(order: Order): Order
    fun deleteByOrderNumber(orderNumber: String)
}