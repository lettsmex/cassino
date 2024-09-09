import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    private final String nome;
    private final String senha;
    private final int idade;
    private double saldo;

    private static List<Usuario> usuarios = new ArrayList<>();

    public Usuario(String nome, String senha, int idade) {
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.saldo = 1000.00;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public static void iniciarCadastro() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cadastro de novo usuário:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        scanner.nextLine();

        if (idade < 18) {
            System.out.println("Desculpe, apenas maiores de 18 anos podem se cadastrar.\n");
            return;
        }

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome)) {
                System.out.println("Usuário já cadastrado.");
                return;
            }
        }

        Usuario novoUsuario = new Usuario(nome, senha, idade);
        usuarios.add(novoUsuario);
        System.out.println("Cadastro realizado com sucesso! Seu saldo inicial é de R$ 1.000,00.\n");
    }

    public static Usuario iniciarLogin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login de usuário:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        for (Usuario usuario : usuarios) {
            if (usuario.getNome().equals(nome) && usuario.senha.equals(senha)) {
                return usuario;
            }
        }

        System.out.println("Nome ou senha incorretos.");
        return null;
    }
}
