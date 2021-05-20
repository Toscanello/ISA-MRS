package app.service.bulk_order;

import app.domain.bulk_order.BulkOrder;
import app.repository.bulk_order.BulkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BulkOrderService {
    @Autowired
    BulkOrderRepository bulkOrderRepository;

    public BulkOrder save(BulkOrder boi) {
        return this.bulkOrderRepository.save(boi);
    }

}
