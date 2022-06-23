package servicio;

import modelo.Cliente;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ExportadorTxtCsv extends Exportador {
    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {

        if (listaClientes == null) {
            System.out.println("La lista esta vacia");
        } else {

            System.out.println("Exportar Datos");
            String separador = ",";
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            System.out.println("Archivo creado en la ruta: " + file.getAbsolutePath());

            try {
                PrintWriter printWriter = new PrintWriter(new FileWriter(file));

                for (Cliente cliente : listaClientes) {
                    printWriter
                            .append(cliente.getRunCliente())
                            .append(separador)
                            .append(cliente.getNombreCliente())
                            .append(separador)
                            .append(cliente.getApellidoCliente())
                            .append(separador)
                            .append(cliente.getAniosCliente())
                            .append(separador)
                            .append(cliente.getNombreCategoria().toString())
                            .append("\n");

                }

                printWriter.close();
                System.out.println("Datos exportados correctamente");

            } catch (IOException ex) {
                System.out.println("El archivo no pudo ser creado");
            } finally {
                System.out.println("**********************************");
            }
        }
    }
}

