#ifndef GESTORESTUDIANTES_H
#define GESTORESTUDIANTES_H
#include "Estudiante.h"

class GestorEstudiantes {
private:
    Estudiante* lista[20];
    int total;

public:
    GestorEstudiantes() {
        lista[0] = new Estudiante("111", "Juan", "Perez", 14.5, 18.0);
        lista[1] = new Estudiante("222", "Maria", "Gomez", 20.0, 19.5);
        total = 2;
    }
    Estudiante* buscar(string cedula) {
        for(int i = 0; i < total; i++) {
            if(lista[i]->getCedula() == cedula) return lista[i];
        }
        return nullptr;
    }
    Estudiante* getEstudiante(int index) { return lista[index]; }
    int getTotalRegistrados() { return total; }
};
#endif
