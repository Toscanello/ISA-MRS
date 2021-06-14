package app.service;

import app.domain.DermatologistRating;
import app.domain.MedicineRating;
import app.domain.PharmacistRating;
import app.repository.MedicineRatingRespository;
import app.repository.PharmacistRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacistRatingService {
    @Autowired
    PharmacistRatingRepository pharmacistRatingRepository;

    public void addRating(String patientEmail, String pharmacistEmail, Double rating){
        if(pharmacistRatingRepository.findRatingByEmail(patientEmail,pharmacistEmail ) == null)
            pharmacistRatingRepository.addRating(patientEmail, pharmacistEmail, rating);
        else
            pharmacistRatingRepository.updateRating(patientEmail, pharmacistEmail, rating);
    }

    public Double getRating(String patientEmail, String pharmacistEmail){
        PharmacistRating retVal = pharmacistRatingRepository.findRatingByEmail(patientEmail,pharmacistEmail);
        if(retVal == null)
            return 0.0;
        else
            return retVal.getRating();
    }

}
