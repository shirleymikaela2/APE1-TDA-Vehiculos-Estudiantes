

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Instancia del gestor que integra el arreglo de máximo 20 estudiantes de Josué
        GestorEstudiantes gestor = new GestorEstudiantes(); 
        int opcion = 0;

        while (opcion != 5) {
            System.out.println("\n=== GESTOR DE PERSONAS ===");
            System.out.println("1.- Estudiantes.");
            System.out.println("2.- Registro de calificaciones.");
            System.out.println("3.- Determinar el promedio de notas de un estudiante.");
            System.out.println("4.- Determinar el promedio de notas del curso.");
            System.out.println("5.- Salir");
            System.out.print("Teclee su opción (1-4): ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    // Aquí llamas al menú CRUD de estudiantes que programará Cris
                    // gestor.menuEstudiantes(scanner);
                    System.out.println("Módulo de Cris en construcción...");
                    break;
                case 2:
                    // Aquí llamas al menú CRUD de calificaciones que programará Lenin
                    // gestor.menuCalificaciones(scanner);
                    System.out.println("Módulo de Lenin en construcción...");
                    break;
                case 3:
                    // Opción 3 de Héctor: Promedio individual
                    System.out.print("Ingrese el número de cédula: ");
                    String cedulaBusqueda = scanner.nextLine();
                    Estudiante est = gestor.buscar(cedulaBusqueda); // Método buscar de Josué
                    
                    if (est != null) {
                        double promedio = calcularPromedioIndividual(est);
                        System.out.println("Nombres: " + est.getNombres());
                        System.out.println("Apellidos: " + est.getApellidos());
                        System.out.println("Edad: " + est.calcularEdad() + " años");
                        System.out.printf("Promedio de calificaciones: %.2f\n", promedio);
                    } else {
                        System.out.println("Error: no se encontró un estudiante con el número de cédula indicado.");
                    }
                    break;
                case 4:
                    // Opción 4 de Héctor: Promedio general del curso
                    calcularPromedioGeneral(gestor);
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    // Lógica de Héctor para Promedio Individual
    private static double calcularPromedioIndividual(Estudiante est) {
        if (est.getContadorNotas() == 0) return 0.0;
        double suma = 0;
        double[] notas = est.getNotas();
        for (int i = 0; i < est.getContadorNotas(); i++) {
            suma += notas[i];
        }
        return suma / est.getContadorNotas();
    }

    // Lógica de Héctor para Promedio General
    private static void calcularPromedioGeneral(GestorEstudiantes gestor) {
        Estudiante[] lista = gestor.getListaEstudiantes();
        int totalEstudiantes = gestor.getTotalRegistrados();
        
        if (totalEstudiantes == 0) {
            System.out.println("No se han registrado calificaciones de estudiantes.");
            return;
        }

        double sumaGeneral = 0;
        int totalNotasGlobales = 0;

        for (int i = 0; i < totalEstudiantes; i++) {
            Estudiante est = lista[i];
            for (int j = 0; j < est.getContadorNotas(); j++) {
                sumaGeneral += est.getNotas()[j];
                totalNotasGlobales++;
            }
        }

        if (totalNotasGlobales == 0) {
            System.out.println("No se han registrado calificaciones de estudiantes.");
        } else {
            double promedioGeneral = sumaGeneral / totalNotasGlobales;
            System.out.printf("El promedio general de calificaciones del curso es: %.2f\n", promedioGeneral);
        }
    }
}