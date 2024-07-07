package stock.adapter.out.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "stock")
class StockJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(nullable = false)
    var orderNumber: String,

    @Column(nullable = false)
    var count: Int,
) {

    constructor(orderNumber: String, quantity: Int) : this(0, orderNumber, quantity)

    protected constructor() : this("", 0)

    fun decrease() {
        this.count--
    }

    fun increase() {
        this.count++
    }
}