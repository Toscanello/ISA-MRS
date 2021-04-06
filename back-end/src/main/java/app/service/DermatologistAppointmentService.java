package app.service;

import app.domain.DermatologistAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DermatologistAppointmentService {
    @Autowired
    DermatologistAppointmentService dermatologistAppointmentService;

    public DermatologistAppointment save(DermatologistAppointment da) {
        return dermatologistAppointmentService.save(da);
    }
}
