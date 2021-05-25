package app.dto;

public class ReportDTO {
    private String report;
    private Integer days;
    private String patient_id;

    public ReportDTO() {
    }

    public ReportDTO(String report, Integer days, String patient_id) {
        this.report = report;
        this.days = days;
        this.patient_id = patient_id;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }
}
