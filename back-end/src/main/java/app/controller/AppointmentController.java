package app.controller;

import app.domain.Appointment;
import app.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PreAuthorize("hasAnyRole('PHARMACIST','DERMATOLOGIST')")
    @PostMapping(value = "/finished/{id}/{appearance}")
    public void finished(@PathVariable Long id,@PathVariable Boolean appearance){
        Appointment a = appointmentService.findOneById(id);
        appointmentService.updateFinished(a.getId());
        if(appearance)
            appointmentService.updateAppearance(a.getId());
    }

}
