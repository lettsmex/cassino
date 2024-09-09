import java.util.Scanner;

public class Blackjack {

    public static void iniciarBlackjack(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Usuário não autenticado. Retornando ao menu principal.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            Baralho baralho = new Baralho();

            System.out.println("\nBem-vindo ao Blackjack!");
            System.out.println("\nSeu saldo atual é: R$ " + usuario.getSaldo());
            System.out.print("\nDigite o valor da aposta: ");
            double valorAposta = scanner.nextDouble();
            scanner.nextLine();

            if (valorAposta > usuario.getSaldo()) {
                System.out.println("Saldo insuficiente para realizar essa aposta, considere fazer um empréstimo.");
                return;
            }

            Jogador jogador = new Jogador(usuario.getNome());
            Jogador dealer = new Jogador("Dealer");

            jogador.receberCarta(baralho.distribuirCarta());
            jogador.receberCarta(baralho.distribuirCarta());
            dealer.receberCarta(baralho.distribuirCarta());
            dealer.receberCarta(baralho.distribuirCarta());

            System.out.println(jogador.mostrarMao());
            System.out.println("Pontuação do jogador: " + jogador.calcularPontuacao());

            while (!jogador.temBusted()) {
                System.out.print("Deseja 'c' para COMPRAR ou 'p' para PARAR? ");
                String acao = scanner.nextLine();
                if (acao.equalsIgnoreCase("c")) {
                    jogador.receberCarta(baralho.distribuirCarta());
                    System.out.println(jogador.mostrarMao());
                    System.out.println("Pontuação do jogador: " + jogador.calcularPontuacao());
                } else if (acao.equalsIgnoreCase("p")) {
                    System.out.println();
                    break;
                }
            }


            System.out.println(dealer.mostrarMao());
            while (dealer.calcularPontuacao() < 17) {
                dealer.receberCarta(baralho.distribuirCarta());
                System.out.println(dealer.mostrarMao());
            }
            System.out.println("\nPontuação do dealer: " + dealer.calcularPontuacao());
            System.out.println("Pontuação do jogador: " + jogador.calcularPontuacao() +"\n");

            int pontuacaoJogador = jogador.calcularPontuacao();
            int pontuacaoDealer = dealer.calcularPontuacao();

            if (jogador.temBusted()) {
                System.out.println("Você estourou. Dealer venceu!");
                usuario.setSaldo(usuario.getSaldo() - valorAposta);
            } else if (pontuacaoDealer > 21 || pontuacaoJogador > pontuacaoDealer) {
                System.out.println("Você venceu!");
                usuario.setSaldo(usuario.getSaldo() + valorAposta);
            } else if (pontuacaoJogador < pontuacaoDealer) {
                System.out.println("Dealer venceu!");
                usuario.setSaldo(usuario.getSaldo() - valorAposta);
            } else {
                System.out.println("Empate!");
            }

            System.out.println("Seu novo saldo é de: R$ " + usuario.getSaldo());

            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Jogar novamente");
            System.out.println("2. Voltar ao menu inicial");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (usuario.getSaldo() == 0){
                        System.out.println("Saldo insuficiente para realizar apostas, considere fazer um empréstimo.");
                        jogarNovamente = false;
                    }
                    break;
                case 2:
                    jogarNovamente = false;
                    Cassino.main(null);
                    break;
                case 0:
                    jogarNovamente = false;
                    System.out.println("Obrigado por jogar Blackjack!");
                    break;
                default:
                    System.out.println("Opção inválida. Saindo do jogo.");
                    jogarNovamente = false;
                    break;
            }
        }
    }
}
