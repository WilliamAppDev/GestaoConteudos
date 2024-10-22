package Db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestaoDeUsuarios {
    private ControleDeAcesso db;

    public GestaoDeUsuarios(ControleDeAcesso db) {
        this.db = db;
    }

    public void cadastrarUsuario(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        String sql = "INSERT INTO Usuarios (Nome, Senha) VALUES ('" + nome + "', '" + senha + "')";
        int res = db.executarUpdate(sql);
        if (res > 0) {
            System.out.println("Usuário cadastrado com sucesso!");
        } else {
            System.out.println("Erro ao cadastrar usuário.");
        }
    }

    public void listarUsuarios() {
        String sql = "SELECT ID, Nome FROM Usuarios";
        ResultSet rs = db.executarQuery(sql);

        try {
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhum usuário encontrado.");
                return;
            }

            System.out.println("Usuários cadastrados:");
            while (rs.next()) {
                int id = rs.getInt("ID");
                String nome = rs.getString("Nome");
                System.out.println("ID: " + id + " | Nome: " + nome);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void alterarUsuario(Scanner scanner, int usuarioID) {
        System.out.print("Novo nome de usuário: ");
        String novoNome = scanner.nextLine();

        String sql = "UPDATE Usuarios SET Nome = '" + novoNome + "' WHERE ID = " + usuarioID;
        int res = db.executarUpdate(sql);
        if (res > 0) {
            System.out.println("Nome de usuário atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar nome de usuário.");
        }
    }

    public void excluirUsuario(Scanner scanner) {
        System.out.print("ID do usuário a ser excluído: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir o newline

        String sql = "DELETE FROM Usuarios WHERE ID = " + id;
        int res = db.executarUpdate(sql);
        if (res > 0) {
            System.out.println("Usuário excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir usuário.");
        }
    }

    public void alterarSenha(Scanner scanner, int usuarioID) {
        System.out.print("Nova senha: ");
        String novaSenha = scanner.nextLine();

        String sql = "UPDATE Usuarios SET Senha = '" + novaSenha + "' WHERE ID = " + usuarioID;
        int res = db.executarUpdate(sql);
        if (res > 0) {
            System.out.println("Senha atualizada com sucesso!");
        } else {
            System.out.println("Erro ao atualizar senha.");
        }
    }

    public int loginUsuario(Scanner scanner) {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Senha: ");
        String senha = scanner.nextLine();

        String sql = "SELECT ID FROM Usuarios WHERE Nome = '" + nome + "' AND Senha = '" + senha + "'";
        ResultSet rs = db.executarQuery(sql);

        try {
            if (rs.next()) {
                int usuarioID = rs.getInt("ID");
                System.out.println("Login bem-sucedido!");
                return usuarioID;
            } else {
                System.out.println("Login falhou. Usuário ou senha incorretos.");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
