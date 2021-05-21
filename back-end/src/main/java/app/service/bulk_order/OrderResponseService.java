package app.service.bulk_order;

import app.domain.bulk_order.OrderResponse;
import app.repository.bulk_order.OrderResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderResponseService {
    @Autowired
    OrderResponseRepository orderResponseRepository;

    public List<OrderResponse> getAll() {
        return orderResponseRepository.getAll();
    }

    public List<OrderResponse> getAllByOrderId(Long id) {
        return orderResponseRepository.getAllByOrderId(id);
    }

    public OrderResponse save(OrderResponse or) {
        return orderResponseRepository.save(or);
    }
}
