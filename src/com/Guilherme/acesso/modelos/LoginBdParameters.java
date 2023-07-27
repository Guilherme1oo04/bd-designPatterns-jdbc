package com.Guilherme.acesso.modelos;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoginBdParameters {
    private static Properties carregarParametros(){
        Properties props = new Properties();

        try {
            FileInputStream fis = new FileInputStream(".env");
            props.load(fis);
        } catch (IOException e){
            e.printStackTrace();
        }

        return props;
    }

    public static String user(){
        Properties props = carregarParametros();

        return props.getProperty("DB_USER");
    }

    public static String password(){
        Properties props = carregarParametros();

        return props.getProperty("DB_PASSWORD");
    }
}
