package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ClienteServicioTest {
    private final ClienteServicio servicioCliente = mock(ClienteServicio.class);

    @Test
    public void testListarClientes() {
        doNothing().when(servicioCliente).listarClientes();
        servicioCliente.listarClientes();
        verify(servicioCliente, times(1)).listarClientes();
    }

    @Test
    public void testAgregarCliente() {
        Cliente maria = new Cliente("maria", "12345-k", "diaz", "23", CategoriaEnum.Activo);
        when(servicioCliente.agregarCliente(maria)).thenReturn("Cliente creado");
        String respuesta = servicioCliente.agregarCliente(maria);
        Assert.assertEquals("Cliente creado", respuesta);
        verify(servicioCliente).agregarCliente(maria);
    }

    @Test
    public void testBuscarCliente() {
        Cliente maria = new Cliente("111", "maria", "diaz", "23", CategoriaEnum.Activo);
        when(servicioCliente.buscarCliente(anyString())).thenReturn(maria);
        Cliente cliente = servicioCliente.buscarCliente(maria.getRunCliente());
        Assert.assertEquals(maria,cliente);
    }

    @Test
    public void testEditarCliente() {
        Cliente maria = new Cliente("12345-k", "maria", "diaz", "23", CategoriaEnum.Activo);
        when(servicioCliente.editarCliente(maria.getRunCliente())).thenReturn("Cliente editado");
        String respuesta = servicioCliente.editarCliente(maria.getRunCliente());
        Assert.assertEquals("Cliente editado", respuesta);
        verify(servicioCliente).editarCliente(maria.getRunCliente());
    }
}