import java.util.Scanner;

public class LeninCalificaciones {
    static Scanner scanner = new Scanner(System.in);

    // Metodo auxiliar para calcular edad
    static int calcularEdad(String fechaNacimiento) {
        try {
            String[] partes = fechaNacimiento.split("/");
            int anioNacimiento = Integer.parseInt(partes[partes.length - 1]);
            return 2026 - anioNacimiento; 
        } catch (Exception e) {
            return 0; 
        }
    }

    // CRUD de Calificaciones (Opcion 2)
    static void registroCalificaciones() {
        boolean continuarModulo = true;

        while (continuarModulo) {
            System.out.print("\nIngrese el numero de cedula del estudiante: ");
            String cedula = scanner.nextLine();
            
            // Llama al metodo buscar del programa principal
            int index = GestorPersonas.buscar(cedula); 

            if (index == -1) {
                System.out.println("Error: Estudiante no encontrado.");
                System.out.print("Desea ingresar otra cedula? (S/N): ");
                String resp = scanner.nextLine();
                if (resp.equalsIgnoreCase("N")) {
                    continuarModulo = false;
                }
                continue;
            }

            Estudiante est = GestorPersonas.estudiantes[index];
            int edad = calcularEdad(est.fechaNacimiento);
            
            System.out.println("\n--- Datos del Estudiante ---");
            System.out.println("Nombres y Apellidos: " + est.nombres + " " + est.apellidos);
            System.out.println("Edad: " + edad + " anios");

            boolean gestionandoNotas = true;
            while (gestionandoNotas) {
                System.out.println("\n--- Calificaciones Registradas ---");
                if (est.cantidadNotas == 0) {
                    System.out.println("No hay calificaciones registradas.");
                } else {
                    for (int i = 0; i < est.cantidadNotas; i++) {
                        System.out.println("[" + (i + 1) + "] Nota: " + est.notas[i]);
                    }
                }

                if (est.cantidadNotas == 7) {
                    System.out.println("\n¡Aviso!: Se han ingresado todas las calificaciones posibles (7/7).");
                }

                System.out.println("\nOpciones:");
                System.out.println("1. Insertar nota");
                System.out.println("2. Modificar nota");
                System.out.println("3. Eliminar nota");
                System.out.println("4. Buscar otro estudiante");
                System.out.println("5. Volver al menu principal");
                System.out.print("Opcion: ");
                
                int opcionNotas = scanner.nextInt();
                scanner.nextLine();

                switch (opcionNotas) {
                    case 1:
                        if (est.cantidadNotas < 7) {
                            System.out.print("Ingrese la nueva nota: ");
                            est.notas[est.cantidadNotas] = scanner.nextDouble();
                            scanner.nextLine();
                            est.cantidadNotas++;
                            System.out.println("Nota registrada con exito.");
                        } else {
                            System.out.println("Error: Limite maximo de 7 notas alcanzado.");
                        }
                        break;
                    case 2:
                        if (est.cantidadNotas > 0) {
                            System.out.print("Numero de la nota a modificar (1-" + est.cantidadNotas + "): ");
                            int posMod = scanner.nextInt(); scanner.nextLine();
                            if (posMod >= 1 && posMod <= est.cantidadNotas) {
                                System.out.print("Nuevo valor: ");
                                est.notas[posMod - 1] = scanner.nextDouble(); scanner.nextLine();
                                System.out.println("Nota modificada.");
                            } else System.out.println("Posicion invalida.");
                        } else System.out.println("No hay notas para modificar.");
                        break;
                    case 3:
                        if (est.cantidadNotas > 0) {
                            System.out.print("Numero de la nota a eliminar (1-" + est.cantidadNotas + "): ");
                            int posElim = scanner.nextInt(); scanner.nextLine();
                            if (posElim >= 1 && posElim <= est.cantidadNotas) {
                                for (int i = posElim - 1; i < est.cantidadNotas - 1; i++) {
                                    est.notas[i] = est.notas[i + 1];
                                }
                                est.notas[est.cantidadNotas - 1] = 0; 
                                est.cantidadNotas--;
                                System.out.println("Nota eliminada.");
                            } else System.out.println("Posicion invalida.");
                        } else System.out.println("No hay notas para eliminar.");
                        break;
                    case 4: gestionandoNotas = false; break;
                    case 5: gestionandoNotas = false; continuarModulo = false; break;
                    default: System.out.println("Opcion no valida.");
                }
            }
        }
    }
}
