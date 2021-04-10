package app.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dermatologist extends MedicalWorker{

    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<DermatologistWorkHour> workingHours = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    public Set<DermatologistWorkHour> getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Set<DermatologistWorkHour> workngHours) {
        this.workingHours = workngHours;
    }

    @Override
    public Set<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
