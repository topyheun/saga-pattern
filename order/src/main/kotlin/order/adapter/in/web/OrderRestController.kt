package order.adapter.`in`.web

import order.application.port.`in`.OrderCreateCommand
import order.application.service.OrderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderRestController(
    private val orderService: OrderService,
) {

    @PostMapping("/order")
    fun order(@RequestBody orderCreateCommand: OrderCreateCommand) {
        orderService.createOrder(orderCreateCommand)
    }
}