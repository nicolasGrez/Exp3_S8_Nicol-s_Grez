/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package exp3_s8_nicolás_grez;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 *
 * @author admin
 */
public class Exp3_S8_Nicolás_Grez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try ( Scanner sc = new Scanner(System.in)) {
            String[] zonas = {"Zona A", "Zona B", "Zona C"};
            int[] preciosNetos = {30000, 18000, 13000};
            int[] asientosDisponibles = {1, 12, 12};
            String[] clientes = new String[36];
            int[] edadesClientes = new int[36];
            String[] asientosReservados = new String[36];
            
            ArrayList<Double> descuentosAplicados = new ArrayList<>();
            ArrayList<String> tipoCliente = new ArrayList<>();
            ArrayList<Double> montosFinales = new ArrayList<>();
            
            int totalClientes = 0;
            int estudiantes = 0;
            int adultosMayores = 0;
            boolean salir = false;
            
            while (!salir) {
                // Menú principal
                System.out.println("\n--- TEATRO MORO ---");
                System.out.println("1. Mostrar Zonas disponibles");
                System.out.println("2. Comprar entradas");
                System.out.println("3. Eliminar reserva existentes");
                System.out.println("4. Imprimir boleta");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opcion: ");
                
                try {
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    
                    switch (opcion) {
                        case 1 -> {
                            System.out.println("\n Zonas disponibles:");
                            System.out.println("---------------ESCENARIO---------------");
                            System.out.println("  Zona A:  01-02-03-04-05-06 ");
                            System.out.println("           07-08-09-10-11-12 ");
                            System.out.println("           ================= ");
                            System.out.println("  Zona B:  01-02-03-04-05-06 ");
                            System.out.println("           07-08-09-10-11-12 ");
                            System.out.println("           ================= ");
                            System.out.println("  Zona C:  01-02-03-04-05-06 ");
                            System.out.println("           07-08-09-10-11-12 ");
                            for (int i = 0; i < zonas.length; i++) {
                                System.out.println(i + ". " + zonas[i] + " (Precio Neto: $" + preciosNetos[i] + ", asientos disponibles: " + asientosDisponibles[i] + ")");
                            }
                        }
                        case 2 -> {
                            if (totalClientes >= clientes.length) {
                                System.out.println("Lo sentimos, no hay mas entradas par este evento.");
                            } else {
                                System.out.print("\nIngrese su nombre: ");
                                String nombre = sc.nextLine();
                                while (nombre.isBlank()) {
                                    System.out.print("Nombre invalido. Ingrese su nombre: ");
                                    nombre = sc.nextLine();
                                }
                                
                                int edad = 0;
                                boolean edadValida = false;
                                while (!edadValida) {
                                    try {
                                        System.out.print("Ingrese su edad: ");
                                        edad = sc.nextInt();
                                        sc.nextLine();
                                        if (edad > 0 && edad < 150) {
                                            edadValida = true;
                                        } else {
                                            System.out.println("Edad invalida.");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Debe ingresar un numero valido.");
                                        sc.nextLine();
                                    }
                                }
                                
                                System.out.println("Seleccion de zona:");
                                for (int i = 0; i < zonas.length; i++) {
                                    System.out.println(i + ". " + zonas[i] + " (Precio Neto: $" + preciosNetos[i] + ", asientos disponibles: " + asientosDisponibles[i] + ")");
                                }
                                
                                int seleccion = -1;
                                boolean seleccionValida = false;
                                while (!seleccionValida) {
                                    try {
                                        System.out.print("Ingrese el numero de la zona: ");
                                        seleccion = sc.nextInt();
                                        sc.nextLine();
                                        if (seleccion >= 0 && seleccion < zonas.length) {
                                            seleccionValida = true;
                                        } else {
                                            System.out.println("Seleccion invalida. Intente nuevamente.");
                                        }
                                    } catch (InputMismatchException e) {
                                        System.out.println("Debe ingresar un numero valido.");
                                        sc.nextLine();
                                    }
                                }
                                
                                if (asientosDisponibles[seleccion] > 0) {
                                    clientes[totalClientes] = nombre;
                                    edadesClientes[totalClientes] = edad;
                                    asientosReservados[totalClientes] = zonas[seleccion];
                                    
                                    double descuento = 0;
                                    String tipo = "general";
                                    
                                    if (edad >= 60) {
                                        descuento = 15.0;
                                        tipo = "Adulto Mayor";
                                        adultosMayores++;
                                    } else {
                                        System.out.print("Es estudiante? (si/no): ");
                                        String respuesta = sc.nextLine().trim().toLowerCase();
                                        while (!(respuesta.equals("si") || respuesta.equals("no"))) {
                                            System.out.print("Respuesta invalida. Ingrese 'si' o 'no': ");
                                            respuesta = sc.nextLine().trim().toLowerCase();
                                        }
                                        if (respuesta.equals("si")) {
                                            descuento = 10.0;
                                            tipo = "Estudiante";
                                            estudiantes++;
                                        }
                                    }
                                    
                                    tipoCliente.add(tipo);
                                    descuentosAplicados.add(descuento);
                                    
                                    double precioBase = preciosNetos[seleccion];
                                    double montoConDescuento = precioBase * (1 - descuento / 100);
                                    
                                    montosFinales.add(montoConDescuento);
                                    
                                    asientosDisponibles[seleccion]--;
                                    totalClientes++;
                                    
                                    System.out.println("Registro exitoso.");
                                    System.out.println("Nombre: " + nombre);
                                    System.out.println("Zona: " + zonas[seleccion]);
                                    System.out.println("Precio Neto: $" + precioBase);
                                    System.out.println("Descuento aplicado: " + descuento + "%");
                                    System.out.println("Precio Final: $" + montoConDescuento);
                                    
                                } else {
                                    System.out.println("No hay asientos disponibles en esa zona.");
                                }
                            }
                        }
                        case 3 -> {
                            System.out.print("\nIngrese su nombre para cancelar su reserva: ");
                            String nombreCancelar = sc.nextLine();
                            boolean encontrado = false;
                            for (int i = 0; i < totalClientes; i++) {
                                if (clientes[i] != null && clientes[i].equalsIgnoreCase(nombreCancelar)) {
                                    switch (asientosReservados[i]) {
                                        case "Zona A" -> asientosDisponibles[0]++;
                                        case "Zona B" -> asientosDisponibles[1]++;
                                        case "Zona C" -> asientosDisponibles[2]++;
                                        default -> {
                                        }
                                    }
                                    
                                    clientes[i] = null;
                                    asientosReservados[i] = null;
                                    edadesClientes[i] = 0;
                                    descuentosAplicados.set(i, 0.0);
                                    montosFinales.set(i, 0.0);
                                    
                                    System.out.println("Reserva cancelada.");
                                    encontrado = true;
                                    break;
                                }
                            }       if (!encontrado) {
                                System.out.println("Cliente no encontrado.");
                            }
                        }
                        case 4 -> {
                            System.out.println("\n Detalle de reservas:");
                            int inscritosTotales = 0;
                            double netoTotal = 0;
                            for (int i = 0; i < totalClientes; i++) {
                                if (clientes[i] != null) {
                                    inscritosTotales++;
                                    double montoNeto = montosFinales.get(i);
                                    netoTotal += montoNeto;
                                }
                                System.out.println(clientes[i] + " - " + asientosReservados[i] +
                                        " - Edad: " + edadesClientes[i] +
                                        " - Anos - Tipo: " + tipoCliente.get(i) +
                                        " - Descuento aplicado:" + descuentosAplicados.get(i)+ "%"+
                                        " - Total pagado: $" + montosFinales.get(i));
                            }
                            System.out.println("\n===== BOLETA =====");
                            System.out.println("Total reservas: " + inscritosTotales);
                            System.out.println("Estudiantes: " + estudiantes);
                            System.out.println("Adultos mayores: " + adultosMayores);
                            System.out.println("Neto total: $" + netoTotal);
                        }
                        
                        case 5 -> {
                            salir = true;
                            System.out.println("Gracias por su compra!");
                            System.out.println("Hasta luego!");
                        }
                        default -> System.out.println("Opcion invalida. Ingrese un numero entre 1 y 5.");
                    }
                    
                } catch (InputMismatchException e) {
                    System.out.println("Entrada invalida. Debe ingresar un numero.");
                    sc.nextLine();
                }
            }
        }
                
        
    }

}
