package app.service;

import app.domain.DermatologistRating;
import app.domain.PharmacistRating;
import app.repository.DermatologistRatingRepository;
import app.repository.PharmacistRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistRatingService {
    @Autowired
    DermatologistRatingRepository dermatologistRatingRepository;

    public void addRating(String patientEmail, String dermatologistEmail, Double rating){
        if(dermatologistRatingRepository.findRatingByEmail(patientEmail,dermatologistEmail ) == null)
            dermatologistRatingRepository.addRating(patientEmail, dermatologistEmail, rating);
        else
            dermatologistRatingRepository.updateRating(patientEmail, dermatologistEmail, rating);
    }

    public Double getRating(String patientEmail, String dermatologistEmail){
        DermatologistRating retVal = dermatologistRatingRepository.findRatingByEmail(patientEmail,dermatologistEmail);
        if(retVal == null)
            return 0.0;
        else
            return retVal.getRating();
    }

    public double calculateRatingByDermatologist(String email) {
        double sum = 0.;
        double count = 0.;
        List<DermatologistRating> dermatologistRatings
                = dermatologistRatingRepository.getDermatologistRatingsByDermatologistEmail(email);
        if (dermatologistRatings.isEmpty())
            return 0.;
        for (DermatologistRating rating : dermatologistRatings) {
            sum += rating.getRating();
            count++;
        }
        return sum/count;
    }
}
