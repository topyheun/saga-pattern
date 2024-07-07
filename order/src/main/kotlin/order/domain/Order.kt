package order.domain

import java.util.*

class Order(
    private val id: Long,
    private val orderNumber: String,
    private val productId: Long,
) {

    constructor(id: Long, orderNumber: String) : this(id, orderNumber, 0)

    constructor(orderNumber: String, productId: Long) : this(0, orderNumber, productId)

    companion object {
        fun of(productId: Long): Order {
            return Order(UUID.randomUUID().toString(), productId)
        }
    }

    fun getOrderNumber(): String {
        return orderNumber
    }
}