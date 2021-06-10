package app.service;

import app.domain.Discount;
import app.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    public List<Discount> findAll() { return discountRepository.findAll(); }

    public Discount save(Discount d) {
        return discountRepository.save(d);
    }

    public List<Discount> findAllByMedicineAndPharmacy(String code, String regNo) {
        return discountRepository.getAllByMedicineCodeAndPharmacyRegNo(code, regNo);
    }

    public Discount getCurrentDiscount(String code, String regNo) {
        return discountRepository.getActiveDiscountByMedicineCodeAndPharmacyRegNo(code, regNo);
    }
}
