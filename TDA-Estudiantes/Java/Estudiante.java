import java.time.DateTimeException;
import java.time.LocalDate;

public class Estudiante {

    public static final int MAX_NOTAS = 7;

    private String cedula;
    private String nombres;
    private String apellidos;
    private int diaNacimiento;
    private int mesNacimiento;
    private int anioNacimiento;

    private final double[] notas;
    private int cantidadNotas;

    public Estudiante(
            String cedula,
            String nombres,
            String apellidos,
            int diaNacimiento,
            int mesNacimiento,
            int anioNacimiento) {

        validarDatos(
                cedula,
                nombres,
                apellidos,
                diaNacimiento,
                mesNacimiento,
                anioNacimiento
        );

        this.cedula = cedula.trim();
        this.nombres = nombres.trim();
        this.apellidos = apellidos.trim();
        this.diaNacimiento = diaNacimiento;
        this.mesNacimiento = mesNacimiento;
        this.anioNacimiento = anioNacimiento;

        notas = new double[MAX_NOTAS];
        cantidadNotas = 0;
    }

    public void actualizarDatos(
            String cedula,
            String nombres,
            String apellidos,
            int dia,
            int mes,
            int anio) {

        validarDatos(cedula, nombres, apellidos, dia, mes, anio);

        this.cedula = cedula.trim();
        this.nombres = nombres.trim();
        this.apellidos = apellidos.trim();
        this.diaNacimiento = dia;
        this.mesNacimiento = mes;
        this.anioNacimiento = anio;
    }

    private void validarDatos(
            String cedula,
            String nombres,
            String apellidos,
            int dia,
            int mes,
            int anio) {

        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("Cédula vacía.");
        }

        if (nombres == null || nombres.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombres vacíos.");
        }

        if (apellidos == null || apellidos.trim().isEmpty()) {
            throw new IllegalArgumentException("Apellidos vacíos.");
        }

        try {
            LocalDate fecha = LocalDate.of(anio, mes, dia);

            if (fecha.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException(
                        "La fecha no puede ser futura."
                );
            }
        } catch (DateTimeException error) {
            throw new IllegalArgumentException(
                    "Fecha de nacimiento inválida."
            );
        }
    }

    public int calcularEdad() {
        LocalDate nacimiento = LocalDate.of(
                anioNacimiento,
                mesNacimiento,
                diaNacimiento
        );

        LocalDate actual = LocalDate.now();
        int edad = actual.getYear() - nacimiento.getYear();

        if (actual.getDayOfYear() < nacimiento.getDayOfYear()) {
            edad--;
        }

        return edad;
    }

    public void mostrarDatos() {
        System.out.println("Cédula: " + cedula);
        System.out.println("Nombres: " + nombres);
        System.out.println("Apellidos: " + apellidos);

        System.out.println(
                "Fecha de nacimiento: "
                + diaNacimiento + "/"
                + mesNacimiento + "/"
                + anioNacimiento
        );

        System.out.println("Edad: " + calcularEdad());

        System.out.println(
                "Cantidad de notas: "
                + cantidadNotas + "/" + MAX_NOTAS
        );
    }

    public boolean agregarNota(double nota) {
        if (cantidadNotas >= MAX_NOTAS
                || nota < 0
                || nota > 10) {

            return false;
        }

        notas[cantidadNotas] = nota;
        cantidadNotas++;

        return true;
    }

    public boolean modificarNota(int posicion, double nota) {
        if (posicion < 0
                || posicion >= cantidadNotas
                || nota < 0
                || nota > 10) {

            return false;
        }

        notas[posicion] = nota;
        return true;
    }

    public boolean eliminarNota(int posicion) {
        if (posicion < 0 || posicion >= cantidadNotas) {
            return false;
        }

        for (int i = posicion; i < cantidadNotas - 1; i++) {
            notas[i] = notas[i + 1];
        }

        notas[cantidadNotas - 1] = 0;
        cantidadNotas--;

        return true;
    }

    public void mostrarNotas() {
        if (cantidadNotas == 0) {
            System.out.println(
                    "No existen calificaciones registradas."
            );
            return;
        }

        for (int i = 0; i < cantidadNotas; i++) {
            System.out.printf(
                    "%d. %.2f%n",
                    i + 1,
                    notas[i]
            );
        }
    }

    public double calcularPromedio() {
        if (cantidadNotas == 0) {
            return 0;
        }

        double suma = 0;

        for (int i = 0; i < cantidadNotas; i++) {
            suma += notas[i];
        }

        return suma / cantidadNotas;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public int getDiaNacimiento() {
        return diaNacimiento;
    }

    public int getMesNacimiento() {
        return mesNacimiento;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    public int getCantidadNotas() {
        return cantidadNotas;
    }
}
