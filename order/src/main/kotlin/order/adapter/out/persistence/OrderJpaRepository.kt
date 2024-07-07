package order.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface OrderJpaRepository : JpaRepository<OrderJpaEntity, Long> {
    fun deleteByOrderNumber(orderNumber: String)
}