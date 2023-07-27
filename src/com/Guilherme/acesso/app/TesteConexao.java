package com.Guilherme.acesso.app;


import com.Guilherme.acesso.modelos.TabelaUsuarios;
import com.Guilherme.acesso.modelos.Usuario;

import java.util.List;

public class TesteConexao {

    public static void main(String[] args) {

        TabelaUsuarios bd = new TabelaUsuarios();

        List<Usuario> allUsers = bd.buscarTodos();

        allUsers.forEach(usuario -> {
            System.out.println("Id: " + usuario.getId() + ", Nome: " + usuario.getNome());
            System.out.println();
        });

        System.out.println(bd.buscarPorId(1));
        System.out.println(bd.buscarPorId(4));

        bd.fecharConexao();

    }
}
