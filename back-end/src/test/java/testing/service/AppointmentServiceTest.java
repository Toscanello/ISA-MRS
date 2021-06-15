package testing.service;

import app.Main;
import app.domain.Appointment;
import app.repository.AppointmentRepository;
import app.service.AppointmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Main.class)
public class AppointmentServiceTest {
    @Mock
    private AppointmentRepository appointmentRepositoryMock;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    public void findPatientsPharmacistAppointmentsTest(){
        when(appointmentRepositoryMock.findPatientsPharmacistAppointments("farm@gmail.com"))
                .thenReturn(Collections.singletonList(new Appointment()));
        List<Appointment> appointmentList = appointmentService.findPatientsPharmacistAppointments("farm@gmail.com");
        assertThat(appointmentList,hasSize(1));

        verify(appointmentRepositoryMock,times(1)).findPatientsPharmacistAppointments("farm@gmail.com");
    }

    @Test
    public void saveTest(){
        Appointment a = new Appointment();
        a.setPrice(1000.0);
        when(appointmentRepositoryMock.save(a))
                .thenReturn(a);
        Appointment appointment = appointmentService.save(a);
        Double price = 1000.0;
        assertEquals(appointment.getPrice(),price);

        verify(appointmentRepositoryMock,times(1)).save(a);
    }

    @Test
    public void findActiveAppointmentsByDermatologistTest(){
        when(appointmentRepositoryMock.findActiveAppointmentsByDermatologist("derm1@gmail.com","ccc"))
                .thenReturn(Collections.singletonList(new Appointment()));
        List<Appointment> appointmentList = appointmentService.findActiveAppointmentsByDermatologist("derm1@gmail.com","ccc");
        assertThat(appointmentList,hasSize(1));

        verify(appointmentRepositoryMock,times(1)).findActiveAppointmentsByDermatologist("derm1@gmail.com","ccc");
    }

}
