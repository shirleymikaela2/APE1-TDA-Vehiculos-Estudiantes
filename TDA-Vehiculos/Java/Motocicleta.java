// CONCEPTO: HERENCIA
// Motocicleta hereda los atributos y métodos de Vehiculo.
public class Motocicleta extends Vehiculo {

    // CONCEPTO: TIPOS DE DATOS PRIMITIVOS
    private int cilindrada;
    private boolean tieneMaletero;

    // CONCEPTO: CONSTRUCTOR
    public Motocicleta(String placa, String marca, String modelo,
                       int anio, double precio, boolean disponible,
                       int cilindrada, boolean tieneMaletero) {

        super(placa, marca, modelo, anio, precio, disponible);

        if (cilindrada < 50 || cilindrada > 2500) {
            throw new IllegalArgumentException(
                    "La cilindrada debe estar entre 50 y 2500 cc.");
        }

        this.cilindrada = cilindrada;
        this.tieneMaletero = tieneMaletero;
    }

    // CONCEPTO: POLIMORFISMO
    // Motocicleta implementa mostrarInformacion de forma diferente.
    @Override
    public void mostrarInformacion() {
        System.out.println("\nTipo: Motocicleta");
        mostrarDatosComunes();
        System.out.println("Cilindrada: " + cilindrada + " cc");
        System.out.println(
                "Tiene maletero: " + (tieneMaletero ? "Sí" : "No"));
    }
}