package app.service;

import app.domain.MedicinePricing;
import app.dto.MedicinePricingDTO;
import app.repository.MedicinePricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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

    public List<MedicinePricing> findMedicinePricingsByDate(){
        return medicinePricingRepository.findMedicinePricingsByDate(LocalDateTime.now());
    }

    public Set<String> findMedicineIDByMedicinePricingID(long medicinePricingId){
        return medicinePricingRepository.findMedicineIDByMedicinePricingID(medicinePricingId);
    }

    public List<MedicinePricing> findAllActivePricingInPharmacy(String regNo) {
        return medicinePricingRepository.findMedicinePricingsByPharmacyRegNo(regNo);
    }

    public MedicinePricing findMedicinePricingID(long medicinePricingId){
        return medicinePricingRepository.findById(medicinePricingId).orElse(null);
    }
}
