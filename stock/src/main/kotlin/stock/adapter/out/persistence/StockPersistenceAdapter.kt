package stock.adapter.out.persistence

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import stock.application.port.out.StockPersistencePort

@Repository
@Transactional
class StockPersistenceAdapter(
    private val stockJpaRepository: StockJpaRepository,
) : StockPersistencePort {

    override fun decreaseStock(orderNumber: String) {
        val stockJpaEntity = stockJpaRepository.findByOrderNumber(orderNumber)
            ?: throw EntityNotFoundException("Stock not found for order number: $orderNumber")
        stockJpaEntity.decrease()
    }

    override fun increaseStock(orderNumber: String) {
        val stockJpaEntity = stockJpaRepository.findByOrderNumber(orderNumber)
            ?: throw EntityNotFoundException("Stock not found for order number: $orderNumber")
        stockJpaEntity.increase()
    }
}