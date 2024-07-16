package stock.application.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import stock.application.port.`in`.StockUsecase
import stock.application.port.out.StockPersistencePort
import stock.application.port.out.StockPublishPort

@Service
class StockService(
    private val stockPersistencePort: StockPersistencePort,
    private val stockPublishPort: StockPublishPort,
) : StockUsecase {

    private val logger = KotlinLogging.logger {}

    @Transactional
    override fun decreaseStock(orderNumber: String) {
        try {
            logger.info { "Stock Decrease Start - orderNumber: $orderNumber" }
            stockPersistencePort.decreaseStock(orderNumber)
            stockPublishPort.publishStockDecreasedEvent(orderNumber)
        } catch (e: Exception) {
            logger.info { "Stock Decrease Fail - orderNumber: $orderNumber" }
            stockPublishPort.rollbackStockDecreaseEvent(orderNumber)
            e.printStackTrace()
        }
    }

    override fun increaseStock(orderNumber: String) {
        logger.info { "Stock Increase Start - orderNumber: $orderNumber" }
        stockPersistencePort.increaseStock(orderNumber)
    }

    override fun rollbackCreatedOrderEvent(orderNumber: String) {
        logger.info { "Stock Rollback Start - orderNumber: $orderNumber" }
        stockPublishPort.rollbackCreatedOrderEvent(orderNumber)
    }
}