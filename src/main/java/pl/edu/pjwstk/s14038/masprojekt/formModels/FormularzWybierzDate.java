package pl.edu.pjwstk.s14038.masprojekt.formModels;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class FormularzWybierzDate {
    @DateTimeFormat(pattern = "dd.MM.yyyy",iso = DateTimeFormat.ISO.DATE)
    private Date odData;
    @DateTimeFormat(pattern = "dd.MM.yyyy",iso = DateTimeFormat.ISO.DATE)
    private Date doData;
    private Long idKwatera;


    public FormularzWybierzDate setOdData(Date odData) {
        this.odData = odData;
        return this;
    }

    public FormularzWybierzDate setDoData(Date doData) {
        this.doData = doData;
        return this;
    }

    public FormularzWybierzDate setIdKwatera(Long idKwatera) {
        this.idKwatera = idKwatera;
        return this;
    }

    public Date getOdData() {
        return odData;
    }

    public Date getDoData() {
        return doData;
    }

    public Long getIdKwatera() {
        return idKwatera;
    }
}
