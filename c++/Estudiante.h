#ifndef ESTUDIANTE_H
#define ESTUDIANTE_H
#include <string>

using namespace std;

class Estudiante {
private:
    string cedula, nombres, apellidos;
    double notas[7];
    int contadorNotas;

public:
    Estudiante(string c, string n, string a, double n1, double n2) {
        cedula = c; nombres = n; apellidos = a;
        notas[0] = n1; notas[1] = n2; 
        contadorNotas = 2;
    }
    string getCedula() { return cedula; }
    string getNombres() { return nombres; }
    string getApellidos() { return apellidos; }
    int calcularEdad(int anioActual) { return 20; }
    double getNota(int index) { return notas[index]; }
    int getContadorNotas() { return contadorNotas; }
};
#endif
