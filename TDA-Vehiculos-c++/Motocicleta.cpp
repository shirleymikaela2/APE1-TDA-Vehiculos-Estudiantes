#include "Motocicleta.h"
#include <iostream>
#include <stdexcept>

using namespace std;

// CONCEPTO: CONSTRUCTOR
Motocicleta::Motocicleta(string placa, string marca, string modelo,
                         int anio, double precio, bool disponible,
                         int cilindrada, bool tieneMaletero) 
    // super llama al constructor de la clase padre Vehiculo.
    : Vehiculo(placa, marca, modelo, anio, precio, disponible) {

    if (cilindrada < 50 || cilindrada > 2500) {
        throw invalid_argument("La cilindrada debe estar entre 50 y 2500 cc.");
    }

    this->cilindrada = cilindrada;
    this->tieneMaletero = tieneMaletero;
}

// CONCEPTO: POLIMORFISMO
// Motocicleta implementa mostrarInformacion de forma diferente.
void Motocicleta::mostrarInformacion() {
    cout << "\nTipo: Motocicleta" << endl;
    mostrarDatosComunes();
    cout << "Cilindrada: " << cilindrada << " cc" << endl;
    cout << "Tiene maletero: " << (tieneMaletero ? "Sí" : "No") << endl;
}