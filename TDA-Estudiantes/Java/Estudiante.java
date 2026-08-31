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

    // Vector estático de máximo siete calificaciones.
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
    }

    private void validarDatos(
            String cedula,
            String nombres,
            String apellidos,
            int dia,
            int mes,
            int anio) {

        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La cédula no puede estar vacía.");
        }

        if (nombres == null || nombres.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Los nombres no pueden estar vacíos.");
        }

        if (apellidos == null || apellidos.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Los apellidos no pueden estar vacíos.");
        }

        try {
            LocalDate fecha = LocalDate.of(anio, mes, dia);

            if (fecha.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException(
                        "La fecha no puede ser futura.");
            }

        } catch (DateTimeException error) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento no es válida.");
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

        boolean noHaCumplido =
                actual.getMonthValue()
                        < nacimiento.getMonthValue()
                || (actual.getMonthValue()
                        == nacimiento.getMonthValue()
                && actual.getDayOfMonth()
                        < nacimiento.getDayOfMonth());

        if (noHaCumplido) {
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
                        + anioNacimiento);

        System.out.println("Edad: " + calcularEdad());

        System.out.println(
                "Notas registradas: "
                        + cantidadNotas + "/" + MAX_NOTAS);
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