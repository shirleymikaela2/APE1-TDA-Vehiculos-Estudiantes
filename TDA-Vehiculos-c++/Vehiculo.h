#ifndef VEHICULO_H
#define VEHICULO_H

#include <string>

// CONCEPTO: CLASE ABSTRACTA
// Contiene los atributos y comportamientos comunes de los vehículos.
class Vehiculo {
protected:
    // CONCEPTO: ENCAPSULAMIENTO
    // protected permite que las clases hijas utilicen estos atributos.
    std::string placa;
    std::string marca;
    std::string modelo;

    // CONCEPTO: TIPOS DE DATOS PRIMITIVOS
    int anio;
    double precio;
    bool disponible;

public:
    // CONCEPTO: CONSTRUCTOR
    // Inicializa los atributos de un vehículo.
    Vehiculo(std::string placa, std::string marca, std::string modelo,
             int anio, double precio, bool disponible);

    // Permite obtener la placa desde el TDA RegistroVehiculos.
    std::string getPlaca() const;

    // Muestra los atributos comunes.
    void mostrarDatosComunes() const;

    // CONCEPTO: MÉTODO ABSTRACTO
    // Las clases hijas implementarán este método de manera diferente.
    // El "= 0" convierte a esta función en virtual pura, haciendo abstracta la clase.
    virtual void mostrarInformacion() = 0;

    // Destructor virtual (obligatorio en C++ para clases con herencia y polimorfismo)
    virtual ~Vehiculo() = default;
};

#endif