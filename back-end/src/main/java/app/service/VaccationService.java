package app.service;

import app.domain.Vaccation;
import app.repository.VaccationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VaccationService{
    @Autowired
    VaccationRepository vaccationRepository;

    public void save(Vaccation vaccation) {
        vaccationRepository.save(vaccation);
    }
}
