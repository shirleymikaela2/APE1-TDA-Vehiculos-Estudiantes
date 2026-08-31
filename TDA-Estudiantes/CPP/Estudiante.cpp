#include "Estudiante.h"

#include <ctime>
#include <iostream>
#include <stdexcept>

using namespace std;

Estudiante::Estudiante() {
    cedula = "";
    nombres = "";
    apellidos = "";

    diaNacimiento = 1;
    mesNacimiento = 1;
    anioNacimiento = 1900;

    cantidadNotas = 0;

    for (int i = 0; i < MAX_NOTAS; i++) {
        notas[i] = 0.0;
    }
}

Estudiante::Estudiante(
    string cedula,
    string nombres,
    string apellidos,
    int diaNacimiento,
    int mesNacimiento,
    int anioNacimiento
) {
    if (cedula.empty()) {
        throw invalid_argument(
            "La cedula no puede estar vacia."
        );
    }

    if (nombres.empty()) {
        throw invalid_argument(
            "Los nombres no pueden estar vacios."
        );
    }

    if (apellidos.empty()) {
        throw invalid_argument(
            "Los apellidos no pueden estar vacios."
        );
    }

    if (!fechaValida(
            diaNacimiento,
            mesNacimiento,
            anioNacimiento)) {

        throw invalid_argument(
            "La fecha de nacimiento no es valida."
        );
    }

    this->cedula = cedula;
    this->nombres = nombres;
    this->apellidos = apellidos;

    this->diaNacimiento = diaNacimiento;
    this->mesNacimiento = mesNacimiento;
    this->anioNacimiento = anioNacimiento;

    cantidadNotas = 0;

    for (int i = 0; i < MAX_NOTAS; i++) {
        notas[i] = 0.0;
    }
}

bool Estudiante::fechaValida(
    int dia,
    int mes,
    int anio
) const {
    if (anio < 1900 || mes < 1 || mes > 12) {
        return false;
    }

    int diasPorMes[] = {
        31, 28, 31, 30, 31, 30,
        31, 31, 30, 31, 30, 31
    };

    bool bisiesto =
        (anio % 400 == 0)
        || (anio % 4 == 0 && anio % 100 != 0);

    if (bisiesto) {
        diasPorMes[1] = 29;
    }

    return dia >= 1
        && dia <= diasPorMes[mes - 1];
}

int Estudiante::calcularEdad() const {
    time_t tiempoActual = time(nullptr);
    tm* fechaActual = localtime(&tiempoActual);

    int anioActual = fechaActual->tm_year + 1900;
    int mesActual = fechaActual->tm_mon + 1;
    int diaActual = fechaActual->tm_mday;

    int edad = anioActual - anioNacimiento;

    bool aunNoCumple =
        mesActual < mesNacimiento
        || (mesActual == mesNacimiento
        && diaActual < diaNacimiento);

    if (aunNoCumple) {
        edad--;
    }

    return edad;
}

void Estudiante::mostrarDatos() const {
    cout << "Cedula: " << cedula << endl;
    cout << "Nombres: " << nombres << endl;
    cout << "Apellidos: " << apellidos << endl;

    cout << "Fecha de nacimiento: "
         << diaNacimiento << "/"
         << mesNacimiento << "/"
         << anioNacimiento << endl;

    cout << "Edad: "
         << calcularEdad() << endl;

    cout << "Cantidad de notas: "
         << cantidadNotas << "/"
         << MAX_NOTAS << endl;
}

string Estudiante::getCedula() const {
    return cedula;
}

string Estudiante::getNombres() const {
    return nombres;
}

string Estudiante::getApellidos() const {
    return apellidos;
}

int Estudiante::getDiaNacimiento() const {
    return diaNacimiento;
}

int Estudiante::getMesNacimiento() const {
    return mesNacimiento;
}

int Estudiante::getAnioNacimiento() const {
    return anioNacimiento;
}

int Estudiante::getCantidadNotas() const {
    return cantidadNotas;
}

int Estudiante::getCapacidadNotas() const {
    return MAX_NOTAS;
}
