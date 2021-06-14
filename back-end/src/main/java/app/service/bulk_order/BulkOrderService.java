package app.service.bulk_order;

import app.domain.bulk_order.BulkOrder;
import app.repository.bulk_order.BulkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BulkOrderService {
    @Autowired
    BulkOrderRepository bulkOrderRepository;

    public BulkOrder save(BulkOrder boi) {
        return this.bulkOrderRepository.save(boi);
    }

    public List<BulkOrder> getAllByPharmacyRegNo(String regNo) {
        return bulkOrderRepository.getAllByPharmacyRegNo(regNo);
    }

    public BulkOrder getOneById(Long id) {
        return bulkOrderRepository.getOne(id);
    }

    public void delete(BulkOrder bo) { this.bulkOrderRepository.delete(bo);}

}
