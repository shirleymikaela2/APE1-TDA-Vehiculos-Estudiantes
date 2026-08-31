public class Estudiante {
    private String cedula, nombres, apellidos;
    private double[] notas;
    private int contadorNotas;

    // Constructor de prueba
    public Estudiante(String cedula, String nombres, String apellidos, double[] notas, int contadorNotas) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.notas = notas;
        this.contadorNotas = contadorNotas;
    }

    public String getCedula() { return cedula; }
    public String getNombres() { return nombres; }
    public String getApellidos() { return apellidos; }
    public int calcularEdad() { return 20; } // Edad simulada
    public double[] getNotas() { return notas; }
    public int getContadorNotas() { return contadorNotas; }
}
