import java.time.LocalDate;
import java.time.Period;

public class Estudiante {

    private String cedula;
    private String nombres;
    private String apellidos;
    private LocalDate fechaNacimiento;

    private double[] notas;
    private int cantidadNotas;

    public Estudiante(String cedula, String nombres,
                      String apellidos, LocalDate fechaNacimiento) {

        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;

        notas = new double[7];
        cantidadNotas = 0;
    }

    public int calcularEdad() {
        return Period.between(
                fechaNacimiento,
                LocalDate.now()
        ).getYears();
    }

    public boolean agregarNota(double nota) {

        if (cantidadNotas < 7) {
            notas[cantidadNotas] = nota;
            cantidadNotas++;
            return true;
        }

        return false;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double[] getNotas() {
        return notas;
    }

    public int getCantidadNotas() {
        return cantidadNotas;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
