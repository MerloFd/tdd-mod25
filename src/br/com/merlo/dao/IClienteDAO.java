package br.com.merlo.dao;

import br.com.merlo.dao.generic.IGenericDAO;
import br.com.merlo.domain.Cliente;

public interface IClienteDAO extends IGenericDAO<Cliente> {
    //usa os metodos do generic

//    public Boolean salvar(Cliente cliente);
//
//    public Cliente buscarPorCpf(Long cpf);
//
//    void excluir(Long cpf);
//
//    void alterar(Cliente cliente);
}
