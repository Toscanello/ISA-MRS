package app.service;

import app.domain.Report;
import app.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    public void save(Report report) {
        reportRepository.save(report);
    }
}
