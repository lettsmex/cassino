import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Carta> cartas;

    public Baralho() {
        cartas = new ArrayList<>();
        String[] naipes = {"Espadas", "Copas", "Ouros", "Paus"};
        String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        for (String naipe : naipes) {
            for (String valor : valores) {
                cartas.add(new Carta(naipe, valor));
            }
        }
        Collections.shuffle(cartas);
    }

    public Carta distribuirCarta() {
        return cartas.isEmpty() ? null : cartas.remove(cartas.size() - 1);
    }
}
