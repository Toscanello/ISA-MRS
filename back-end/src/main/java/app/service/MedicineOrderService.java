package app.service;

import app.domain.MedicineOrder;
import app.repository.MedicineOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineOrderService {
    @Autowired
    MedicineOrderRepository medicineOrderRepository;

    public List<MedicineOrder> findPatientsMedicineOrders(String email)
    {
        return medicineOrderRepository.findPatientsMedicineOrders(email);
    }
}
