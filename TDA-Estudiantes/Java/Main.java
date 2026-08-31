import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        GestorEstudiantes gestor = new GestorEstudiantes();

        int opcion;

        do {
            System.out.println("\n=== GESTOR DE PERSONAS ===");
            System.out.println("1.- Estudiantes.");
            System.out.println("2.- Registro de calificaciones.");
            System.out.println(
                    "3.- Determinar el promedio de notas "
                    + "de un estudiante."
            );
            System.out.println(
                    "4.- Determinar el promedio de notas del curso."
            );
            System.out.println("5.- Salir.");
            System.out.print("Teclee su opción (1-5): ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    menuEstudiantes(teclado, gestor);
                    break;

                case 2:
                    LeninCalificaciones.gestionarCalificaciones(
                            teclado,
                            gestor
                    );
                    break;

                case 3:
                    mostrarPromedioEstudiante(teclado, gestor);
                    break;

                case 4:
                    mostrarPromedioCurso(gestor);
                    break;

                case 5:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 5);

        teclado.close();
    }

    private static void menuEstudiantes(
            Scanner teclado,
            GestorEstudiantes gestor) {

        int opcion;

        do {
            gestor.mostrarTodos();

            System.out.println("\n=== SUBMENÚ ESTUDIANTES ===");
            System.out.println("1.- Ingresar estudiante.");
            System.out.println("2.- Modificar estudiante.");
            System.out.println("3.- Eliminar estudiante.");
            System.out.println("4.- Regresar.");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    registrarEstudiante(teclado, gestor);
                    break;

                case 2:
                    modificarEstudiante(teclado, gestor);
                    break;

                case 3:
                    eliminarEstudiante(teclado, gestor);
                    break;

                case 4:
                    System.out.println(
                            "Regresando al menú principal."
                    );
                    break;

                default:
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private static void registrarEstudiante(
            Scanner teclado,
            GestorEstudiantes gestor) {

        if (gestor.estaLleno()) {
            System.out.println(
                    "Ya se registraron los veinte estudiantes."
            );
            return;
        }

        boolean repetir;

        do {
            try {
                System.out.println("\n=== NUEVO ESTUDIANTE ===");

                String cedula = leerTexto(
                        teclado,
                        "Cédula: "
                );

                if (gestor.buscar(cedula) != null) {
                    System.out.println(
                            "Ya existe un estudiante con esa cédula."
                    );
                } else {
                    String nombres = leerTexto(
                            teclado,
                            "Nombres: "
                    );

                    String apellidos = leerTexto(
                            teclado,
                            "Apellidos: "
                    );

                    System.out.print(
                            "Día de nacimiento: "
                    );
                    int dia = leerEntero(teclado);

                    System.out.print(
                            "Mes de nacimiento: "
                    );
                    int mes = leerEntero(teclado);

                    System.out.print(
                            "Año de nacimiento: "
                    );
                    int anio = leerEntero(teclado);

                    Estudiante estudiante = new Estudiante(
                            cedula,
                            nombres,
                            apellidos,
                            dia,
                            mes,
                            anio
                    );

                    if (gestor.registrar(estudiante)) {
                        System.out.println(
                                "Estudiante registrado correctamente."
                        );
                    } else {
                        System.out.println(
                                "No se pudo registrar al estudiante."
                        );
                    }
                }
            } catch (IllegalArgumentException error) {
                System.out.println(
                        "Error: " + error.getMessage()
                );
            }

            if (gestor.estaLleno()) {
                System.out.println(
                        "Se alcanzó la capacidad máxima."
                );
                return;
            }

            repetir = preguntarSiNo(
                    teclado,
                    "¿Desea ingresar otro estudiante? (S/N): "
            );
        } while (repetir);
    }

    private static void modificarEstudiante(
            Scanner teclado,
            GestorEstudiantes gestor) {

        if (gestor.estaVacio()) {
            System.out.println(
                    "No existen estudiantes para modificar."
            );
            return;
        }

        boolean repetir;

        do {
            gestor.mostrarTodos();

            System.out.print(
                    "Autonumérico del estudiante: "
            );
            int autonumerico = leerEntero(teclado);

            Estudiante estudiante =
                    gestor.obtenerPorAutonumerico(
                            autonumerico
                    );

            if (estudiante == null) {
                System.out.println(
                        "El autonumérico no es válido."
                );
            } else {
                try {
                    String cedula = leerTexto(
                            teclado,
                            "Nueva cédula: "
                    );

                    String nombres = leerTexto(
                            teclado,
                            "Nuevos nombres: "
                    );

                    String apellidos = leerTexto(
                            teclado,
                            "Nuevos apellidos: "
                    );

                    System.out.print(
                            "Nuevo día de nacimiento: "
                    );
                    int dia = leerEntero(teclado);

                    System.out.print(
                            "Nuevo mes de nacimiento: "
                    );
                    int mes = leerEntero(teclado);

                    System.out.print(
                            "Nuevo año de nacimiento: "
                    );
                    int anio = leerEntero(teclado);

                    if (gestor.modificar(
                            autonumerico,
                            cedula,
                            nombres,
                            apellidos,
                            dia,
                            mes,
                            anio)) {

                        System.out.println(
                                "Estudiante modificado correctamente."
                        );
                    } else {
                        System.out.println(
                                "No fue posible modificar el registro."
                        );
                    }
                } catch (IllegalArgumentException error) {
                    System.out.println(
                            "Error: " + error.getMessage()
                    );
                }
            }

            repetir = preguntarSiNo(
                    teclado,
                    "¿Desea modificar otro estudiante? (S/N): "
            );
        } while (repetir);
    }

    private static void eliminarEstudiante(
            Scanner teclado,
            GestorEstudiantes gestor) {

        if (gestor.estaVacio()) {
            System.out.println(
                    "No existen estudiantes para eliminar."
            );
            return;
        }

        boolean repetir;

        do {
            gestor.mostrarTodos();

            System.out.print(
                    "Autonumérico del estudiante: "
            );
            int autonumerico = leerEntero(teclado);

            if (gestor.eliminar(autonumerico)) {
                System.out.println(
                        "Estudiante eliminado correctamente."
                );
            } else {
                System.out.println(
                        "El autonumérico indicado no es válido."
                );
            }

            if (gestor.estaVacio()) {
                System.out.println(
                        "No quedan estudiantes registrados."
                );
                return;
            }

            repetir = preguntarSiNo(
                    teclado,
                    "¿Desea eliminar otro estudiante? (S/N): "
            );
        } while (repetir);
    }

    private static void mostrarPromedioEstudiante(
            Scanner teclado,
            GestorEstudiantes gestor) {

        String cedula = leerTexto(
                teclado,
                "Ingrese el número de cédula: "
        );

        Estudiante estudiante = gestor.buscar(cedula);

        if (estudiante == null) {
            System.out.println(
                    "Error: no se encontró un estudiante "
                    + "con la cédula indicada."
            );
            return;
        }

        System.out.println("\n=== DATOS DEL ESTUDIANTE ===");
        System.out.println(
                "Nombres y apellidos: "
                + estudiante.getNombres() + " "
                + estudiante.getApellidos()
        );
        System.out.println(
                "Edad: " + estudiante.calcularEdad()
        );

        if (estudiante.getCantidadNotas() == 0) {
            System.out.println(
                    "El estudiante no tiene calificaciones."
            );
            return;
        }

        System.out.printf(
                "Promedio de calificaciones: %.2f%n",
                estudiante.calcularPromedio()
        );
    }

    private static void mostrarPromedioCurso(
            GestorEstudiantes gestor) {

        double sumaPromedios = 0;
        int estudiantesConNotas = 0;

        for (int i = 1;
             i <= gestor.getCantidadEstudiantes();
             i++) {

            Estudiante estudiante =
                    gestor.obtenerPorAutonumerico(i);

            if (estudiante != null
                    && estudiante.getCantidadNotas() > 0) {

                sumaPromedios +=
                        estudiante.calcularPromedio();

                estudiantesConNotas++;
            }
        }

        if (estudiantesConNotas == 0) {
            System.out.println(
                    "No se han registrado calificaciones "
                    + "de estudiantes."
            );
            return;
        }

        double promedioCurso =
                sumaPromedios / estudiantesConNotas;

        System.out.printf(
                "Promedio general de calificaciones: %.2f%n",
                promedioCurso
        );
    }

    private static int leerEntero(Scanner teclado) {
        while (true) {
            String entrada = teclado.nextLine().trim();

            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException error) {
                System.out.print(
                        "Ingrese un número entero válido: "
                );
            }
        }
    }

    private static String leerTexto(
            Scanner teclado,
            String mensaje) {

        String texto;

        do {
            System.out.print(mensaje);
            texto = teclado.nextLine().trim();

            if (texto.isEmpty()) {
                System.out.println(
                        "El valor no puede estar vacío."
                );
            }
        } while (texto.isEmpty());

        return texto;
    }

    private static boolean preguntarSiNo(
            Scanner teclado,
            String mensaje) {

        while (true) {
            System.out.print(mensaje);

            String respuesta = teclado.nextLine()
                    .trim()
                    .toUpperCase();

            if (respuesta.equals("S")) {
                return true;
            }

            if (respuesta.equals("N")) {
                return false;
            }

            System.out.println(
                    "Responda únicamente S o N."
            );
        }
    }
}
