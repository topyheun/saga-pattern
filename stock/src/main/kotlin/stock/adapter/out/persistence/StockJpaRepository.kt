package stock.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface StockJpaRepository : JpaRepository<StockJpaEntity, Long> {
    fun findByOrderNumber(orderNumber: String): StockJpaEntity?
}