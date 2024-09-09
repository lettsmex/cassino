public class Carta {
    private String naipe;
    private String valor;

    public Carta(String naipe, String valor) {
        this.naipe = naipe;
        this.valor = valor;
    }

    public String getNaipe() {
        return naipe;
    }

    public String getValor() {
        return valor;
    }

    public int getValorNumerico() {
        switch (valor) {
            case "A" -> {
                return 11;
            }
            case "2", "3", "4", "5", "6", "7", "8", "9", "10" -> {
                return Integer.parseInt(valor);
            }
            case "J", "Q", "K" -> {
                return 10;
            }
            default -> throw new IllegalArgumentException("Valor da carta inválido");
        }
    }

    @Override
    public String toString() {
        return valor + " de " + naipe;
    }
}
