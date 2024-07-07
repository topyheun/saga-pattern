package stock.adapter.out.messagequeue.consume

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import stock.application.port.`in`.StockUsecase

@ExtendWith(MockKExtension::class)
class StockConsumerTest(
    @MockK private val useCase: StockUsecase
) {
    @InjectMockKs
    private lateinit var sut: StockConsumer

    @Test
    @DisplayName("주문 생성 이벤트 수신 시 재고 감소 메서드가 호출된다.")
    fun should_DecreaseStock_when_OrderCreateEventIsReceived() {
        // Arrange
        val orderNumber = "123e4567-e89b-12d3-a456-426614174000"
        every { useCase.decreaseStock(orderNumber) } returns Unit

        // Act
        sut.handleOrderCreatedEvent(orderNumber)

        // Assert
        verify { useCase.decreaseStock(orderNumber) }
    }

    @Test
    @DisplayName("재고 롤백 이벤트 수신 시 재고 증가 및 주문 생성 롤백 메서드가 호출된다.")
    fun should_IncreaseStockAndRollbackOrderCreateEvent_when_StockDecreaseRollbackEventIsReceived() {
        // Arrange
        val orderNumber = "123e4567-e89b-12d3-a456-426614174000"
        every { useCase.increaseStock(orderNumber) } returns Unit
        every { useCase.rollbackCreatedOrderEvent(orderNumber) } returns Unit

        // Act
        sut.handleStockDecreaseRollbackEvent(orderNumber)

        // Assert
        verify { useCase.increaseStock(orderNumber) }
        verify { useCase.rollbackCreatedOrderEvent(orderNumber) }
    }
}