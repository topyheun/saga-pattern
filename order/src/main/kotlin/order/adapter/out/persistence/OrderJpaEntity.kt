package order.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class OrderJpaEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    var orderNumber: String,
) {

    protected constructor() : this(0, "")

    constructor(orderNumber: String) : this(0, orderNumber)
}