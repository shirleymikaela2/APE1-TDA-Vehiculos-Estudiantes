#include "Estudiante.h"
#include "GestorEstudiantes.h"

#include <iostream>

using namespace std;

int main() {
    try {
        GestorEstudiantes gestor;

        Estudiante estudiante1(
            "1800000001",
            "Ana Maria",
            "Perez Lopez",
            15,
            5,
            2005
        );

        Estudiante estudiante2(
            "1800000002",
            "Carlos Andres",
            "Gomez Sanchez",
            20,
            10,
            2004
        );

        cout << "Registro del primer estudiante: "
             << gestor.registrar(estudiante1)
             << endl;

        cout << "Registro del segundo estudiante: "
             << gestor.registrar(estudiante2)
             << endl;

        cout << "Cantidad registrada: "
             << gestor.getCantidadEstudiantes()
             << "/"
             << gestor.getCapacidad()
             << endl;

        gestor.mostrarTodos();

        cout << "\n=== BUSQUEDA POR CEDULA ==="
             << endl;

        Estudiante* encontrado =
            gestor.buscar("1800000002");

        if (encontrado != nullptr) {
            cout << "Estudiante encontrado:"
                 << endl;

            encontrado->mostrarDatos();
        } else {
            cout << "No se encontro al estudiante."
                 << endl;
        }

        cout << "\n=== BUSQUEDA INCORRECTA ==="
             << endl;

        Estudiante* inexistente =
            gestor.buscar("9999999999");

        if (inexistente == nullptr) {
            cout << "No existe un estudiante "
                    "con esa cedula."
                 << endl;
        }

    } catch (const exception& error) {
        cout << "Error: "
             << error.what()
             << endl;
    }

    return 0;
}
