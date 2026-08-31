#ifndef GESTORESTUDIANTES_H
#define GESTORESTUDIANTES_H

#include "Estudiante.h"
#include <string>

using namespace std;

class GestorEstudiantes {

private:
    static const int MAX_ESTUDIANTES = 20;

    Estudiante estudiantes[MAX_ESTUDIANTES];
    int cantidadEstudiantes;

public:
    GestorEstudiantes();

    bool registrar(
        const Estudiante& nuevoEstudiante
    );

    Estudiante* buscar(
        const string& cedula
    );

    void mostrarTodos() const;

    bool estaLleno() const;
    int getCantidadEstudiantes() const;
    int getCapacidad() const;
};

#endif
