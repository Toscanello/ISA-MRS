package app.service;

import app.domain.WorkHour;
import app.repository.WorkHourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkHourService {
    @Autowired
    WorkHourRepository workHourRepository;
    public WorkHour findOneById(Long id){
        return workHourRepository.findOneById(id);
    }
}
