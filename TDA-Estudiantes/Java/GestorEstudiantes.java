public class GestorEstudiantes {

    public static final int MAX_ESTUDIANTES = 20;

    // Vector estático de veinte estudiantes.
    private final Estudiante[] estudiantes;

    // Tamaño lógico del vector.
    private int cantidadEstudiantes;

    public GestorEstudiantes() {
        estudiantes = new Estudiante[MAX_ESTUDIANTES];
        cantidadEstudiantes = 0;
    }

    public boolean registrar(Estudiante estudiante) {

        if (estudiante == null || estaLleno()) {
            return false;
        }

        if (buscar(estudiante.getCedula()) != null) {
        return false;
        }

        estudiantes[cantidadEstudiantes] = estudiante;
        cantidadEstudiantes++;

        return true;
    }

    // Método solicitado para buscar por cédula.
    public Estudiante buscar(String cedula) {

        if (cedula == null) {
            return null;
        }

        for (int i = 0; i < cantidadEstudiantes; i++) {
            if (estudiantes[i].getCedula()
                    .equals(cedula.trim())) {

                return estudiantes[i];
            }
        }

        return null;
    }

    public Estudiante obtenerPorAutonumerico(
            int autonumerico) {

        int posicion = autonumerico - 1;

        if (posicion < 0
                || posicion >= cantidadEstudiantes) {

            return null;
        }

        return estudiantes[posicion];
    }

    public boolean modificar(
            int autonumerico,
            String cedula,
            String nombres,
            String apellidos,
            int dia,
            int mes,
            int anio) {

        Estudiante estudiante =
                obtenerPorAutonumerico(autonumerico);

        if (estudiante == null) {
            return false;
        }

        Estudiante cedulaEncontrada = buscar(cedula);

        if (cedulaEncontrada != null
                && cedulaEncontrada != estudiante) {

            return false;
        }

        estudiante.actualizarDatos(
                cedula,
                nombres,
                apellidos,
                dia,
                mes,
                anio
        );

        return true;
    }

    public boolean eliminar(int autonumerico) {

        int posicion = autonumerico - 1;

        if (posicion < 0
                || posicion >= cantidadEstudiantes) {

            return false;
        }

        /*
         * Se desplazan los elementos para evitar
         * espacios vacíos dentro del vector.
         */
        for (int i = posicion;
             i < cantidadEstudiantes - 1;
             i++) {

            estudiantes[i] = estudiantes[i + 1];
        }

        estudiantes[cantidadEstudiantes - 1] = null;
        cantidadEstudiantes--;

        return true;
    }

    public void mostrarTodos() {

        System.out.println("\n=== LISTADO DE ESTUDIANTES ===");

        if (cantidadEstudiantes == 0) {
            System.out.println(
                    "No existen estudiantes registrados.");
            return;
        }

        for (int i = 0; i < cantidadEstudiantes; i++) {
            System.out.println(
                    "\nAutonumérico: " + (i + 1));

            estudiantes[i].mostrarDatos();

            System.out.println("----------------------------");
        }

        System.out.println(
                "Total registrado: "
                        + cantidadEstudiantes
                        + "/"
                        + MAX_ESTUDIANTES);
    }

    public boolean estaLleno() {
        return cantidadEstudiantes == MAX_ESTUDIANTES;
    }

    public boolean estaVacio() {
        return cantidadEstudiantes == 0;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }
}