#ifndef AUTOMOVIL_H
#define AUTOMOVIL_H

#include "Vehiculo.h" // Incluye la clase padre
#include <string>

// CONCEPTO: HERENCIA
// Automovil hereda los atributos y métodos de Vehiculo.
class Automovil : public Vehiculo {
private:
    // CONCEPTO: TIPOS DE DATOS PRIMITIVOS
    int numeroPuertas;
    bool electrico;

public:
    // CONCEPTO: CONSTRUCTOR
    // Solo se declaran las firmas de los métodos
    Automovil(std::string placa, std::string marca, std::string modelo,
              int anio, double precio, bool disponible,
              int numeroPuertas, bool electrico);

    // CONCEPTO: POLIMORFISMO
    // Automovil sobrescribe el método de Vehiculo.
    void mostrarInformacion() override;
};

#endif