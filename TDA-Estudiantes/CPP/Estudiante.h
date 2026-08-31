#ifndef ESTUDIANTE_H
#define ESTUDIANTE_H

#include <string>

using namespace std;

class Estudiante {

private:
    string cedula;
    string nombres;
    string apellidos;

    int diaNacimiento;
    int mesNacimiento;
    int anioNacimiento;

    static const int MAX_NOTAS = 7;

    double notas[MAX_NOTAS];
    int cantidadNotas;

    bool fechaValida(
        int dia,
        int mes,
        int anio
    ) const;

public:
    Estudiante();

    Estudiante(
        string cedula,
        string nombres,
        string apellidos,
        int diaNacimiento,
        int mesNacimiento,
        int anioNacimiento
    );

    string getCedula() const;
    string getNombres() const;
    string getApellidos() const;

    int getDiaNacimiento() const;
    int getMesNacimiento() const;
    int getAnioNacimiento() const;

    int getCantidadNotas() const;
    int getCapacidadNotas() const;

    int calcularEdad() const;
    void mostrarDatos() const;
};

#endif
