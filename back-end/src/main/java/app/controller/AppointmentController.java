package app.controller;

import app.domain.Appointment;
import app.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:8081","http://localhost:8080"})
@RequestMapping(value = "api/appointment")
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;

    @PostMapping(value = "/finished/{id}")
    public void finished(@PathVariable Long id){
        System.out.println("usao");
        Appointment a = appointmentService.findOneById(id);
        System.out.println("prosao");
        appointmentService.update(a.getId());
    }

}
