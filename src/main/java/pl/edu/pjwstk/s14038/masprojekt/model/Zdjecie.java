package pl.edu.pjwstk.s14038.masprojekt.model;

public class Zdjecie {

    private Long id;
    private String path;

    private Pokoj pokoj;

    private Zdjecie(Pokoj pokoj, String path) {
        this.path = path;
        this.pokoj = pokoj;

    }

    public static Zdjecie createImage(Pokoj pokoj, String path) throws Exception {
        if (pokoj == null) {
            throw new Exception("Pokoj nie istnieje");
        }
        Zdjecie zdjecie = new Zdjecie(pokoj, path);
        pokoj.getZdjecia().add(zdjecie);
        zdjecie.pokoj = pokoj;
        return zdjecie;
    }

    public static void remove_Image(Zdjecie zdjecie) {
        zdjecie.pokoj.getZdjecia().removeIf(i -> i.id == zdjecie.id);
    }


}
