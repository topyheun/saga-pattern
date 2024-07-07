package order.adapter.out.messagequeue.consume


import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import order.application.port.`in`.OrderUseCase
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class OrderConsumerTest(
    @MockK private val orderUseCase: OrderUseCase
) {
    @InjectMockKs
    private lateinit var sut: OrderConsumer

    @Test
    @DisplayName("주문 롤백 이벤트 수신 시 주문 삭제 메서드가 호출된다.")
    fun should_DeleteOrder_when_OrderCreateRollbackEventIsReceived() {
        // Arrange
        val orderNumber = "123e4567-e89b-12d3-a456-426614174000"
        every { orderUseCase.deleteByOrderNumber(orderNumber) } returns Unit

        // Act
        sut.handleOrderCreateRollbackEvent(orderNumber)

        // Assert
        verify { orderUseCase.deleteByOrderNumber(orderNumber) }
    }
}