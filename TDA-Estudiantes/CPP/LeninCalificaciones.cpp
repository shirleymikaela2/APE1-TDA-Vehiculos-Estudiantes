#include <iostream>
#include <string>

using namespace std;
// Nota: El arreglo 'estudiantes' y la función 'buscar' vendran del archivo principal
int calcularEdad(string fechaNacimiento) {
    size_t lastSlash = fechaNacimiento.find_last_of('/');
    if (lastSlash != string::npos) {
        string anioStr = fechaNacimiento.substr(lastSlash + 1);
        try {
            int anioNacimiento = stoi(anioStr);
            return 2026 - anioNacimiento;
        } catch (...) { return 0; }
    }
    return 0;
}

void registroCalificaciones() {
    bool continuarModulo = true;

    while (continuarModulo) {
        cout << "\nIngrese el numero de cedula del estudiante: ";
        string cedula;
        getline(cin, cedula);
        
        // Llama a la funcion buscar del archivo principal
        int index = buscar(cedula); 

        if (index == -1) {
            cout << "Error: Estudiante no encontrado.\n";
            cout << "Desea ingresar otra cedula? (S/N): ";
            string resp;
            getline(cin, resp);
            if (resp == "N" || resp == "n") continuarModulo = false;
            continue;
        }

        int edad = calcularEdad(estudiantes[index].fechaNacimiento);
        
        cout << "\n--- Datos del Estudiante ---\n";
        cout << "Nombres y Apellidos: " << estudiantes[index].nombres << " " << estudiantes[index].apellidos << "\n";
        cout << "Edad: " << edad << " anios\n";

        bool gestionandoNotas = true;
        while (gestionandoNotas) {
            cout << "\n--- Calificaciones Registradas ---\n";
            if (estudiantes[index].cantidadNotas == 0) {
                cout << "No hay calificaciones registradas.\n";
            } else {
                for (int i = 0; i < estudiantes[index].cantidadNotas; i++) {
                    cout << "[" << (i + 1) << "] Nota: " << estudiantes[index].notas[i] << "\n";
                }
            }

            if (estudiantes[index].cantidadNotas == 7) {
                cout << "\nAviso: Se han ingresado todas las calificaciones posibles (7/7).\n";
            }

            cout << "\nOpciones:\n1. Insertar nota\n2. Modificar nota\n3. Eliminar nota\n4. Buscar otro estudiante\n5. Volver al menu\nOpcion: ";
            int opcionNotas;
            cin >> opcionNotas; cin.ignore();

            switch (opcionNotas) {
                case 1:
                    if (estudiantes[index].cantidadNotas < 7) {
                        cout << "Nueva nota: ";
                        cin >> estudiantes[index].notas[estudiantes[index].cantidadNotas]; cin.ignore();
                        estudiantes[index].cantidadNotas++;
                        cout << "Nota registrada.\n";
                    } else cout << "Error: Limite maximo de 7 notas alcanzado.\n";
                    break;
                case 2:
                    if (estudiantes[index].cantidadNotas > 0) {
                        cout << "Numero de nota a modificar (1-" << estudiantes[index].cantidadNotas << "): ";
                        int posMod; cin >> posMod; cin.ignore();
                        if (posMod >= 1 && posMod <= estudiantes[index].cantidadNotas) {
                            cout << "Nuevo valor: ";
                            cin >> estudiantes[index].notas[posMod - 1]; cin.ignore();
                            cout << "Nota modificada.\n";
                        } else cout << "Posicion invalida.\n";
                    } else cout << "No hay notas.\n";
                    break;
                case 3:
                    if (estudiantes[index].cantidadNotas > 0) {
                        cout << "Numero de nota a eliminar (1-" << estudiantes[index].cantidadNotas << "): ";
                        int posElim; cin >> posElim; cin.ignore();
                        if (posElim >= 1 && posElim <= estudiantes[index].cantidadNotas) {
                            for (int i = posElim - 1; i < estudiantes[index].cantidadNotas - 1; i++) {
                                estudiantes[index].notas[i] = estudiantes[index].notas[i + 1];
                            }
                            estudiantes[index].cantidadNotas--;
                            cout << "Nota eliminada.\n";
                        } else cout << "Posicion invalida.\n";
                    } else cout << "No hay notas.\n";
                    break;
                case 4: gestionandoNotas = false; break;
                case 5: gestionandoNotas = false; continuarModulo = false; break;
                default: cout << "Opcion no valida.\n";
            }
        }
    }
}
