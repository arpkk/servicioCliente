package vista;


import modelo.CategoriaEnum;
import modelo.Cliente;
import servicio.*;
import utilidades.Utilidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Menu {

    ClienteServicio clienteservicio = new ClienteServicio();
    ExportadorTxtCsv exportartxtcsv = new ExportadorTxtCsv();
    ImportadorTxtCsv importarcsv = new ImportadorTxtCsv(clienteservicio.getListaCliente());
    String filename = "Clientes";
    Scanner scanner = new Scanner(System.in);

    public void iniciarMenu() throws Exception {
        List<String> opcionesMenu = new ArrayList<>();
        opcionesMenu.add("Listar Clientes");
        opcionesMenu.add("Agregar Clientes");
        opcionesMenu.add("Editar Clientes");
        opcionesMenu.add("Importar Datos");
        opcionesMenu.add("Exportar Datos");
        opcionesMenu.add("Terminar el programa");

        Menu menu = new Menu();
        menu.seleccionOpcion(opcionesMenu);
    }

    private void seleccionOpcion(List<String> opcionesMenu) throws Exception {
        boolean continuar = false;
        int resultado;

        do {
            resultado = construirMenu(opcionesMenu);
            switch (resultado) {
                case 1 -> listarCliente();
                case 2 -> agregarCliente();
                case 3 -> editarCliente();
                case 4 -> importarDatos();
                case 5 -> exportarDatos();
                case 6 -> terminarPrograma();
                default -> System.out.println("opcion no valida");
            }

        } while (!continuar);
    }

    private void listarCliente() {
        clienteservicio.listarClientes();
        Utilidad.stopAndContinue();
    }

    private void agregarCliente() {
        System.out.println("Crear cliente");
        scanner.nextLine();
        System.out.println("Ingrese el rut del cliente: ");
        String rut = scanner.nextLine();
        System.out.println("Ingrese nombre");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese apellido: ");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese anios: ");
        String anios = scanner.nextLine();
        Cliente clientenuevo = new Cliente(rut, nombre, apellido, anios, CategoriaEnum.Activo);
        clienteservicio.agregarCliente(clientenuevo);
        Utilidad.stopAndContinue();
    }

    private void editarCliente() {

        System.out.println("-------------Editar Cliente-------------");
        System.out.println("Seleccione qué desea hacer:");
        System.out.println("1.-Cambiar el estado del Cliente");
        System.out.println("2.-Editar los datos ingresados del Cliente");
        System.out.println("Ingrese opcion:");
        System.out.println("----------------------------------------");
        scanner.nextLine();
        String eleccion = scanner.nextLine();

        if (Objects.equals(eleccion, "1")) {
            System.out.println("Ingrese RUN del Cliente a editar:");
            String rutElegido = scanner.nextLine();
            clienteservicio.editarClienteCategoria(rutElegido);
        } else if (Objects.equals(eleccion, "2")) {
            System.out.println("Ingrese RUN del Cliente a editar:");
            String rutElegido = scanner.nextLine();
            clienteservicio.editarCliente(rutElegido);
        } else {
            System.out.println("Seleccion no permitida");
        }
        Utilidad.stopAndContinue();
    }


    private void importarDatos() throws Exception {
        System.out.println("---------Cargar Datos en Linux o Mac-----------");
        System.out.println("Ingresa la ruta en donde se encuentra el archivo DBClientes.csv:");
        scanner.nextLine();
        String direccion = scanner.nextLine();
        importarcsv.importar(direccion);
        Utilidad.stopAndContinue();
    }

    private void exportarDatos() {
        System.out.println("---------Exportar Datos-----------");
        System.out.println("Seleccione el formato a exportar:");
        System.out.println("1.-Formato csv");
        System.out.println("2.-Formato txt");
        System.out.println("Ingrese una opción para exportar:");
        System.out.println("----------------------------------");
        scanner.nextLine();
        String eleccion = scanner.nextLine();
        String ruta;

        if (Objects.equals(eleccion, "1")) {
            System.out.println("Ingresa la ruta en donde desea exportar el archivo clientes.csv:");
            ruta = scanner.nextLine();
            exportartxtcsv.exportar(ruta + filename + ".csv", clienteservicio.getListaCliente());
        } else if (Objects.equals(eleccion, "2")) {
            System.out.println("Ingresa la ruta en donde desea exportar el archivo clientes.txt:");
            ruta = scanner.nextLine();
            exportartxtcsv.exportar(ruta + filename + ".txt", clienteservicio.getListaCliente());
        } else {
            System.out.println("Seleccion no permitida");
        }
        Utilidad.stopAndContinue();
    }


    private void terminarPrograma() {
        Utilidad.stop();
    }

    private int construirMenu(List<String> opcionesMenu) {

        int largo = opcionesMenu.size();

        for (int i = 0; i < largo; i++) {
            System.out.println(i + 1 + " " + opcionesMenu.get(i));
        }

        int opcion;
        System.out.println("Ingrese una opcion: ");

        opcion = scanner.nextInt();

        if (opcion < 1 || opcion >= largo + 1) {
            System.out.println("Seleccion no permitida");
        }

        return opcion;
    }
}
