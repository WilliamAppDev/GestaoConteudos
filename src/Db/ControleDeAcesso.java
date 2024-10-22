package Db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ControleDeAcesso {
    private Connection conn;

    public ControleDeAcesso() {
        try {
            // Carregar o driver JDBC
            Class.forName("org.postgresql.Driver");

            // Estabelecer conexão com o banco de dados
            String url = "jdbc:postgresql://localhost:5432/projeto_final";
            String usuario = "postgres";
            String senha = "postgres";
            conn = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conexão estabelecida com sucesso.");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar com o banco de dados.");
            e.printStackTrace();
        }
    }

    public ResultSet executarQuery(String sql) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao executar query.");
            e.printStackTrace();
            return null;
        }
    }

    public int executarUpdate(String sql) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao executar update.");
            e.printStackTrace();
            return -1;
        }
    }

    public void fecharConexao() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Conexão encerrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão.");
            e.printStackTrace();
        }
    }
}
