// CONCEPTO: TIPO DE DATO ABSTRACTO
// Administra un conjunto de vehículos mediante operaciones públicas.
public class RegistroVehiculos {

    // Capacidad máxima del arreglo.
    private static final int CAPACIDAD = 10;

    // CONCEPTO: ARREGLO ESTÁTICO
    // Su tamaño se establece al crearlo y no puede cambiar.
    private final Vehiculo[] vehiculos =
            new Vehiculo[CAPACIDAD];

    // Representa el tamaño lógico del arreglo.
    private int cantidad;

    // CONCEPTO: CONSTRUCTOR
    public RegistroVehiculos() {
        cantidad = 0;
    }

    /*
     * PRECONDICIONES:
     * - El vehículo no debe ser null.
     * - El arreglo no debe estar lleno.
     * - La placa no debe estar repetida.
     *
     * POSTCONDICIÓN:
     * - Si se registra correctamente, cantidad aumenta en uno.
     */
    public boolean registrar(Vehiculo nuevoVehiculo) {
        if (nuevoVehiculo == null || estaLleno()) {
            return false;
        }

        if (existePlaca(nuevoVehiculo.getPlaca())) {
            return false;
        }

        vehiculos[cantidad] = nuevoVehiculo;
        cantidad++;

        return true;
    }

    // Busca si una placa ya está registrada.
    public boolean existePlaca(String placa) {
        for (int i = 0; i < cantidad; i++) {
            if (vehiculos[i].getPlaca()
                    .equalsIgnoreCase(placa.trim())) {
                return true;
            }
        }

        return false;
    }

    // Comprueba si se ocuparon las diez posiciones.
    public boolean estaLleno() {
        return cantidad == CAPACIDAD;
    }

    public int getCantidad() {
        return cantidad;
    }

    // Muestra todos los vehículos registrados.
    public void mostrarTodos() {
        if (cantidad == 0) {
            System.out.println(
                    "\nNo existen vehículos registrados.");
            return;
        }

        System.out.println("\n=== VEHÍCULOS REGISTRADOS ===");

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nRegistro número: " + (i + 1));

            // CONCEPTO: POLIMORFISMO
            // Se ejecuta el método del tipo real del objeto.
            vehiculos[i].mostrarInformacion();

            System.out.println("----------------------------");
        }

        System.out.println(
                "Total registrado: " + cantidad + "/" + CAPACIDAD);
    }
}