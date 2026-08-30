import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        // CONCEPTO: INSTANCIACIÓN DE OBJETOS
        RegistroVehiculos registro = new RegistroVehiculos();

        int opcion;

        do {
            System.out.println("\n=== REGISTRO DE VEHÍCULOS ===");
            System.out.println("1. Registrar automóvil");
            System.out.println("2. Registrar motocicleta");
            System.out.println("3. Mostrar vehículos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(teclado);

            switch (opcion) {
                case 1:
                    registrarAutomovil(teclado, registro);
                    break;

                case 2:
                    registrarMotocicleta(teclado, registro);
                    break;

                case 3:
                    registro.mostrarTodos();
                    break;

                case 4:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción incorrecta.");
            }

        } while (opcion != 4);

        teclado.close();
    }

    private static void registrarAutomovil(
            Scanner teclado, RegistroVehiculos registro) {

        if (registro.estaLleno()) {
            System.out.println(
                    "Ya se registraron los 10 vehículos permitidos.");
            return;
        }

        try {
            System.out.println("\n=== NUEVO AUTOMÓVIL ===");

            String placa = leerTexto(teclado, "Placa: ");

            if (registro.existePlaca(placa)) {
                System.out.println(
                        "Ya existe un vehículo con esa placa.");
                return;
            }

            String marca = leerTexto(teclado, "Marca: ");
            String modelo = leerTexto(teclado, "Modelo: ");

            System.out.print("Año: ");
            int anio = leerEntero(teclado);

            System.out.print("Precio: ");
            double precio = leerDouble(teclado);

            boolean disponible =
                    leerBooleano(teclado, "¿Está disponible?");

            System.out.print("Número de puertas: ");
            int numeroPuertas = leerEntero(teclado);

            boolean electrico =
                    leerBooleano(teclado, "¿Es eléctrico?");

            // CONCEPTO: INSTANCIACIÓN Y POLIMORFISMO
            Vehiculo nuevoVehiculo = new Automovil(
                    placa, marca, modelo, anio, precio,
                    disponible, numeroPuertas, electrico);

            if (registro.registrar(nuevoVehiculo)) {
                System.out.println(
                        "Automóvil registrado correctamente.");
            } else {
                System.out.println(
                        "No fue posible registrar el automóvil.");
            }

        } catch (IllegalArgumentException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private static void registrarMotocicleta(
            Scanner teclado, RegistroVehiculos registro) {

        if (registro.estaLleno()) {
            System.out.println(
                    "Ya se registraron los 10 vehículos permitidos.");
            return;
        }

        try {
            System.out.println("\n=== NUEVA MOTOCICLETA ===");

            String placa = leerTexto(teclado, "Placa: ");

            if (registro.existePlaca(placa)) {
                System.out.println(
                        "Ya existe un vehículo con esa placa.");
                return;
            }

            String marca = leerTexto(teclado, "Marca: ");
            String modelo = leerTexto(teclado, "Modelo: ");

            System.out.print("Año: ");
            int anio = leerEntero(teclado);

            System.out.print("Precio: ");
            double precio = leerDouble(teclado);

            boolean disponible =
                    leerBooleano(teclado, "¿Está disponible?");

            System.out.print("Cilindrada: ");
            int cilindrada = leerEntero(teclado);

            boolean tieneMaletero =
                    leerBooleano(teclado, "¿Tiene maletero?");

            // CONCEPTO: INSTANCIACIÓN Y POLIMORFISMO
            Vehiculo nuevoVehiculo = new Motocicleta(
                    placa, marca, modelo, anio, precio,
                    disponible, cilindrada, tieneMaletero);

            if (registro.registrar(nuevoVehiculo)) {
                System.out.println(
                        "Motocicleta registrada correctamente.");
            } else {
                System.out.println(
                        "No fue posible registrar la motocicleta.");
            }

        } catch (IllegalArgumentException error) {
            System.out.println("Error: " + error.getMessage());
        }
    }

    private static String leerTexto(
            Scanner teclado, String mensaje) {

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

    private static double leerDouble(Scanner teclado) {
        while (true) {
            try {
                String entrada = teclado.nextLine()
                        .trim()
                        .replace(',', '.');

                return Double.parseDouble(entrada);

            } catch (NumberFormatException error) {
                System.out.print(
                        "Ingrese un número decimal válido: ");
            }
        }
    }

    private static boolean leerBooleano(
            Scanner teclado, String pregunta) {

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
}