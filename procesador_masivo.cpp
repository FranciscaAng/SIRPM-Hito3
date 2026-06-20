#include <iostream>
#include <fstream>
#include <string>

using namespace std;

int main(int argc, char* argv[]) {
    if (argc < 2) return 1;

    ifstream archivo(argv[1]);
    if (!archivo.is_open()) return 1;

    string linea;
    linea.reserve(256); 
    int hazmat = 0, reefer = 0, dry = 0, errores = 0, total = 0;
    bool primeraLinea = true;

    while (getline(archivo, linea)) {
        if (primeraLinea) {
            primeraLinea = false;
            continue;
        }

        if (linea.length() < 10) {
            errores++;
            continue;
        }

        total++;
        int comas = 0;
        size_t inicioCarga = 0;

        for (size_t i = 0; i < linea.length(); i++) {
            if (linea[i] == ',') {
                comas++;
                if (comas == 5) {
                    inicioCarga = i + 1;
                    break;
                }
            }
        }
        if (comas == 5) {
            if (linea.compare(inicioCarga, 6, "Hazmat") == 0) {
                hazmat++;
            } else if (linea.compare(inicioCarga, 6, "Reefer") == 0) {
                reefer++;
            } else if (linea.compare(inicioCarga, 3, "Dry") == 0) {
                dry++;
            } else {
                errores++;
            }
        } else {
            errores++;
        }
    }
    archivo.close();

    ofstream resumen("resumen_sirpm.csv");
    resumen << "Hazmat," << hazmat << '\n';
    resumen << "Reefer," << reefer << '\n';
    resumen << "Dry," << dry << '\n';
    resumen << "Errores," << errores << '\n';
    resumen << "Total," << total << '\n';
    resumen.close();
    return 0;
}