import com.charter.cutomerRewards.configuration.RewardPointsRangeConfiguration;
import com.charter.cutomerRewards.database.entities.CustomerEntity;
import com.charter.cutomerRewards.database.entities.PaymentsEntity;
import com.charter.cutomerRewards.database.repository.CustomerRepository;
import com.charter.cutomerRewards.database.repository.PaymentsRepository;
import com.charter.cutomerRewards.dto.response.RewardsPerMonth;
import com.charter.cutomerRewards.dto.response.RewardsResponse;
import com.charter.cutomerRewards.service.RewardsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RewardServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    PaymentsRepository paymentsRepository;

    @Mock
    RewardPointsRangeConfiguration rewardsConfig;

    @InjectMocks
    RewardsService rewardsService;

    CustomerEntity customer1 = new CustomerEntity();
    @BeforeEach
    void init() {
        customer1 = CustomerEntity.builder().firstName("Multani").id(1L).build();
        when(customerRepository.findCustomerEntityById(1L))
                .thenReturn(Optional.ofNullable(customer1));
    }

    @Test
    void successScenario() {
        List<PaymentsEntity> validPaymentsEntityList = Arrays.asList(
                buildPaymentEntity(customer1,1L, BigDecimal.valueOf(120),LocalDate.now()),
                buildPaymentEntity(customer1,2L, BigDecimal.valueOf(60),LocalDate.now().minusMonths(2)),
                buildPaymentEntity(customer1,3L, BigDecimal.valueOf(50),LocalDate.now().minusMonths(1)));
        Map<String, Integer> pointsRange = new HashMap<>();
        pointsRange.put("LOWER", 1);
        pointsRange.put("UPPER", 2);
        when(rewardsConfig.getLowerLimit()).thenReturn(50);
        when(rewardsConfig.getUpperLimit()).thenReturn(100);
        when(rewardsConfig.getPointsRange()).thenReturn(pointsRange);
        when(paymentsRepository.findAllByCustomerAndTransactionDateAfter(any(CustomerEntity.class), any(LocalDate.class)))
                .thenReturn(validPaymentsEntityList);
        ResponseEntity<RewardsResponse> response = rewardsService.getCustomerRewards(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Multani",response.getBody().getCustomerName());
        assertTrue(response.getBody().getPointsBreakdown().stream()
                .map(RewardsPerMonth::getMonth)
                .collect(Collectors.toList())
                .contains(LocalDate.now().getMonth().name()));
        assertEquals(100,response.getBody().getTotalPoints());
    }

    @Test
    void errorScenario_NoPaymentsForCustomer() {
        when(paymentsRepository.findAllByCustomerAndTransactionDateAfter(any(CustomerEntity.class), any(LocalDate.class)))
                .thenReturn(new ArrayList<>());
        ResponseEntity<RewardsResponse> response = rewardsService.getCustomerRewards(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertEquals("Multani",response.getBody().getCustomerName());
        assertEquals(0,response.getBody().getTotalPoints());
    }

    @Test
    void errorScenario_NoCustomerFound() {
        when(customerRepository.findCustomerEntityById(1L))
                .thenReturn(Optional.empty());
        ResponseEntity<RewardsResponse> response = rewardsService.getCustomerRewards(1L);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertNull(response.getBody().getCustomerName());
        assertNull(response.getBody().getTotalPoints());
        assertNull(response.getBody().getPointsBreakdown());
    }

    PaymentsEntity buildPaymentEntity(CustomerEntity customer, long paymentId ,BigDecimal transactionAmount, LocalDate transactionDate) {
        return PaymentsEntity.builder().id(paymentId).customer(customer).merchantName("Charter Bank")
                .transactionAmount(transactionAmount).transactionDate(transactionDate).build();
    }
}
