import java.util.Scanner;

public class MainCris {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        GestorEstudiantes gestor = new GestorEstudiantes();

        int opcion;

        do {
            gestor.mostrarTodos();

            System.out.println("\n=== SUBMENÚ ESTUDIANTES ===");
            System.out.println("1. Ingresar estudiante");
            System.out.println("2. Modificar estudiante");
            System.out.println("3. Eliminar estudiante");
            System.out.println("4. Regresar");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    repetirRegistro(teclado, gestor);
                    break;

                case 2:
                    repetirModificacion(teclado, gestor);
                    break;

                case 3:
                    repetirEliminacion(teclado, gestor);
                    break;

                case 4:
                    System.out.println(
                            "Regresando al menú principal.");
                    break;

                default:
                    System.out.println("Opción incorrecta.");
            }

        } while (opcion != 4);

        teclado.close();
    }

    private static void repetirRegistro(
            Scanner teclado,
            GestorEstudiantes gestor) {

        boolean continuar;

        do {
            registrarEstudiante(teclado, gestor);

            if (gestor.estaLleno()) {
                System.out.println(
                        "Se registraron los 20 estudiantes permitidos.");

                continuar = false;
            } else {
                continuar = preguntarRepetir(
                        teclado,
                        "¿Desea registrar otro estudiante?");
            }

        } while (continuar);
    }

    private static void registrarEstudiante(
            Scanner teclado,
            GestorEstudiantes gestor) {

        if (gestor.estaLleno()) {
            System.out.println(
                    "No se pueden registrar más estudiantes.");
            return;
        }

        try {
            System.out.println("\n=== NUEVO ESTUDIANTE ===");

            String cedula =
                    leerTexto(teclado, "Cédula: ");

            if (gestor.buscar(cedula) != null) {
                System.out.println(
                        "Ya existe un estudiante con esa cédula.");
                return;
            }

            String nombres =
                    leerTexto(teclado, "Nombres: ");

            String apellidos =
                    leerTexto(teclado, "Apellidos: ");

            System.out.print("Día de nacimiento: ");
            int dia = leerEntero(teclado);

            System.out.print("Mes de nacimiento: ");
            int mes = leerEntero(teclado);

            System.out.print("Año de nacimiento: ");
            int anio = leerEntero(teclado);

            Estudiante nuevo = new Estudiante(
                    cedula,
                    nombres,
                    apellidos,
                    dia,
                    mes,
                    anio
            );

            if (gestor.registrar(nuevo)) {
                System.out.println(
                        "Estudiante registrado correctamente.");
            } else {
                System.out.println(
                        "No fue posible registrar al estudiante.");
            }

        } catch (IllegalArgumentException error) {
            System.out.println(
                    "Error: " + error.getMessage());
        }
    }

    private static void repetirModificacion(
            Scanner teclado,
            GestorEstudiantes gestor) {

        if (gestor.estaVacio()) {
            System.out.println(
                    "No existen estudiantes para modificar.");
            return;
        }

        boolean continuar;

        do {
            modificarEstudiante(teclado, gestor);

            continuar = preguntarRepetir(
                    teclado,
                    "¿Desea modificar otro estudiante?");

        } while (continuar && !gestor.estaVacio());
    }

    private static void modificarEstudiante(
            Scanner teclado,
            GestorEstudiantes gestor) {

        gestor.mostrarTodos();

        System.out.print(
                "Ingrese el autonumérico del estudiante: ");

        int autonumerico = leerEntero(teclado);

        Estudiante estudiante =
                gestor.obtenerPorAutonumerico(autonumerico);

        if (estudiante == null) {
            System.out.println(
                    "El autonumérico no existe.");
            return;
        }

        try {
            System.out.println("\nIngrese los nuevos datos.");

            String cedula =
                    leerTexto(teclado, "Cédula: ");

            String nombres =
                    leerTexto(teclado, "Nombres: ");

            String apellidos =
                    leerTexto(teclado, "Apellidos: ");

            System.out.print("Día de nacimiento: ");
            int dia = leerEntero(teclado);

            System.out.print("Mes de nacimiento: ");
            int mes = leerEntero(teclado);

            System.out.print("Año de nacimiento: ");
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
                        "Estudiante modificado correctamente.");
            } else {
                System.out.println(
                        "No fue posible modificar. "
                        + "La cédula puede estar repetida.");
            }

        } catch (IllegalArgumentException error) {
            System.out.println(
                    "Error: " + error.getMessage());
        }
    }

    private static void repetirEliminacion(
            Scanner teclado,
            GestorEstudiantes gestor) {

        if (gestor.estaVacio()) {
            System.out.println(
                    "No existen estudiantes para eliminar.");
            return;
        }

        boolean continuar;

        do {
            eliminarEstudiante(teclado, gestor);

            if (gestor.estaVacio()) {
                System.out.println(
                        "Ya no existen estudiantes registrados.");

                continuar = false;
            } else {
                continuar = preguntarRepetir(
                        teclado,
                        "¿Desea eliminar otro estudiante?");
            }

        } while (continuar);
    }

    private static void eliminarEstudiante(
            Scanner teclado,
            GestorEstudiantes gestor) {

        gestor.mostrarTodos();

        System.out.print(
                "Ingrese el autonumérico del estudiante: ");

        int autonumerico = leerEntero(teclado);

        if (gestor.eliminar(autonumerico)) {
            System.out.println(
                    "Estudiante eliminado correctamente.");
        } else {
            System.out.println(
                    "El autonumérico indicado no existe.");
        }
    }

    private static boolean preguntarRepetir(
            Scanner teclado,
            String pregunta) {

        int respuesta;

        do {
            System.out.println(pregunta);
            System.out.println("1. Sí");
            System.out.println("2. No");
            System.out.print("Respuesta: ");

            respuesta = leerEntero(teclado);

            if (respuesta != 1 && respuesta != 2) {
                System.out.println(
                        "Seleccione únicamente 1 o 2.");
            }

        } while (respuesta != 1 && respuesta != 2);

        return respuesta == 1;
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
                        "El campo no puede estar vacío.");
            }

        } while (texto.isEmpty());

        return texto;
    }

    private static int leerEntero(Scanner teclado) {

        while (true) {
            try {
                return Integer.parseInt(
                        teclado.nextLine().trim());

            } catch (NumberFormatException error) {
                System.out.print(
                        "Ingrese un número entero válido: ");
            }
        }
    }
}