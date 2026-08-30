// CONCEPTO: HERENCIA
// Automovil hereda los atributos y métodos de Vehiculo.
public class Automovil extends Vehiculo {

    // CONCEPTO: TIPOS DE DATOS PRIMITIVOS
    private int numeroPuertas;
    private boolean electrico;

    // CONCEPTO: CONSTRUCTOR
    public Automovil(String placa, String marca, String modelo,
                     int anio, double precio, boolean disponible,
                     int numeroPuertas, boolean electrico) {

        // super llama al constructor de la clase padre Vehiculo.
        super(placa, marca, modelo, anio, precio, disponible);

        if (numeroPuertas < 2 || numeroPuertas > 6) {
            throw new IllegalArgumentException(
                    "El automóvil debe tener entre 2 y 6 puertas.");
        }

        this.numeroPuertas = numeroPuertas;
        this.electrico = electrico;
    }

    // CONCEPTO: POLIMORFISMO
    // Automovil sobrescribe el método de Vehiculo.
    @Override
    public void mostrarInformacion() {
        System.out.println("\nTipo: Automóvil");
        mostrarDatosComunes();
        System.out.println("Número de puertas: " + numeroPuertas);
        System.out.println(
                "Eléctrico: " + (electrico ? "Sí" : "No"));
    }
}