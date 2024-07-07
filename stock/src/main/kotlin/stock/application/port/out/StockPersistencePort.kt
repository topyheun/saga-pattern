package stock.application.port.out

interface StockPersistencePort {
    fun decreaseStock(orderNumber: String)
    fun increaseStock(orderNumber: String)
}