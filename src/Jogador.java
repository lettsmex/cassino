import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private List<Carta> mao;

    public Jogador(String nome) {
        this.nome = nome;
        mao = new ArrayList<>();
    }

    public void receberCarta(Carta carta) {
        mao.add(carta);
    }

    public String mostrarMao() {
        return "Mão do " + nome + ": " + mao.toString();
    }

    public int calcularPontuacao() {
        int pontuacao = 0;
        int numAs = 0;
        for (Carta carta : mao) {
            int valor = carta.getValorNumerico();
            if (valor == 11) {
                numAs++;
            }
            pontuacao += valor;
        }
        while (pontuacao > 21 && numAs > 0) {
            pontuacao -= 10;
            numAs--;
        }
        return pontuacao;
    }

    public boolean temBusted() {
        return calcularPontuacao() > 21;
    }

    public String getNome() {
        return nome;
    }
}
