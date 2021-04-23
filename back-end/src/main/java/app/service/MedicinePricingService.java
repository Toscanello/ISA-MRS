package app.service;

import app.domain.MedicinePricing;
import app.dto.MedicinePricingDTO;
import app.repository.MedicinePricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicinePricingService {
    @Autowired
    MedicinePricingRepository medicinePricingRepository;

    public List<MedicinePricing> findAllPricingsByPharmacyRegNo(String regNo) {
        return medicinePricingRepository.findMedicinePricingsByPharmacyRegNo(regNo);
    }

    public List<MedicinePricing> findAllPricingsForMedicineInPharmacy(String regNo, String code) {
        return medicinePricingRepository.findMedicinePricingsByCodeForPharmacy(regNo, code);
    }

    public MedicinePricing findActivePricingForMedicineInPharmacy(String regNo, String code) {
        return medicinePricingRepository.findActivePricingByCodeForPharmacy(regNo, code);
    }

    public MedicinePricing save(MedicinePricing medicinePricing) {
        return medicinePricingRepository.save(medicinePricing);
    }

    public void deletePricingFromPharmacy(String regNo, String code) {
        medicinePricingRepository.deleteMedicinePricingByPharmacyRegNo(regNo, code);
    }
}
