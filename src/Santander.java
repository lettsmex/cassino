import java.util.InputMismatchException;
import java.util.Scanner;

public class Santander {

    public static void emprestimoSantander(Usuario usuario) {
        if (usuario == null) {
            System.out.println("Usuário não autenticado. Retornando ao menu principal.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nBem-vindo ao Internet Banking Santander!");
        System.out.println("Seu saldo atual é de: R$ " + usuario.getSaldo());
        while (true) {
            System.out.print("\nDigite o valor do empréstimo que deseja obter: ");
            try {
                double valorDoEmprestimo = scanner.nextDouble();
                scanner.nextLine();
                if (valorDoEmprestimo <= 0) {
                    System.out.println("Valor inválido, por favor digite apenas valores maiores do que zero.");

                } else {
                    usuario.setSaldo(usuario.getSaldo() + valorDoEmprestimo);
                    System.out.println("\nSeu novo saldo é de R$ " + usuario.getSaldo());
                    System.out.println("O Santander agradece a sua confiança!");
                    break;
                }
            }
            catch (InputMismatchException e){
                System.out.println("Por favor, digite apenas números.");
                scanner.nextLine();
            }
        }
    }
}
