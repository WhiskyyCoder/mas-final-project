package pl.edu.pjwstk.s14038.masprojekt.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.edu.pjwstk.s14038.masprojekt.formModels.FormularzWybierzDate;

import java.util.Date;

public class WybierzDateValidator implements Validator {

    public boolean supports(Class clazz) {
        return FormularzWybierzDate.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        if(obj!=null) {
            FormularzWybierzDate formularzWybierzDate = (FormularzWybierzDate) obj;
            Date now=new Date();
            if(formularzWybierzDate.getOdData()!=null && formularzWybierzDate.getDoData()!=null) {
                if (formularzWybierzDate.getOdData().before(now))
                    e.rejectValue("odData", null, "Podana data rezerwacji od musi być większa niż dziś");
                if (formularzWybierzDate.getOdData().after(formularzWybierzDate.getDoData()))
                    e.rejectValue("odData", null, "Podana data musi być mniejsza od daty do");
                if (formularzWybierzDate.getDoData().before(formularzWybierzDate.getOdData()))
                    e.rejectValue("doData", null, "Podana data musi być większa od daty od");
            }else{
                if (formularzWybierzDate.getOdData()==null)
                    e.rejectValue("odData", null, "To pole nie może być puste");
                if (formularzWybierzDate.getDoData()==null)
                    e.rejectValue("doData", null, "To pole nie może być puste");
            }
        }
    }


}
