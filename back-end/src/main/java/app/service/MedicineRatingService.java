package app.service;

import app.domain.MedicineRating;
import app.repository.AppointmentRepository;
import app.repository.MedicineRatingRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineRatingService {
    @Autowired
    MedicineRatingRespository medicineRatingRespository;

    public void addRating(String patientEmail, String medicineCode, Double rating){
        if(medicineRatingRespository.findRatingByEmail(patientEmail,medicineCode ) == null)
            medicineRatingRespository.addRating(patientEmail, medicineCode, rating);
        else
            medicineRatingRespository.updateRating(patientEmail, medicineCode, rating);
    }

    public Double getRating(String patientEmail, String medicineCode){
        MedicineRating retVal = medicineRatingRespository.findRatingByEmail(patientEmail,medicineCode);
        if(retVal == null)
            return 0.0;
        else
            return retVal.getRating();
    }
}
