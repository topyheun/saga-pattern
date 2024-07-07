package stock.application.port.out

interface StockPublishPort {
    fun publishStockDecreasedEvent(orderNumber: String)
    fun rollbackStockDecreaseEvent(orderNumber: String)
    fun rollbackCreatedOrderEvent(orderNumber: String)
}