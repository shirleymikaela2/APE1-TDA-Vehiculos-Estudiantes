#ifndef MOTOCICLETA_H
#define MOTOCICLETA_H

#include "Vehiculo.h"
#include <string>

// CONCEPTO: HERENCIA
// Motocicleta hereda los atributos y métodos de Vehiculo.
class Motocicleta : public Vehiculo {
private:
    // CONCEPTO: TIPOS DE DATOS PRIMITIVOS
    int cilindrada;
    bool tieneMaletero;

public:
    // CONCEPTO: CONSTRUCTOR
    Motocicleta(std::string placa, std::string marca, std::string modelo,
                int anio, double precio, bool disponible,
                int cilindrada, bool tieneMaletero);

    // CONCEPTO: POLIMORFISMO
    // Motocicleta implementa mostrarInformacion de forma diferente.
    void mostrarInformacion() override;
};

#endif