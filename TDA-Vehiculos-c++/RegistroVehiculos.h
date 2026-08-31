#ifndef REGISTROVEHICULOS_H
#define REGISTROVEHICULOS_H

#include "Vehiculo.h"
#include <string>

// CONCEPTO: TIPO DE DATO ABSTRACTO
// Administra un conjunto de vehículos mediante operaciones públicas.
class RegistroVehiculos {
private:
    // Capacidad máxima del arreglo.
    static const int CAPACIDAD = 10;

    // CONCEPTO: ARREGLO ESTÁTICO
    // Su tamaño se establece al crearlo y no puede cambiar.
    // Se utiliza un arreglo de punteros para soportar polimorfismo en C++.
    Vehiculo* vehiculos[CAPACIDAD];

    // Representa el tamaño lógico del arreglo.
    int cantidad;

public:
    // CONCEPTO: CONSTRUCTOR
    RegistroVehiculos();

    /*
     * PRECONDICIONES:
     * - El vehículo no debe ser null.
     * - El arreglo no debe estar lleno.
     * - La placa no debe estar repetida.
     *
     * POSTCONDICIÓN:
     * - Si se registra correctamente, cantidad aumenta en uno.
     */
    bool registrar(Vehiculo* nuevoVehiculo);

    // Busca si una placa ya está registrada.
    bool existePlaca(std::string placa);

    // Comprueba si se ocuparon las diez posiciones.
    bool estaLleno() const;

    int getCantidad() const;

    // Muestra todos los vehículos registrados.
    void mostrarTodos() const;

    // Destructor para evitar fugas de memoria.
    ~RegistroVehiculos();
};

#endif