package app.repository;

import app.domain.WorkHour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkHourRepository extends JpaRepository<WorkHour,Long> {
    public WorkHour findOneById(Long id);
}
