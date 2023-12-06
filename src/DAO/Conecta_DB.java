package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conecta_DB {

    private final String url = "jdbc:mysql://localhost:3306/agenda_database";
    private final String user = "root";
    private final String password = "root";
    private Connection connection;

    public Connection connectDB() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Conexão bem-sucedida ao banco de dados!");
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao conectar-se ao banco de dados: " + e.getMessage());
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Conexão com o banco de dados fechada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao fechar a conexão com o banco de dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Conecta_DB mySQLConnection = new Conecta_DB();
        Connection connection = mySQLConnection.connectDB();

        // Faça operações no banco de dados aqui
        // ...
        mySQLConnection.closeConnection();
    }
}