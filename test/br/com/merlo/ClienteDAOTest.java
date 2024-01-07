package br.com.merlo;

import br.com.merlo.dao.IClienteDAO;
import br.com.merlo.domain.Cliente;
import br.com.merlo.exception.TipoChaveNaoEncontradaException;
import br.com.merlo.mock.ClienteDAOMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ClienteDAOTest {
    private IClienteDAO clienteDAO;
    private Cliente cliente;

    public ClienteDAOTest(){
        //mocka direto pois o dao so depende de um bd, entao o constructor e diferente do servicetest
        clienteDAO = new ClienteDAOMock();
    }

    @Before
    public void init(){
        cliente = new Cliente();
        cliente.setCpf(1123456789L);
        cliente.setNome("matheus");
        cliente.setCidade("rc");
        cliente.setEstado("sp");
        cliente.setEnd("end");
        cliente.setNumero(10);
        cliente.setTel(123415252L);
    }

    @Test
    public void pesquisarCliente(){
        Cliente clienteConsultado = clienteDAO.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        clienteDAO.cadastrar(cliente);
    }

    @Test
    public void excluirCliente(){
        clienteDAO.excluir(cliente.getCpf());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("matheus merlo");
        clienteDAO.alterar(cliente);

        Assert.assertEquals("matheus merlo", cliente.getNome());
    }
}

