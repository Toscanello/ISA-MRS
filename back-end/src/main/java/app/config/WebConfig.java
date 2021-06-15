package app.config;

import app.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class WebConfig {
    @Autowired
    PatientService patientService;

    @Scheduled(cron = "* * * 1 * ?")
    public void scheduleTaskUsingCronExpression() {
        patientService.erasePenalties();
    }
}
