package app.service;

import app.domain.Vaccation;
import app.repository.VaccationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VaccationService{
    @Autowired
    VaccationRepository vaccationRepository;

    @Autowired
    JavaMailSender emailSender;

    public void save(Vaccation vaccation) {
        vaccationRepository.save(vaccation);
    }

    public List<Vaccation> findAll() {
        return vaccationRepository.findAll();
    }

    public List<Vaccation> findVacationsByMedicalWorkerEmail(String email) {
        return vaccationRepository.findVaccationsByMedicalWorkerEmail(email);
    }

    public Vaccation findVacationByMedicalWorkerEmail(String email) {
        return vaccationRepository.findVaccationByMedicalWorkerEmail(email);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Vaccation acceptVacationRequest(Vaccation vac, String email) {
        vac.setStatus(Vaccation.Status.ACCEPT);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("servis.apoteka@gmail.com");
        message.setTo("mat3xthepro@gmail.com");
        message.setSubject("Vacation request reviewed");
        String response = "Your vacation request has been accepted";
        message.setText(response);
        emailSender.send(message);
        return  vaccationRepository.save(vac);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Vaccation declineVacationRequest(Vaccation vac, String email, String declineReason) {
        vac.setStatus(Vaccation.Status.REJECT);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("servis.apoteka@gmail.com");
        message.setTo("mat3xthepro@gmail.com");
        message.setSubject("Vacation request reviewed");
        String response = String.format("Your vacation request has been declined because of: \n\t%s", declineReason);
        message.setText(response);
        emailSender.send(message);
        return vaccationRepository.save(vac);
    }
}
