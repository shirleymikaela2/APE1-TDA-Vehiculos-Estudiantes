#include <iostream>
#include <string>
#include <stdexcept>
#include <algorithm>
#include "RegistroVehiculos.h"
#include "Automovil.h"
#include "Motocicleta.h"
#include <windows.h>

using namespace std;

// --- FUNCIONES AUXILIARES PARA LECTURA DE DATOS ---

string trimInput(const string& str) {
    size_t first = str.find_first_not_of(" \t\r\n");
    if (string::npos == first) return "";
    size_t last = str.find_last_not_of(" \t\r\n");
    return str.substr(first, (last - first + 1));
}

string leerTexto(const string& mensaje) {
    string texto;
    do {
        cout << mensaje;
        getline(cin, texto);
        texto = trimInput(texto);

        if (texto.empty()) {
            cout << "El campo no puede estar vacio." << endl;
        }
    } while (texto.empty());

    return texto;
}

int leerEntero() {
    string entrada;
    while (true) {
        try {
            getline(cin, entrada);
            entrada = trimInput(entrada);
            return stoi(entrada);
        } catch (...) {
            cout << "Ingrese un numero entero valido: ";
        }
    }
}

double leerDouble() {
    string entrada;
    while (true) {
        try {
            getline(cin, entrada);
            entrada = trimInput(entrada);
            replace(entrada.begin(), entrada.end(), ',', '.');
            return stod(entrada);
        } catch (...) {
            cout << "Ingrese un numero decimal valido: ";
        }
    }
}

bool leerBooleano(const string& pregunta) {
    int respuesta;
    do {
        cout << pregunta << endl;
        cout << "1. Si" << endl;
        cout << "2. No" << endl;
        cout << "Respuesta: ";
        respuesta = leerEntero();

        if (respuesta != 1 && respuesta != 2) {
            cout << "Seleccione unicamente 1 o 2." << endl;
        }
    } while (respuesta != 1 && respuesta != 2);

    return respuesta == 1;
}

// --- MÉTODOS DE REGISTRO ---

void registrarAutomovil(RegistroVehiculos& registro) {
    if (registro.estaLleno()) {
        cout << "Ya se registraron los 10 vehiculos permitidos." << endl;
        return;
    }

    try {
        cout << "\n=== NUEVO AUTOMOVIL ===" << endl;

        string placa = leerTexto("Placa: ");

        if (registro.existePlaca(placa)) {
            cout << "Ya existe un vehiculo con esa placa." << endl;
            return;
        }

        string marca = leerTexto("Marca: ");
        string modelo = leerTexto("Modelo: ");

        cout << "Anio: ";
        int anio = leerEntero();

        cout << "Precio: ";
        double precio = leerDouble();

        bool disponible = leerBooleano("Esta disponible?");

        cout << "Numero de puertas: ";
        int numeroPuertas = leerEntero();

        bool electrico = leerBooleano("Es electrico?");

        // CONCEPTO: INSTANCIACIÓN Y POLIMORFISMO
        Vehiculo* nuevoVehiculo = new Automovil(
                placa, marca, modelo, anio, precio,
                disponible, numeroPuertas, electrico);

        if (registro.registrar(nuevoVehiculo)) {
            cout << "Automovil registrado correctamente." << endl;
        } else {
            cout << "No fue posible registrar el automovil." << endl;
            delete nuevoVehiculo; // Liberar memoria si falla el registro
        }

    } catch (const invalid_argument& error) {
        cout << "Error: " << error.what() << endl;
    }
}

void registrarMotocicleta(RegistroVehiculos& registro) {
    if (registro.estaLleno()) {
        cout << "Ya se registraron los 10 vehiculos permitidos." << endl;
        return;
    }

    try {
        cout << "\n=== NUEVA MOTOCICLETA ===" << endl;

        string placa = leerTexto("Placa: ");

        if (registro.existePlaca(placa)) {
            cout << "Ya existe un vehiculo con esa placa." << endl;
            return;
        }

        string marca = leerTexto("Marca: ");
        string modelo = leerTexto("Modelo: ");

        cout << "Anio: ";
        int anio = leerEntero();

        cout << "Precio: ";
        double precio = leerDouble();

        bool disponible = leerBooleano("Esta disponible?");

        cout << "Cilindrada: ";
        int cilindrada = leerEntero();

        bool tieneMaletero = leerBooleano("Tiene maletero?");

        // CONCEPTO: INSTANCIACIÓN Y POLIMORFISMO
        Vehiculo* nuevoVehiculo = new Motocicleta(
                placa, marca, modelo, anio, precio,
                disponible, cilindrada, tieneMaletero);

        if (registro.registrar(nuevoVehiculo)) {
            cout << "Motocicleta registrada correctamente." << endl;
        } else {
            cout << "No fue posible registrar la motocicleta." << endl;
            delete nuevoVehiculo; // Liberar memoria si falla el registro
        }

    } catch (const invalid_argument& error) {
        cout << "Error: " << error.what() << endl;
    }
}

// --- FUNCIÓN PRINCIPAL ---

int main() {
    // Configura la consola de Windows para trabajar en UTF-8.
    // Esto evita que las tildes salgan como caracteres raros
    // (ej: "veh├¡culos" en vez de "vehículos").
    SetConsoleOutputCP(CP_UTF8);
    SetConsoleCP(CP_UTF8);

    // CONCEPTO: INSTANCIACIÓN DE OBJETOS
    RegistroVehiculos registro;
    int opcion;

    do {
        cout << "\n=== REGISTRO DE VEHICULOS ===" << endl;
        cout << "1. Registrar automovil" << endl;
        cout << "2. Registrar motocicleta" << endl;
        cout << "3. Mostrar vehiculos" << endl;
        cout << "4. Salir" << endl;
        cout << "Seleccione una opcion: ";

        opcion = leerEntero();

        switch (opcion) {
            case 1:
                registrarAutomovil(registro);
                break;
            case 2:
                registrarMotocicleta(registro);
                break;
            case 3:
                registro.mostrarTodos();
                break;
            case 4:
                cout << "Programa finalizado." << endl;
                break;
            default:
                cout << "Opcion incorrecta." << endl;
        }

    } while (opcion != 4);

    return 0;
}