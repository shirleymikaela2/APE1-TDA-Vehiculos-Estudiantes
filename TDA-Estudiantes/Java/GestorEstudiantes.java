public class GestorEstudiantes {

    public static final int MAX_ESTUDIANTES = 20;

    private final Estudiante[] estudiantes;
    private int cantidadEstudiantes;

    public GestorEstudiantes() {
        estudiantes = new Estudiante[MAX_ESTUDIANTES];
        cantidadEstudiantes = 0;
    }

    public boolean registrar(Estudiante nuevoEstudiante) {

        if (nuevoEstudiante == null || estaLleno()) {
            return false;
        }

        if (buscar(nuevoEstudiante.getCedula()) != null) {
            return false;
        }

        estudiantes[cantidadEstudiantes] = nuevoEstudiante;
        cantidadEstudiantes++;

        return true;
    }

    public Estudiante buscar(String cedula) {

        if (cedula == null || cedula.trim().isEmpty()) {
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

    public void mostrarTodos() {

        if (cantidadEstudiantes == 0) {
            System.out.println(
                    "No existen estudiantes registrados.");
            return;
        }

        System.out.println(
                "\n=== ESTUDIANTES REGISTRADOS ===");

        for (int i = 0; i < cantidadEstudiantes; i++) {

            System.out.println(
                    "\nAutonumerico: " + (i + 1));

            estudiantes[i].mostrarDatos();

            System.out.println(
                    "----------------------------");
        }
    }

    public boolean estaLleno() {
        return cantidadEstudiantes == MAX_ESTUDIANTES;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }

    public int getCapacidad() {
        return MAX_ESTUDIANTES;
    }
}
