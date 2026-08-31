public class PruebaJosue {

    public static void main(String[] args) {

        GestorEstudiantes gestor = new GestorEstudiantes();

        Estudiante estudiante1 = new Estudiante(
                "1800000001",
                "Ana Maria",
                "Perez Lopez",
                15,
                5,
                2005
        );

        Estudiante estudiante2 = new Estudiante(
                "1800000002",
                "Carlos Andres",
                "Gomez Sanchez",
                20,
                10,
                2004
        );

        System.out.println(
                "Registro del primer estudiante: "
                + gestor.registrar(estudiante1));

        System.out.println(
                "Registro del segundo estudiante: "
                + gestor.registrar(estudiante2));

        System.out.println(
                "Cantidad registrada: "
                + gestor.getCantidadEstudiantes()
                + "/"
                + gestor.getCapacidad());

        gestor.mostrarTodos();

        System.out.println(
                "\n=== BUSQUEDA POR CEDULA ===");

        Estudiante encontrado =
                gestor.buscar("1800000002");

        if (encontrado != null) {
            System.out.println(
                    "Estudiante encontrado correctamente:");

            encontrado.mostrarDatos();
        } else {
            System.out.println(
                    "No se encontro al estudiante.");
        }

        System.out.println(
                "\n=== BUSQUEDA INCORRECTA ===");

        Estudiante inexistente =
                gestor.buscar("9999999999");

        if (inexistente == null) {
            System.out.println(
                    "No existe un estudiante con esa cedula.");
        }
    }
}
