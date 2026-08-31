#include "GestorEstudiantes.h"

#include <iostream>

using namespace std;

GestorEstudiantes::GestorEstudiantes() {
    cantidadEstudiantes = 0;
}

bool GestorEstudiantes::registrar(
    const Estudiante& nuevoEstudiante
) {
    if (estaLleno()) {
        return false;
    }

    if (buscar(nuevoEstudiante.getCedula())
            != nullptr) {

        return false;
    }

    estudiantes[cantidadEstudiantes] =
        nuevoEstudiante;

    cantidadEstudiantes++;

    return true;
}

Estudiante* GestorEstudiantes::buscar(
    const string& cedula
) {
    for (int i = 0;
         i < cantidadEstudiantes;
         i++) {

        if (estudiantes[i].getCedula()
                == cedula) {

            return &estudiantes[i];
        }
    }

    return nullptr;
}

void GestorEstudiantes::mostrarTodos() const {
    if (cantidadEstudiantes == 0) {
        cout << "No existen estudiantes registrados."
             << endl;

        return;
    }

    cout << "\n=== ESTUDIANTES REGISTRADOS ==="
         << endl;

    for (int i = 0;
         i < cantidadEstudiantes;
         i++) {

        cout << "\nAutonumerico: "
             << (i + 1) << endl;

        estudiantes[i].mostrarDatos();

        cout << "----------------------------"
             << endl;
    }
}

bool GestorEstudiantes::estaLleno() const {
    return cantidadEstudiantes
        == MAX_ESTUDIANTES;
}

int GestorEstudiantes::getCantidadEstudiantes()
        const {

    return cantidadEstudiantes;
}

int GestorEstudiantes::getCapacidad() const {
    return MAX_ESTUDIANTES;
}
