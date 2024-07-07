package order.adapter.out.persistence

import order.application.port.out.OrderPersistencePort
import order.domain.Order
import org.springframework.stereotype.Repository

@Repository
class OrderPersistenceAdapter(
    private val orderJpaRepository: OrderJpaRepository,
) : OrderPersistencePort {

    override fun save(order: Order): Order {
        val orderJpaEntity = orderJpaRepository.save(OrderJpaEntity(order.getOrderNumber()))
        return Order(orderJpaEntity.id, orderJpaEntity.orderNumber)
    }

    override fun deleteByOrderNumber(orderNumber: String) {
        orderJpaRepository.deleteByOrderNumber(orderNumber);
    }
}