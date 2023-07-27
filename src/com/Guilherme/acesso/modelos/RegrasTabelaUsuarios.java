package com.Guilherme.acesso.modelos;

import java.util.List;

public interface RegrasTabelaUsuarios {

    void inserir (Usuario usuario);
    List<Usuario> buscarTodos();
    Usuario buscarPorId(long id);
    void removerPorId(long id);
    void fecharConexao();
}
