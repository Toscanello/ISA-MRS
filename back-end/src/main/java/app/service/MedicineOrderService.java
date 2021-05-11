package app.service;

import app.domain.Appointment;
import app.domain.MedicineOrder;
import app.dto.MedicineOrderDTO;
import app.repository.MedicineOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MedicineOrderService {
    @Autowired
    MedicineOrderRepository medicineOrderRepository;

    @Autowired
    private JavaMailSender emailSender;

    public List<MedicineOrder> findPatientsMedicineOrders(String email)
    {
        return medicineOrderRepository.findPatientsMedicineOrders(email);
    }

    public MedicineOrder save(MedicineOrderDTO moDTO) {

        MedicineOrder mo = new MedicineOrder();
        return medicineOrderRepository.save(mo);
    }

    public void insertNewOrder(Long medicinePricingId, int quantity,
                               double price, String patientEmail, LocalDateTime start, LocalDateTime end){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("servis.apoteka@gmail.com");
        message.setTo("klimentaj99@gmail.com");
        message.setSubject("Rezervacija lijeka");
        message.setText("Narucen je lijek " + Long.toString(medicinePricingId));
        emailSender.send(message);
        medicineOrderRepository.insertNewOrder(medicinePricingId, quantity, price, patientEmail, start, end);
    }
}
