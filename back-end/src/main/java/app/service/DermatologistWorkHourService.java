package app.service;

import app.domain.DermatologistWorkHour;
import app.repository.DermatologistWorkHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DermatologistWorkHourService {
    @Autowired
    DermatologistWorkHourRepository workHourRepository;

    public List<DermatologistWorkHour> getDermatologistWorkHours(String email) {
        return workHourRepository.findWorkHoursByDermatologistEmail(email);
    }
}
