package src.main.java.DataModel;

import java.util.ArrayList;
import java.util.List;

public class Pracownik {
    private String NazwiskoImie;
    private List<Zadanie> listaZadan = new ArrayList<>();

    public Pracownik(String NazwiskoImie) {
        this.NazwiskoImie = NazwiskoImie;
    }


}
