import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class JogoDeDados {

    public static void iniciarJogoDeDados(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean jogarNovamente = true;

        System.out.println("\nBem-vindo ao Jogo de Dados!");
        while (jogarNovamente) {
            System.out.println("\nSeu saldo atual é: R$ " + usuario.getSaldo());
            System.out.print("Escolha o valor da aposta: ");
            double valorAposta = scanner.nextDouble();

            if (valorAposta > usuario.getSaldo()) {
                System.out.println("Saldo insuficiente para realizar essa aposta.");
                return;
            }

            int numeroApostado = 0;
            while (numeroApostado < 2 || numeroApostado > 12) {
                try {
                    System.out.print("Aposte em um número de 2 a 12 (soma de dois dados): ");
                    numeroApostado = scanner.nextInt();
                    if (numeroApostado < 2 || numeroApostado > 12) {
                        System.out.println("Numero Inválido, por favor digite apenas números entre 2 e 12.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira apenas números.");
                    scanner.nextLine();
                }
            }


            int dado1 = random.nextInt(6) + 1;
            int dado2 = random.nextInt(6) + 1;
            int somaDados = dado1 + dado2;

            System.out.println("\nOs dados rolaram " + dado1 + " e " + dado2 + ". Soma: " + somaDados + "\n");

            if (numeroApostado == somaDados) {
                double ganho = valorAposta * 10;
                usuario.setSaldo(usuario.getSaldo() + ganho);
                System.out.println("Parabéns! Você ganhou R$ " + ganho + "! Seu novo saldo é: R$ " + usuario.getSaldo());
            } else {
                usuario.setSaldo(usuario.getSaldo() - valorAposta);
                System.out.println("Que pena! Você perdeu. Seu novo saldo é: R$ " + usuario.getSaldo());
            }

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
