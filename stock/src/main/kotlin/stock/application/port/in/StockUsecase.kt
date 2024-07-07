package stock.application.port.`in`

interface StockUsecase {
    fun decreaseStock(orderNumber: String)
    fun increaseStock(orderNumber: String)
    fun rollbackCreatedOrderEvent(orderNumber: String)
}