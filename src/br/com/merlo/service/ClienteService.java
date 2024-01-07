package br.com.merlo.service;

import br.com.merlo.domain.Cliente;
import br.com.merlo.dao.IClienteDAO;
import br.com.merlo.exception.TipoChaveNaoEncontradaException;

public class ClienteService implements IClienteService {

    //precisa de um atributo dao pra acessar o bd
    private IClienteDAO clienteDAO;

    public ClienteService(IClienteDAO dao){
        this.clienteDAO=dao;
    }

    @Override
    public Boolean salvar(Cliente cliente) throws TipoChaveNaoEncontradaException {
        return clienteDAO.cadastrar(cliente);
    }

    @Override
    public Cliente buscarPorCpf(Long cpf) {
        return clienteDAO.consultar(cpf);
    }

    @Override
    public void excluir(Long cpf) {
        //buscar no map (ou bd) pelo cpf e dar um excluir
        clienteDAO.excluir(cpf);
    }

    @Override
    public void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException  {
        clienteDAO.alterar(cliente);
    }
}
