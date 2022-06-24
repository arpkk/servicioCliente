package servicio;

import modelo.CategoriaEnum;
import modelo.Cliente;
import utilidades.Utilidad;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class ImportadorTxtCsv extends Importador {
    List<Cliente> listacliente;

    public ImportadorTxtCsv(List<Cliente> listacliente) {
        this.listacliente = listacliente;
    }

    public void importar(String fileName) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        String rut;
        String nombre;
        String apellido;
        String anios;
        String categoria;
        while (sc.hasNextLine()) {
            try {
                rut = sc.next();
                nombre = sc.next();
                apellido = sc.next();
                anios = sc.next();
                categoria = sc.nextLine();
                categoria = categoria.replace(",", "");
                CategoriaEnum nuevaCategoria = CategoriaEnum.valueOf(categoria);
                Cliente nuevo = new Cliente(rut, nombre, apellido, anios, nuevaCategoria);
                listacliente.add(nuevo);
                nuevo.mostrar();
            } catch (Exception e) {
                System.out.println("Error: Formato del archivo no funciona");
            }
        }
        sc.close();
        Utilidad.stopAndContinue();
    }
}
