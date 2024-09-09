import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Roleta {

    public static void iniciarRoleta(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean jogarNovamente = true;

        while (jogarNovamente) {
            System.out.println("Bem-vindo à Roleta!");

            System.out.println("Seu saldo atual é: R$ " + usuario.getSaldo());
            System.out.print("Escolha o valor da aposta: ");
            double valorAposta = scanner.nextDouble();

            if (valorAposta > usuario.getSaldo()) {
                System.out.println("Saldo insuficiente para realizar essa aposta.");
                return;
            }

            int numeroApostado = -1;
            while(numeroApostado < 0 || numeroApostado > 36) {
                try {
                    System.out.print("Aposte em um número de 0 a 36: ");
                    numeroApostado = scanner.nextInt();
                    scanner.nextLine();
                    if (numeroApostado < 0 || numeroApostado > 36) {
                        System.out.println("Numero Inválido, por favor digite apenas números entre 0 e 36.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada inválida. Por favor, insira apenas números.");
                    scanner.nextLine();
                }
            }

            String corApostada = "";
            while (!corApostada.equals("VERMELHO") && !corApostada.equals("PRETO")) {
                System.out.print("Escolha uma cor (Vermelho ou Preto): ");
                corApostada = scanner.nextLine();
                corApostada = corApostada.toUpperCase();

                if (!corApostada.equals("VERMELHO") && !corApostada.equals("PRETO")) {
                    System.out.println("Cor inválida. Por favor, escolha entre VERMELHO ou PRETO.");
                }
            }

            int numeroSorteado = random.nextInt(37);

            String corSorteada;
            if (random.nextBoolean()) {
                corSorteada = "Vermelho";
            } else {
                corSorteada = "Preto";
            }

            System.out.println("A roleta parou no número " + numeroSorteado + " e na cor " + corSorteada + ".");

            if (numeroApostado == numeroSorteado && corApostada.equalsIgnoreCase(corSorteada)) {
                double ganho = valorAposta * 20;
                usuario.setSaldo(usuario.getSaldo() + ganho);
                System.out.println("Parabéns! Você ganhou R$ " + ganho + "! Seu novo saldo é de: R$ " + usuario.getSaldo());
            } else {
                usuario.setSaldo(usuario.getSaldo() - valorAposta);
                System.out.println("Que pena! Você perdeu. Seu novo saldo é de: R$ " + usuario.getSaldo());
            }

            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Jogar novamente");
            System.out.println("2. Voltar ao menu inicial");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    if (usuario.getSaldo() == 0) {
                        System.out.println("Saldo insuficiente para realizar apostas, considere fazer um empréstimo.");
                        jogarNovamente = false;
                    }
                        break;
                case 2:
                    jogarNovamente = false;
                    break;
                case 0:
                    jogarNovamente = false;
                    System.out.println("Obrigado por jogar Roleta!");
                    break;
                default:
                    System.out.println("Opção inválida. Saindo do jogo.");
                    jogarNovamente = false;
                    break;
            }
        }
    }
}
