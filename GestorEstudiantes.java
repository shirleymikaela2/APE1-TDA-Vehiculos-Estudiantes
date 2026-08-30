public class GestorEstudiantes {
    private Estudiante[] lista = new Estudiante[20];
    private int total = 0;

    public GestorEstudiantes() {
        // Inyectamos dos estudiantes con notas para que pruebes tus cálculos
        lista[0] = new Estudiante("111", "Juan", "Perez", new double[]{14.5, 18.0}, 2);
        lista[1] = new Estudiante("222", "Maria", "Gomez", new double[]{20.0, 19.5, 18.0}, 3);
        total = 2;
    }

    public Estudiante buscar(String cedula) {
        for (int i = 0; i < total; i++) {
            if (lista[i].getCedula().equals(cedula)) return lista[i];
        }
        return null;
    }

    public Estudiante[] getListaEstudiantes() { return lista; }
    public int getTotalRegistrados() { return total; }
}