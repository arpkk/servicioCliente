package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;
import utilidades.Utilidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ClienteServicio {
    List<Cliente> listaCliente;

    public ClienteServicio() {
        listaCliente = new ArrayList<>();
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaProductos) {
        this.listaCliente = listaProductos;
    }

    public void listarClientes() {
        if (listaCliente != null) {

            for (Cliente cliente : listaCliente) {
                cliente.mostrar();
            }

        } else {
            System.out.println("No se pueden listar los productos, lista vacia");
        }
        Utilidad.stopAndContinue();
    }

    public void agregarCliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente, CategoriaEnum categoria) {
        //crear un producto
        Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, categoria);

        //agregar producto a la lista
        if (listaCliente != null) {
            listaCliente.add(cliente);
            cliente.mostrar();
        } else {
            System.out.println("El producto o la lista estan nulos, no se puede agregar");
        }
    }

    public Cliente buscarCliente(String rut) {
        try {
            for (Cliente cliente : listaCliente) {
                if (Objects.equals(cliente.getRunCliente(), rut)) {
                    return cliente;
                }
            }
        } catch (Exception e) {
            System.out.println("No se encontro el cliente");
        }
        return null;
    }

    public void editarCliente(String rut) {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = buscarCliente(rut);
        try {
            System.out.println("----Actualizando datos del Cliente-----");
            System.out.println("1.-El RUN del Cliente es: " + cliente.getRunCliente());
            System.out.println("2.-El Nombre del Cliente es: " + cliente.getNombreCliente());
            System.out.println("3.-El Apellido del Cliente es: " + cliente.getApellidoCliente());
            System.out.println("4.-Los años como Cliente son: " + cliente.getNombreCliente() + " años");
            System.out.println("Ingrese opcion a editar de los datos del cliente:");
            System.out.println("----------------------------------------");

            String eleccion = scanner.nextLine();

            switch (eleccion) {
                case "1" -> {
                    System.out.println("Ingrese nuevo rut: ");
                    String rutt = scanner.nextLine();
                    cliente.setRunCliente(rutt);
                }
                case "2" -> {
                    System.out.println("Ingrese nuevo nombre: ");
                    String nombre = scanner.nextLine();
                    cliente.setNombreCliente(nombre);
                }
                case "3" -> {
                    System.out.println("Ingrese nuevo apellido: ");
                    String apellido = scanner.nextLine();
                    cliente.setApellidoCliente(apellido);
                }
                case "4" -> {
                    System.out.println("Ingrese nueva edad: ");
                    String edad = scanner.nextLine();
                    cliente.setAniosCliente(edad);
                }
                default -> System.out.println("opcion no valida");
            }
            System.out.println("Datos cambiados con éxito");
            cliente.mostrar();
        } catch (Exception e) {
            System.out.println("Error: La lista esta vacia o no existe el rut");
        }
    }

    public void editarClienteCategoria(String rut) {
        Scanner scanner = new Scanner(System.in);
        Cliente cliente = buscarCliente(rut);
        try {
            System.out.println("-----Actualizando estado del Cliente----");
            System.out.println("El estado actual es: " + cliente.getNombreCategoria());
            System.out.println("1.-Si desea cambiar el estado del Cliente a Inactivo");
            System.out.println("2.-Si desea mantener el estado del cliente Activo");
            System.out.println("Ingrese opcion:");
            System.out.println("----------------------------------------");

            String eleccion = scanner.nextLine();

            if (Objects.equals(eleccion, "1")) {
                cliente.setNombreCategoria(CategoriaEnum.Inactivo);
            } else if (Objects.equals(eleccion, "2")) {
                cliente.setNombreCategoria(CategoriaEnum.Activo);
            } else {
                System.out.println("Seleccion no permitida");
                Utilidad.stopAndContinue();
            }

            System.out.println("Datos cambiados con éxito");
            cliente.mostrar();
        } catch (Exception e) {
            System.out.println("Error: La lista esta vacia o no existe el rut");
        }
    }

}