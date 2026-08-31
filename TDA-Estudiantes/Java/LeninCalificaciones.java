import java.util.Scanner;

public class LeninCalificaciones {

    public static void gestionarCalificaciones(
            Scanner teclado,
            GestorEstudiantes gestor) {

        System.out.println(
                "\n=== REGISTRO DE CALIFICACIONES ==="
        );

        System.out.print("Cédula del estudiante: ");
        String cedula = teclado.nextLine().trim();

        Estudiante estudiante = gestor.buscar(cedula);

        if (estudiante == null) {
            System.out.println(
                    "No se encontró un estudiante con esa cédula."
            );
            return;
        }

        System.out.println(
                "Estudiante: "
                + estudiante.getNombres() + " "
                + estudiante.getApellidos()
        );

        System.out.println(
                "Edad: " + estudiante.calcularEdad()
        );

        int opcion;

        do {
            System.out.println(
                    "\n=== MENÚ DE CALIFICACIONES ==="
            );

            estudiante.mostrarNotas();

            System.out.println("1. Ingresar calificación");
            System.out.println("2. Modificar calificación");
            System.out.println("3. Eliminar calificación");
            System.out.println("4. Regresar");
            System.out.print("Opción: ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    agregar(teclado, estudiante);
                    break;

                case 2:
                    modificar(teclado, estudiante);
                    break;

                case 3:
                    eliminar(teclado, estudiante);
                    break;

                case 4:
                    System.out.println("Regresando.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private static void agregar(
            Scanner teclado,
            Estudiante estudiante) {

        if (estudiante.getCantidadNotas()
                == Estudiante.MAX_NOTAS) {

            System.out.println(
                    "Ya se registraron las siete calificaciones."
            );
            return;
        }

        System.out.print("Nueva calificación (0-10): ");
        double nota = leerDouble(teclado);

        if (estudiante.agregarNota(nota)) {
            System.out.println("Calificación registrada.");
        } else {
            System.out.println("Calificación inválida.");
        }
    }

    private static void modificar(
            Scanner teclado,
            Estudiante estudiante) {

        if (estudiante.getCantidadNotas() == 0) {
            System.out.println(
                    "No existen calificaciones para modificar."
            );
            return;
        }

        estudiante.mostrarNotas();

        System.out.print("Número de calificación: ");
        int posicion = leerEntero(teclado);

        System.out.print("Nueva calificación (0-10): ");
        double nota = leerDouble(teclado);

        if (estudiante.modificarNota(posicion - 1, nota)) {
            System.out.println("Calificación modificada.");
        } else {
            System.out.println("Datos inválidos.");
        }
    }

    private static void eliminar(
            Scanner teclado,
            Estudiante estudiante) {

        if (estudiante.getCantidadNotas() == 0) {
            System.out.println(
                    "No existen calificaciones para eliminar."
            );
            return;
        }

        estudiante.mostrarNotas();

        System.out.print("Número de calificación: ");
        int posicion = leerEntero(teclado);

        if (estudiante.eliminarNota(posicion - 1)) {
            System.out.println("Calificación eliminada.");
        } else {
            System.out.println("Posición inválida.");
        }
    }

    private static int leerEntero(Scanner teclado) {
        while (true) {
            try {
                return Integer.parseInt(
                        teclado.nextLine().trim()
                );
            } catch (NumberFormatException error) {
                System.out.print("Ingrese un entero válido: ");
            }
        }
    }

    private static double leerDouble(Scanner teclado) {
        while (true) {
            try {
                String entrada = teclado.nextLine()
                        .trim()
                        .replace(',', '.');

                return Double.parseDouble(entrada);
            } catch (NumberFormatException error) {
                System.out.print("Ingrese una nota válida: ");
            }
        }
    }

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GestorEstudiantes gestor = new GestorEstudiantes();

        Estudiante prueba = new Estudiante(
                "1800000001",
                "Ana María",
                "Pérez López",
                15,
                5,
                2005
        );

        gestor.registrar(prueba);

        System.out.println(
                "Para probar use la cédula: 1800000001"
        );

        gestionarCalificaciones(teclado, gestor);
        teclado.close();
    }
}