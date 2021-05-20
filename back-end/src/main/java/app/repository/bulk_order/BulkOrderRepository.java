package app.repository.bulk_order;

import app.domain.bulk_order.BulkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BulkOrderRepository extends JpaRepository<BulkOrder, Long> {

}
