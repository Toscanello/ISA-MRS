package testing.service;

import app.Main;
import app.domain.bulk_order.BulkOrder;
import app.repository.bulk_order.BulkOrderRepository;
import app.service.bulk_order.BulkOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class BulkOrderServiceTest {

    @Mock
    private BulkOrderRepository bulkOrderRepositoryMock;

    @Mock
    private BulkOrder bulkOrderMock;

    @InjectMocks
    private BulkOrderService bulkOrderService;

    @Test
    public void getAllBulkOrdersByPharmacyTest() {
        when(bulkOrderRepositoryMock.getAllByPharmacyRegNo("abc"))
                .thenReturn(Collections.singletonList(new BulkOrder()));

        List<BulkOrder> orders = bulkOrderService.getAllByPharmacyRegNo("abc");

        assertThat(orders, hasSize(1));

        verify(bulkOrderRepositoryMock, times(1)).getAllByPharmacyRegNo("abc");
    }


}
