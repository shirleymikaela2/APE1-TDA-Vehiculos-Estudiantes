#include "Automovil.h"
#include <iostream>
#include <stdexcept>

using namespace std;

// CONCEPTO: CONSTRUCTOR
Automovil::Automovil(string placa, string marca, string modelo,
                     int anio, double precio, bool disponible,
                     int numeroPuertas, bool electrico) 
    // super llama al constructor de la clase padre Vehiculo.
    : Vehiculo(placa, marca, modelo, anio, precio, disponible) {

    if (numeroPuertas < 2 || numeroPuertas > 6) {
        throw invalid_argument("El automóvil debe tener entre 2 y 6 puertas.");
    }

    this->numeroPuertas = numeroPuertas;
    this->electrico = electrico;
}

// CONCEPTO: POLIMORFISMO
// Automovil sobrescribe el método de Vehiculo.
void Automovil::mostrarInformacion() {
    cout << "\nTipo: Automóvil" << endl;
    mostrarDatosComunes();
    cout << "Número de puertas: " << numeroPuertas << endl;
    cout << "Eléctrico: " << (electrico ? "Sí" : "No") << endl;
}