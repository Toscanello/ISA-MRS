package app.repository.bulk_order;

import app.domain.bulk_order.BulkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BulkOrderRepository extends JpaRepository<BulkOrder, Long> {

    public List<BulkOrder> getAllByPharmacyRegNo(String regNo);

}
