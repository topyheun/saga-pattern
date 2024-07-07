package payment.adapter.out.messagequeue.consume

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import payment.application.port.`in`.PaymentUseCase

@ExtendWith(MockKExtension::class)
class PaymentConsumerTest(
    @MockK private val paymentUseCase: PaymentUseCase
) {
    @InjectMockKs
    private lateinit var sut: PaymentConsumer

    @Test
    @DisplayName("재고 감소 이벤트 수신 시 결제 메서드가 호출된다.")
    fun should_Payment_when_StockDecreaseEventIsReceived() {
        // Arrange
        val orderNumber = "123e4567-e89b-12d3-a456-426614174000"
        every { paymentUseCase.payment(orderNumber) } returns Unit

        // Act
        sut.handleStockDecreasedEvent(orderNumber)

        // Assert
        verify { paymentUseCase.payment(orderNumber) }
    }
}