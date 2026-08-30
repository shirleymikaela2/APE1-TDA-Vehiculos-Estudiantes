#include "RegistroVehiculos.h"
#include <iostream>

using namespace std;

// CONCEPTO: CONSTRUCTOR
RegistroVehiculos::RegistroVehiculos() {
    cantidad = 0;
    for (int i = 0; i < CAPACIDAD; i++) {
        vehiculos[i] = nullptr;
    }
}

bool RegistroVehiculos::registrar(Vehiculo* nuevoVehiculo) {
    if (nuevoVehiculo == nullptr || estaLleno()) {
        return false;
    }

    if (existePlaca(nuevoVehiculo->getPlaca())) {
        return false;
    }

    vehiculos[cantidad] = nuevoVehiculo;
    cantidad++;

    return true;
}

// Busca si una placa ya está registrada.
bool RegistroVehiculos::existePlaca(string placa) {
    for (int i = 0; i < cantidad; i++) {
        // En C++ estándar usamos el operador == para comparar strings.
        if (vehiculos[i]->getPlaca() == placa) {
            return true;
        }
    }

    return false;
}

// Comprueba si se ocuparon las diez posiciones.
bool RegistroVehiculos::estaLleno() const {
    return cantidad == CAPACIDAD;
}

int RegistroVehiculos::getCantidad() const {
    return cantidad;
}

// Muestra todos los vehículos registrados.
void RegistroVehiculos::mostrarTodos() const {
    if (cantidad == 0) {
        cout << "\nNo existen vehículos registrados." << endl;
        return;
    }

    cout << "\n=== VEHÍCULOS REGISTRADOS ===" << endl;

    for (int i = 0; i < cantidad; i++) {
        cout << "\nRegistro número: " << (i + 1) << endl;

        // CONCEPTO: POLIMORFISMO
        // Se ejecuta el método del tipo real del objeto mediante el puntero.
        vehiculos[i]->mostrarInformacion();

        cout << "----------------------------" << endl;
    }

    cout << "Total registrado: " << cantidad << "/" << CAPACIDAD << endl;
}

// Destructor manual de C++ para liberar la memoria dinámica.
RegistroVehiculos::~RegistroVehiculos() {
    for (int i = 0; i < cantidad; i++) {
        delete vehiculos[i];
    }
}