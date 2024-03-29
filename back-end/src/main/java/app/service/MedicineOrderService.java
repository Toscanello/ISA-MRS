package app.service;

import app.domain.Appointment;
import app.domain.MedicineOrder;
import app.dto.MedicineOrderDTO;
import app.repository.MedicineOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Transactional
    public void insertNewOrder(Long medicinePricingId, int quantity,
                               double price, String patientEmail, LocalDateTime start, LocalDateTime end){


        medicineOrderRepository.insertNewOrder(medicinePricingId, quantity, price, patientEmail, start, end);
        Integer mo = medicineOrderRepository.getId();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("servis.apoteka@gmail.com");
        message.setTo("mika95455@gmail.com");
        message.setSubject("Rezervacija lijeka");
        message.setText("Jedinstveni id rezervacije je: " + Integer.toString(mo));
        emailSender.send(message);
    }

    public List<MedicineOrderDTO> findOrdersByPharmacy(String regno) {
        List<MedicineOrder> medicineOrders = medicineOrderRepository.findAllByPharmacyRegNo(regno);
        List<MedicineOrderDTO> medicineOrderDTOS = new ArrayList<MedicineOrderDTO>();
        for (MedicineOrder mo: medicineOrders) {
            medicineOrderDTOS.add(new MedicineOrderDTO(mo));
        }
        return medicineOrderDTOS;
    }

    public MedicineOrder findOrderById(Long id) {
        return medicineOrderRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(MedicineOrder mo) {
        medicineOrderRepository.update(mo.getId());
    }

    public void delete(MedicineOrder mo) {
        medicineOrderRepository.deleteOrder(mo.getId());
    }
}
