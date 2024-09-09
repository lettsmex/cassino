import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Raspadinha {

    public static void iniciarJogo(Usuario usuario) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean jogarNovamente = true;

        System.out.println("Bem-vindo à Raspadinha!");
        while (jogarNovamente) {
            System.out.println("Seu saldo atual é: R$ " + usuario.getSaldo());
            System.out.print("Escolha o valor da aposta: ");
            double valorAposta = lerDouble(scanner);

            if (valorAposta > usuario.getSaldo()) {
                System.out.println("Saldo insuficiente para realizar essa aposta.");
                return;
            }


            String[] opcoes = new String[20];
            for (int i = 0; i < opcoes.length; i++) {
                opcoes[i] = "Nada";
            }

            // Distribuição dos prêmios
            int[] indicesPremios = {0, 1, 2, 3, 4, 5, 6}; // Índices fixos para prêmios específicos
            opcoes[indicesPremios[0]] = "10x o valor apostado";
            opcoes[indicesPremios[1]] = "5x o valor apostado";
            opcoes[indicesPremios[2]] = "Dobro do valor apostado";
            opcoes[indicesPremios[3]] = "R$ 500";
            opcoes[indicesPremios[4]] = "R$ 200";
            opcoes[indicesPremios[5]] = "R$ 100";
            opcoes[indicesPremios[6]] = "R$ 50";


            for (int i = opcoes.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                String temp = opcoes[i];
                opcoes[i] = opcoes[j];
                opcoes[j] = temp;
            }

            System.out.println("\nEscolha uma das 20 opções:");
            for (int i = 0; i < 20; i++) {
                System.out.printf("%-15s", "Opção " + (i + 1));
                if ((i + 1) % 4 == 0) {
                    System.out.println();
                }
            }
            int escolha = lerEscolha(scanner);

            if (escolha < 1 || escolha > 20) {
                System.out.println("Opção inválida. Tente novamente.");
                return;
            }


            String premioEscolhido = opcoes[escolha - 1];
            System.out.println("\nVocê escolheu a opção " + escolha);
            System.out.println("\nResultado: " + premioEscolhido);

            double valorPremio = switch (premioEscolhido) {
                case "10x o valor apostado" -> valorAposta * 10;
                case "5x o valor apostado" -> valorAposta * 5;
                case "Dobro do valor apostado" -> valorAposta * 2;
                case "R$ 500" -> 500;
                case "R$ 200" -> 200;
                case "R$ 100" -> 100;
                case "R$ 50" -> 50;
                default -> 0;
            };

            if (valorPremio > 0) {
                usuario.setSaldo(usuario.getSaldo() + valorPremio);
                System.out.println("Parabéns! Você ganhou " + premioEscolhido + "!");
            } else {
                usuario.setSaldo(usuario.getSaldo() - valorAposta);
                System.out.println("Você não ganhou nada. Tente novamente!");
            }


            System.out.println("\nO que havia nas outras raspadinhas:");

            for (int i = 0; i < opcoes.length; i++) {
                if (i == escolha - 1) {
                    System.out.printf("%-30s", (i + 1) + ". Sua escolha");
                } else {
                    System.out.printf("%-30s", (i + 1) + ". " + opcoes[i]);
                }

                if ((i + 1) % 4 == 0 || i == opcoes.length - 1) {
                    System.out.println();
                }
            }

            System.out.println("\nSeu novo saldo é de: R$ " + usuario.getSaldo());

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

    private static double lerDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                scanner.nextLine(); 
            }
        }
    }

    private static int lerEscolha(Scanner scanner) {
        while (true) {
            System.out.print("Escolha uma opção: ");
            try {
                int escolha = scanner.nextInt();
                scanner.nextLine(); 
                return escolha;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido.");
                scanner.nextLine(); 
            }
        }
    }
}
