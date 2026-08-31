#include <iostream>
#include <string>
#include <iomanip>
#include "GestorEstudiantes.h"
#include "Estudiante.h"

using namespace std;

// Funciones de Héctor
double calcularPromedioIndividual(Estudiante* est) {
    if (est->getContadorNotas() == 0) return 0.0;
    double suma = 0;
    for (int i = 0; i < est->getContadorNotas(); i++) {
        suma += est->getNota(i); // Asume que Josué crea este getter
    }
    return suma / est->getContadorNotas();
}

void calcularPromedioGeneral(GestorEstudiantes& gestor) {
    if (gestor.getTotalRegistrados() == 0) {
        cout << "No se han registrado calificaciones de estudiantes." << endl;
        return;
    }

    double sumaGeneral = 0;
    int totalNotasGlobales = 0;

    for (int i = 0; i < gestor.getTotalRegistrados(); i++) {
        Estudiante* est = gestor.getEstudiante(i);
        for (int j = 0; j < est->getContadorNotas(); j++) {
            sumaGeneral += est->getNota(j);
            totalNotasGlobales++;
        }
    }

    if (totalNotasGlobales == 0) {
        cout << "No se han registrado calificaciones de estudiantes." << endl;
    } else {
        double promedioGeneral = sumaGeneral / totalNotasGlobales;
        cout << "El promedio general de calificaciones del curso es: " 
             << fixed << setprecision(2) << promedioGeneral << endl;
    }
}

int main() {
    GestorEstudiantes gestor;
    string input;
    int opcion = 0;

    while (opcion != 5) {
        cout << "\n=== GESTOR DE PERSONAS ===" << endl;
        cout << "1.- Estudiantes." << endl;
        cout << "2.- Registro de calificaciones." << endl;
        cout << "3.- Determinar el promedio de notas de un estudiante." << endl;
        cout << "4.- Determinar el promedio de notas del curso." << endl;
        cout << "5.- Salir" << endl;
        cout << "Teclee su opcion (1-4): ";
        
        getline(cin, input);
        try {
            opcion = stoi(input);
        } catch (...) {
            opcion = 0;
        }

        switch (opcion) {
            case 1:
                // gestor.menuEstudiantes();
                cout << "Modulo de Cris en construccion..." << endl;
                break;
            case 2:
                // gestor.menuCalificaciones();
                cout << "Modulo de Lenin en construccion..." << endl;
                break;
            case 3: {
                cout << "Ingrese el numero de cedula: ";
                string cedulaBusqueda;
                getline(cin, cedulaBusqueda);
                
                Estudiante* est = gestor.buscar(cedulaBusqueda);
                
                if (est != nullptr) {
                    double promedio = calcularPromedioIndividual(est);
                    cout << "Nombres: " << est->getNombres() << endl;
                    cout << "Apellidos: " << est->getApellidos() << endl;
                    // Asumiendo que le pasan el año actual para calcular edad
                    cout << "Edad: " << est->calcularEdad(2026) << " anios" << endl; 
                    cout << "Promedio de calificaciones: " << fixed << setprecision(2) << promedio << endl;
                } else {
                    cout << "Error: no se encontro un estudiante con el numero de cedula indicado." << endl;
                }
                break;
            }
            case 4:
                calcularPromedioGeneral(gestor);
                break;
            case 5:
                cout << "Saliendo del programa..." << endl;
                break;
            default:
                cout << "Opcion no valida. Intente nuevamente." << endl;
        }
    }
    return 0;
}
