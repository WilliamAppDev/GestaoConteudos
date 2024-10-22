package main;

import java.util.Scanner;
import Db.ControleDeAcesso;
import Db.GestaoDeUsuarios;
import Db.GestaoDeArtigos;

public class Principal {
    public static void main(String[] args) {
        ControleDeAcesso db = new ControleDeAcesso();
        GestaoDeUsuarios gestaoDeUsuarios = new GestaoDeUsuarios(db);
        GestaoDeArtigos gestaoDeArtigos = new GestaoDeArtigos(db);

        Scanner scanner = new Scanner(System.in);

        // Menu inicial de login ou listar conteúdos públicos
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Login");
            System.out.println("2 - Listar Conteúdos (Público)");
            System.out.println("3 - Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine();  // Consumir o newline

            int usuarioID = -1;

            switch (escolha) {
                case 1:
                    usuarioID = gestaoDeUsuarios.loginUsuario(scanner);
                    if (usuarioID != -1) {
                        menuUsuarioLogado(scanner, db, gestaoDeUsuarios, gestaoDeArtigos, usuarioID);
                    }
                    break;
                case 2:
                    gestaoDeArtigos.consultarConteudosPublicos();
                    break;
                case 3:
                    db.fecharConexao();
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void menuUsuarioLogado(Scanner scanner, ControleDeAcesso db, GestaoDeUsuarios gestaoDeUsuarios, GestaoDeArtigos gestaoDeArtigos, int usuarioID) {
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Criar Conteúdo");
            System.out.println("2 - Listar Conteúdo");
            System.out.println("3 - Atualizar Conteúdo");
            System.out.println("4 - Excluir Conteúdo");
            System.out.println("5 - Criar Usuário");
            System.out.println("6 - Listar Usuário");
            System.out.println("7 - Alterar Usuário");
            System.out.println("8 - Excluir Usuário");
            System.out.println("9 - Alterar Senha");
            System.out.println("10 - Logout");

            int escolha = scanner.nextInt();
            scanner.nextLine();  // Consumir o newline

            switch (escolha) {
                case 1:
                    gestaoDeArtigos.inserirConteudo(scanner, usuarioID);
                    break;
                case 2:
                    gestaoDeArtigos.consultarConteudos(usuarioID);
                    break;
                case 3:
                    gestaoDeArtigos.atualizarConteudo(scanner, usuarioID);
                    break;
                case 4:
                    gestaoDeArtigos.excluirConteudo(scanner, usuarioID);
                    break;
                case 5:
                    gestaoDeUsuarios.cadastrarUsuario(scanner);
                    break;
                case 6:
                    gestaoDeUsuarios.listarUsuarios();
                    break;
                case 7:
                    gestaoDeUsuarios.alterarUsuario(scanner, usuarioID);
                    break;
                case 8:
                    gestaoDeUsuarios.excluirUsuario(scanner);
                    break;
                case 9:
                    gestaoDeUsuarios.alterarSenha(scanner, usuarioID);
                    break;
                case 10:
                    System.out.println("Deslogando...");
                    return;  // Retorna ao menu inicial
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
