package app.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dermatologist extends MedicalWorker{

    @OneToMany(mappedBy = "dermatologist", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<WorkHour> workngHours = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Appointment> appointments = new HashSet<>();

    public Set<WorkHour> getWorkngHours() {
        return workngHours;
    }

    public void setWorkngHours(Set<WorkHour> workngHours) {
        this.workngHours = workngHours;
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
