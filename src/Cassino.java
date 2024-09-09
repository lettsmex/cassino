import java.util.InputMismatchException;
import java.util.Scanner;

public class Cassino {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void exibirLogoCassino() {
        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║" + ANSI_RED +   "          _______   " + ANSI_GREEN +   "______   " + ANSI_YELLOW +   "______   " + ANSI_BLUE +   "______   " + ANSI_RESET + "       ║");
        System.out.println("║" + ANSI_RED +   "         |A      | " + ANSI_GREEN +   "|K     | " + ANSI_YELLOW +   "|Q     | " + ANSI_BLUE +   "|J     |  " + ANSI_RESET + "       ║");
        System.out.println("║" + ANSI_RED +   "         |       | " + ANSI_GREEN +   "|      | " + ANSI_YELLOW +   "|      | " + ANSI_BLUE +   "|      |  " + ANSI_RESET + "       ║");
        System.out.println("║" + ANSI_RED +   "         |      A| " + ANSI_GREEN +   "|     K| " + ANSI_YELLOW +   "|     Q| " + ANSI_BLUE +   "|     J|  " + ANSI_RESET + "       ║");
        System.out.println("║" + ANSI_RED +   "         |_______| " + ANSI_GREEN +   "|______| " + ANSI_YELLOW +   "|______| " + ANSI_BLUE +   "|______|  " + ANSI_RESET + "       ║");
        System.out.println("║                                                      ║");
        System.out.println("║" + ANSI_YELLOW + " Bem-vindo ao Cassino Virtual Santander Coders 2024!" + ANSI_RESET + "  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                exibirLogoCassino();

                System.out.println("Sistema de Cadastro e Login:\n");
                System.out.println("1. Cadastrar novo usuário");
                System.out.println("2. Fazer login");
                System.out.println("0. Sair\n");

                int escolha = lerEscolha(scanner);

                switch (escolha) {
                    case 1:
                        Usuario.iniciarCadastro();
                        break;
                    case 2:
                        Usuario usuarioLogado = Usuario.iniciarLogin();
                        if (usuarioLogado != null) {
                            menuPrincipal(usuarioLogado);
                        } else {
                            System.out.println("O login falhou. Tente novamente.");
                        }
                        break;
                    case 0:
                        System.out.println("Saindo...");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        }
    }

    private static void menuPrincipal(Usuario usuario) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\nEscolha o jogo que deseja jogar:\n");
                System.out.println("1. Blackjack");
                System.out.println("2. Roleta");
                System.out.println("3. Jogo de Dados");
                System.out.println("4. Raspadinha");
                System.out.println("5. Empréstimo Bancário Santander");
                System.out.println("0. Sair\n");
    
                int opcao = lerEscolha(scanner);
    
                switch (opcao) {
                    case 1:
                        Blackjack.iniciarBlackjack(usuario);
                        break;
                    case 2:
                        Roleta.iniciarRoleta(usuario);
                        break;
                    case 3:
                        JogoDeDados.iniciarJogoDeDados(usuario);
                        break;
                    case 4:
                        Raspadinha.iniciarJogo(usuario);
                        break;
                    case 5:
                        Santander.emprestimoSantander(usuario);
                        break;
                    case 0:
                        System.out.println("\nObrigado por jogar no Cassino Virtual!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
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
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); 
            }
        }
    }
}
