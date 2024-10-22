package Db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestaoDeArtigos {
    private ControleDeAcesso db;

    public GestaoDeArtigos(ControleDeAcesso db) {
        this.db = db;
    }

    public void inserirConteudo(Scanner scanner, int usuarioID) {
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Conteúdo: ");
        String conteudo = scanner.nextLine();

        String sql = "INSERT INTO Conteudos (Titulo, Conteudo, UsuarioID) " +
                     "VALUES ('" + titulo + "', '" + conteudo + "', " + usuarioID + ")";
        int res = db.executarUpdate(sql);
        if (res > 0) {
            System.out.println("Conteúdo inserido com sucesso!");
        } else {
            System.out.println("Erro ao inserir conteúdo.");
        }
    }

    public void consultarConteudos(int usuarioID) {
        String sql = "SELECT Titulo, Conteudo FROM Conteudos WHERE UsuarioID = " + usuarioID;

        ResultSet rs = db.executarQuery(sql);

        try {
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhum conteúdo encontrado para este usuário.");
                return;
            }

            System.out.println("Conteúdos do Usuário:");
            System.out.println("------------------------------------------------------------");
            while (rs.next()) {
                String titulo = rs.getString("Titulo");
                String conteudo = rs.getString("Conteudo");

                System.out.println("Título: " + titulo);
                System.out.println("Conteúdo: " + conteudo);
                System.out.println("------------------------------------------------------------");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Função para consultar conteúdos públicos
    public void consultarConteudosPublicos() {
        String sql = "SELECT Titulo, Conteudo FROM Conteudos";

        ResultSet rs = db.executarQuery(sql);

        try {
            if (!rs.isBeforeFirst()) {
                System.out.println("Nenhum conteúdo disponível.");
                return;
            }

            System.out.println("Conteúdos Públicos:");
            System.out.println("------------------------------------------------------------");
            while (rs.next()) {
                String titulo = rs.getString("Titulo");
                String conteudo = rs.getString("Conteudo");

                System.out.println("Título: " + titulo);
                System.out.println("Conteúdo: " + conteudo);
                System.out.println("------------------------------------------------------------");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Função para atualizar conteúdo
    public void atualizarConteudo(Scanner scanner, int usuarioID) {
        System.out.print("ID do conteúdo a ser atualizado: ");
        int conteudoID = scanner.nextInt();
        scanner.nextLine();  // Consumir o newline

        System.out.print("Novo título: ");
        String novoTitulo = scanner.nextLine();
        System.out.print("Novo conteúdo: ");
        String novoConteudo = scanner.nextLine();

        String sql = "UPDATE Conteudos SET Titulo = '" + novoTitulo + "', Conteudo = '" + novoConteudo + "' " +
                     "WHERE ID = " + conteudoID + " AND UsuarioID = " + usuarioID;
        int res = db.executarUpdate(sql);
        if (res > 0) {
            System.out.println("Conteúdo atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar conteúdo.");
        }
    }

    // Função para excluir conteúdo
    public void excluirConteudo(Scanner scanner, int usuarioID) {
        System.out.print("ID do conteúdo a ser excluído: ");
        int conteudoID = scanner.nextInt();
        scanner.nextLine();  // Consumir o newline

        String sql = "DELETE FROM Conteudos WHERE ID = " + conteudoID + " AND UsuarioID = " + usuarioID;
        int res = db.executarUpdate(sql);
        if (res > 0) {
            System.out.println("Conteúdo excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir conteúdo.");
        }
    }
}
