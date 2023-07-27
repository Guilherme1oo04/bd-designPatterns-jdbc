package com.Guilherme.acesso.modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection criar(String urlDb, String driverDb, String user, String password){

        Connection conexao = null;

        try{
            Class.forName(driverDb);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            conexao = DriverManager.getConnection(urlDb, user, password);
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return conexao;
    }
}
