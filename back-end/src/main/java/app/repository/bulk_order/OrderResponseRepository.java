package app.repository.bulk_order;

import app.domain.bulk_order.OrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderResponseRepository extends JpaRepository<OrderResponse, Long> {
    List<OrderResponse> getAll();

    List<OrderResponse> getAllByOrderId(Long id);

}
