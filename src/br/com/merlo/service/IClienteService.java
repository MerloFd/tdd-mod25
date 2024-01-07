package br.com.merlo.service;

import br.com.merlo.domain.Cliente;
import br.com.merlo.exception.TipoChaveNaoEncontradaException;

public interface IClienteService {
    public Boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException;

    public Cliente buscarPorCpf(Long cpf);

    void excluir(Long cpf);

    void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException ;
}
