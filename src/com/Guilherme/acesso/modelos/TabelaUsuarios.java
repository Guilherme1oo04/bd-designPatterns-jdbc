package com.Guilherme.acesso.modelos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabelaUsuarios implements RegrasTabelaUsuarios{
    private static final String USER = LoginBdParameters.user();
    private static final String PASSWORD = LoginBdParameters.password();
    private static final String URL_MYSQL = "jdbc:mysql://localhost/testando_bd_jdbc";
    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final Connection conexao = DbConnection.criar(URL_MYSQL, DRIVER_CLASS, USER, PASSWORD);

    @Override
    public void inserir(Usuario usuario) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conexao.prepareStatement("INSERT INTO usuarios (nome_user) VALUES (?)");
            pstmt.setString(1, usuario.getNome());
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();

        } finally {
            try {
                if (pstmt != null){
                    pstmt.close();
                }
                System.out.println("Cadastro realizado com sucesso!");

            } catch (SQLException e){
                e.printStackTrace();

            }
        }
    }

    @Override
    public List<Usuario> buscarTodos() {
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();

        PreparedStatement pstmt = null;
        try {
            pstmt = conexao.prepareStatement("SELECT * FROM usuarios");
            ResultSet result = pstmt.executeQuery();

            Usuario user = null;

            while (result.next()){
                user = new Usuario(result.getString("nome_user"));
                user.setId(result.getLong("id_user"));
                listaUsuarios.add(user);
            }

        } catch (SQLException e){
            e.printStackTrace();

        } finally {
            try {
                if (pstmt != null){
                    pstmt.close();
                }

            } catch (SQLException e){
                e.printStackTrace();

            }
        }

        return listaUsuarios;
    }

    @Override
    public Usuario buscarPorId(long id){
        Usuario user = null;

        PreparedStatement pstmt = null;
        try {
            pstmt = conexao.prepareStatement("SELECT * FROM usuarios WHERE id_user = ?");
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();

            if (result.next()){
                user = new Usuario(result.getString("nome_user"));
                user.setId(result.getLong("id_user"));
                return user;
            }

        } catch (SQLException e){
            e.printStackTrace();

        } finally {
            try {
                if (pstmt != null){
                    pstmt.close();
                }

            } catch (SQLException e){
                e.printStackTrace();

            }
        }

        return user;
    }


    @Override
    public void removerPorId(long id) {
        PreparedStatement pstmt = null;

        try {
            pstmt = conexao.prepareStatement("DELETE FROM usuarios WHERE id_user = ?");
            pstmt.setLong(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();

        } finally {
            try {
                if (pstmt != null){
                    pstmt.close();
                }
                System.out.println("Usuário deletado com sucesso!");

            } catch (SQLException e){
                e.printStackTrace();

            }
        }
    }

    @Override
    public void fecharConexao() {
        try {
            conexao.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
