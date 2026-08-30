// CONCEPTO: CLASE ABSTRACTA
// Contiene los atributos y comportamientos comunes de los vehículos.
public abstract class Vehiculo {

    // CONCEPTO: ENCAPSULAMIENTO
    // protected permite que las clases hijas utilicen estos atributos.
    protected String placa;
    protected String marca;
    protected String modelo;

    // CONCEPTO: TIPOS DE DATOS PRIMITIVOS
    protected int anio;
    protected double precio;
    protected boolean disponible;

    // CONCEPTO: CONSTRUCTOR
    // Inicializa los atributos de un vehículo.
    public Vehiculo(String placa, String marca, String modelo,
                    int anio, double precio, boolean disponible) {

        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La placa no puede estar vacía.");
        }

        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La marca no puede estar vacía.");
        }

        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El modelo no puede estar vacío.");
        }

        if (anio < 1886 || anio > 2100) {
            throw new IllegalArgumentException(
                    "El año debe estar entre 1886 y 2100.");
        }

        if (precio < 0) {
            throw new IllegalArgumentException(
                    "El precio no puede ser negativo.");
        }

        this.placa = placa.trim().toUpperCase();
        this.marca = marca.trim();
        this.modelo = modelo.trim();
        this.anio = anio;
        this.precio = precio;
        this.disponible = disponible;
    }

    // Permite obtener la placa desde el TDA RegistroVehiculos.
    public String getPlaca() {
        return placa;
    }

    // Muestra los atributos comunes.
    protected void mostrarDatosComunes() {
        System.out.println("Placa: " + placa);
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Año: " + anio);
        System.out.printf("Precio: $%.2f%n", precio);
        System.out.println(
                "Disponible: " + (disponible ? "Sí" : "No"));
    }

    // CONCEPTO: MÉTODO ABSTRACTO
    // Las clases hijas implementarán este método de manera diferente.
    public abstract void mostrarInformacion();
}