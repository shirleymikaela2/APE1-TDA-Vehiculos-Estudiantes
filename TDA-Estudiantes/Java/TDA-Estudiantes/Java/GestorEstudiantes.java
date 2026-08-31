public class GestorEstudiantes {

    private Estudiante[] estudiantes;
    private int cantidadEstudiantes;

    public GestorEstudiantes() {

        estudiantes = new Estudiante[20];
        cantidadEstudiantes = 0;
    }

    public boolean agregarEstudiante(Estudiante estudiante) {

        if (cantidadEstudiantes < 20) {

            estudiantes[cantidadEstudiantes] = estudiante;
            cantidadEstudiantes++;

            return true;
        }

        return false;
    }

    public Estudiante buscar(String cedula) {

        for (int i = 0; i < cantidadEstudiantes; i++) {

            if (estudiantes[i].getCedula().equals(cedula)) {

                return estudiantes[i];
            }
        }

        return null;
    }

    public Estudiante[] getEstudiantes() {
        return estudiantes;
    }

    public int getCantidadEstudiantes() {
        return cantidadEstudiantes;
    }
}
