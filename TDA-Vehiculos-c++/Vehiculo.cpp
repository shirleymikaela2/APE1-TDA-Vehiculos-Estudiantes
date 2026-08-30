#include "Vehiculo.h"
#include <iostream>
#include <iomanip>
#include <stdexcept>
#include <algorithm>
#include <cctype>

using namespace std;

// Funciones auxiliares para emular el trim() y toUpperCase() de Java
string trim(const string& str) {
    size_t first = str.find_first_not_of(" \t\r\n");
    if (string::npos == first) return "";
    size_t last = str.find_last_not_of(" \t\r\n");
    return str.substr(first, (last - first + 1));
}

string toUpperCase(string str) {
    transform(str.begin(), str.end(), str.begin(), ::toupper);
    return str;
}

// CONCEPTO: CONSTRUCTOR
// Inicializa los atributos de un vehículo.
Vehiculo::Vehiculo(string placa, string marca, string modelo,
                   int anio, double precio, bool disponible) {

    string placaTrim = trim(placa);
    if (placaTrim.empty()) {
        throw invalid_argument("La placa no puede estar vacía.");
    }

    string marcaTrim = trim(marca);
    if (marcaTrim.empty()) {
        throw invalid_argument("La marca no puede estar vacía.");
    }

    string modeloTrim = trim(modelo);
    if (modeloTrim.empty()) {
        throw invalid_argument("El modelo no puede estar vacío.");
    }

    if (anio < 1886 || anio > 2100) {
        throw invalid_argument("El año debe estar entre 1886 y 2100.");
    }

    if (precio < 0) {
        throw invalid_argument("El precio no puede ser negativo.");
    }

    this->placa = toUpperCase(placaTrim);
    this->marca = marcaTrim;
    this->modelo = modeloTrim;
    this->anio = anio;
    this->precio = precio;
    this->disponible = disponible;
}

// Permite obtener la placa desde el TDA RegistroVehiculos.
string Vehiculo::getPlaca() const {
    return placa;
}

// Muestra los atributos comunes.
void Vehiculo::mostrarDatosComunes() const {
    cout << "Placa: " << placa << endl;
    cout << "Marca: " << marca << endl;
    cout << "Modelo: " << modelo << endl;
    cout << "Año: " << anio << endl;
    // std::fixed y std::setprecision(2) emulan el comportamiento de printf("%.2f")
    cout << "Precio: $" << fixed << setprecision(2) << precio << endl;
    cout << "Disponible: " << (disponible ? "Sí" : "No") << endl;
}