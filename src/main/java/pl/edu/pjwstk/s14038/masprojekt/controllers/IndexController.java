package pl.edu.pjwstk.s14038.masprojekt.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pjwstk.s14038.masprojekt.formModels.FormularzRezerwacji;
import pl.edu.pjwstk.s14038.masprojekt.formModels.FormularzWybierzDate;
import pl.edu.pjwstk.s14038.masprojekt.model.Kwatera;
import pl.edu.pjwstk.s14038.masprojekt.model.Pokoj;
import pl.edu.pjwstk.s14038.masprojekt.model.Rezerwacja;
import pl.edu.pjwstk.s14038.masprojekt.model.Uzytkownik;
import pl.edu.pjwstk.s14038.masprojekt.services.RezerwacjaService;
import pl.edu.pjwstk.s14038.masprojekt.services.UzytkownikService;
import pl.edu.pjwstk.s14038.masprojekt.validators.WybierzDateValidator;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class IndexController {
    private UzytkownikService uzytkownikService;
    private RezerwacjaService rezerwacjaService;

    public IndexController(UzytkownikService uzytkownikService, RezerwacjaService rezerwacjaService) {
        this.uzytkownikService = uzytkownikService;
        this.rezerwacjaService = rezerwacjaService;
    }

    @InitBinder("termin")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new WybierzDateValidator());
    }

    @RequestMapping("/dodaj")
    public String dodajUzytkownika() {
        Uzytkownik uzytkownik = Uzytkownik.dodajWlasciciela(uzytkownikService, null, "321321", "Jan", "Kowalski", "test@pjwstk.edu.pl", "ala");
        Kwatera kwatera = new Kwatera("Przyjazna chatka", "ALEJA. 29 LISTOPADA, 35-334 Kraków", 3, uzytkownik);
        Pokoj p1 = new Pokoj(3, "30x20x30");
        Pokoj p2 = new Pokoj(4, "40x20x30");
        Pokoj p3 = new Pokoj(2, "50x25x30");
        kwatera.dodajPokoj(rezerwacjaService, p1);
        kwatera.dodajPokoj(rezerwacjaService, p2);
        kwatera.dodajPokoj(rezerwacjaService, p3);


        return "index";
    }

    @GetMapping("/wybierzdate/{idKwatera}")
    public String pokazTerminFormularz(@PathVariable Long idKwatera, Model model) {
        FormularzWybierzDate formularzWybierzDate = new FormularzWybierzDate();
        formularzWybierzDate.setIdKwatera(idKwatera);
        model.addAttribute("termin", formularzWybierzDate);
        return "wybierz_date";
    }

    @PostMapping("/termin")
    public String obsluzFormularzTermin(@Valid @ModelAttribute("termin") FormularzWybierzDate formularzWybierzDate, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors())
            return "wybierz_date";

        Map<Long, Pokoj> pokoje = uzytkownikService.getKwateraRepository().findById(formularzWybierzDate.getIdKwatera()).orElse(new Kwatera()).getPokoje();
        Set<Pokoj> pokojeKwatery = Rezerwacja.pokazDostepnePokoje(rezerwacjaService, pokoje.values().
                stream().collect(Collectors.toSet()), formularzWybierzDate.getOdData(), formularzWybierzDate.getDoData());


        String pattern = "dd.MM.yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateOd = simpleDateFormat.format(formularzWybierzDate.getOdData());
        String dateDo = simpleDateFormat.format(formularzWybierzDate.getDoData());
        model.addAttribute("pokoje", pokojeKwatery);
        model.addAttribute("dataOd", dateOd);
        model.addAttribute("dataDo", dateDo);

        return "wybierz_pokoj";
    }

    @PostMapping("/rezerwacja")
    public String pokazFormularzRezerwacji(@RequestParam("idChecked") List<Integer> idPokoje, @RequestParam String dataOd, @RequestParam String dataDo, Model model) {
        FormularzRezerwacji rezerwacja = new FormularzRezerwacji();
        rezerwacja.setIdPokoje(idPokoje);
        rezerwacja.setDoo(dataDo);
        rezerwacja.setOd(dataOd);
        model.addAttribute("formularzRezerwacji", rezerwacja);
        return "rezerwacja";
    }

    @PostMapping("/podsumowanie")
    public String pokazPodsumowanie(@Valid @ModelAttribute("formularzRezerwacji") FormularzRezerwacji formularzRezerwacji, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors())
            return "rezerwacja";

        Rezerwacja rezerwacja = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
            rezerwacja = Rezerwacja.rezerwuj(rezerwacjaService, simpleDateFormat.parse(formularzRezerwacji.getOd()),
                    simpleDateFormat.parse(formularzRezerwacji.getDoo()), formularzRezerwacji.getTelefonKontaktowy(),
                    formularzRezerwacji.getNazwisko(), formularzRezerwacji.getCenaZaOsobe(), formularzRezerwacji.getIloscOsob());
        } catch (Exception e) {
        }

        Set<Pokoj> pokoje = new HashSet<>();

        formularzRezerwacji.getIdPokoje().forEach(id ->
                {
                    pokoje.add(rezerwacjaService.getPokojRepository().findById(new Long(id)).orElse(new Pokoj()));
                }

        );
        rezerwacja.setPokoje(pokoje);
        rezerwacjaService.getRezerwacjaRepository().save(rezerwacja);

        model.addAttribute("rezerwacja", rezerwacja);

        return "podsumowanie";
    }

    @GetMapping("/kwatery")
    public String pokazKwatery(Model model) {
        Uzytkownik uzytkownik = uzytkownikService.getUzytkownikRepository().findById(new Long(1)).get();
        model.addAttribute("kwatery", uzytkownik.getKwatery());
        return "kwatery";
    }

    @GetMapping("/")
    public String pokazIndex(Model model) {
        Uzytkownik uzytkownik = uzytkownikService.getUzytkownikRepository().findById(new Long(1)).get();
        model.addAttribute("uzytkownik", uzytkownik);

        return "index";
    }

}
