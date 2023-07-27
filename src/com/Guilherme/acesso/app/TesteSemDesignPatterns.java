package com.Guilherme.acesso.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TesteSemDesignPatterns {
    private static final String URL_MYSQL = "jdbc:mysql://localhost/testando_bd_jdbc";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
        Properties props = new Properties();

        try {
            FileInputStream fis = new FileInputStream(".env");
            props.load(fis);
        } catch (IOException e){
            e.printStackTrace();
        }

        String USER = props.getProperty("DB_USER");
        String PASSWORD = props.getProperty("DB_PASSWORD");

        try {

            Class.forName(DRIVER_CLASS);

            Connection conexao = DriverManager.getConnection(URL_MYSQL, USER, PASSWORD);

            System.out.println("Conectou!");

            Statement stmt = conexao.createStatement();

//            stmt.executeUpdate("INSERT INTO usuarios (nome_user) VALUES ('Guilherme Chaves')");

//            stmt.executeUpdate("INSERT INTO usuarios (nome_user) VALUES ('Guilherme da Silva')");

//            stmt.executeUpdate("UPDATE usuarios SET nome_user = 'Guilherme da Silva Chaves' WHERE id_user = 2");

//            String comandoDeletar = "DELETE FROM usuarios WHERE id_user = 2";
//            stmt.executeUpdate(comandoDeletar);

//            PreparedStatement pstmt = conexao.prepareStatement("INSERT INTO usuarios (nome_user) VALUES (?)");
//            pstmt.setString(1, "Guilherme da Silva");
//            pstmt.executeUpdate();

            // Os comandos abaixo chamam uma procedure
//            CallableStatement procedure = conexao.prepareCall("{call SP_INSERT_USER(?)}");
//            procedure.setString(1, "Fulano da Silva");
//            procedure.executeUpdate();

            ResultSet result = stmt.executeQuery("SELECT * FROM usuarios");

            while (result.next()){
                System.out.println("Id: " + result.getInt("id_user") + ", Nome: " + result.getString("nome_user"));
            }

            conexao.close();

        } catch (SQLException e){
            System.err.println("Não foi possível conectar ao banco de dados.");
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            System.err.println("O driver não foi encontrado.");
            e.printStackTrace();
        }
    }
}
