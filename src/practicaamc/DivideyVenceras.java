/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaamc;

import java.util.ArrayList;

/**
 *
 * @author Antonio Diego
 */
public class DivideyVenceras {

    ArrayList<Punto> puntos;
    Fichero f;
    private double tiempo;
    private int puntosRecorridos;

    public DistanciaPuntos DivideyVenceras(ArrayList<Punto> p, int izquierda, int derecha) {
        DistanciaPuntos menorDistancia = null;

        this.puntos = p;

        tiempo = 0;

        long startTime = System.nanoTime();

        if (derecha - izquierda <= 3) {
            double minDistancia = Double.MAX_VALUE;
            // Caso base: pocos puntos, calcular por fuerza bruta
            for (int i = izquierda; i <= derecha; i++) {
                for (int j = i + 1; j <= derecha; j++) {
                    DistanciaPuntos actualLinea = new DistanciaPuntos(puntos.get(i), puntos.get(j));
                    double distancia = actualLinea.getDistanciaMin();
                    puntosRecorridos++;
                    if (distancia < minDistancia) {
                        minDistancia = distancia;
                        menorDistancia = actualLinea;
                    }
                }
            }
            return menorDistancia;
        }

        int medio = (izquierda + derecha) / 2;
        Punto puntoMedio = puntos.get(medio);

        DistanciaPuntos lineaIzquierda = DivideyVenceras(puntos, izquierda, medio);
        DistanciaPuntos lineaDerecha = DivideyVenceras(puntos, medio, derecha);
        double distanciaMin;

        if (lineaIzquierda.getDistanciaMin() <= lineaDerecha.getDistanciaMin()) {
            distanciaMin = lineaIzquierda.getDistanciaMin();
            menorDistancia = lineaIzquierda;
        } else {
            distanciaMin = lineaDerecha.getDistanciaMin();
            menorDistancia = lineaDerecha;
        }

        // Crear una lista de puntos en la banda de distanciaMin
        ArrayList<Punto> puntosEnRango = new ArrayList<>();
        for (int i = izquierda; i <= derecha; i++) {
            if (Math.abs(puntos.get(i).getX() - puntoMedio.getX()) < distanciaMin) {
                puntosEnRango.add(puntos.get(i));
            }
        }

        // Búsqueda en la banda de distanciaMin
        for (int i = 0; i < puntosEnRango.size() - 1; i++) {
            for (int j = i + 1; j < puntosEnRango.size(); j++) {
                DistanciaPuntos l = new DistanciaPuntos(puntosEnRango.get(i), puntosEnRango.get(j));
                puntosRecorridos++;
                if (l.getDistanciaMin() < distanciaMin) {
                    distanciaMin = l.getDistanciaMin();
                    menorDistancia = l;
                }
            }
        }

        long endTime = System.nanoTime();
        tiempo = endTime - startTime;
        tiempo /= 1000;

        return menorDistancia;
    }

    public ArrayList<Punto> quickSort(ArrayList<Punto> puntos, int low, int high) {

        if (low < high) {
            int pi = partition(puntos, low, high);

            quickSort(puntos, low, pi - 1);
            quickSort(puntos, pi + 1, high);
        }

        return puntos;
    }

    public int partition(ArrayList<Punto> puntos, int low, int high) {
        Punto pivot = puntos.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(puntos.get(j), pivot) <= 0) {
                i++;
                swap(puntos, i, j);
            }
        }

        swap(puntos, i + 1, high);
        return i + 1;
    }

    public double compare(Punto p1, Punto p2) {
        // Comparar los puntos según algún criterio, por ejemplo, comparar las coordenadas x o y.
        // Devolver un número negativo si p1 es menor que p2,
        // devolver 0 si p1 es igual a p2,
        // devolver un número positivo si p1 es mayor que p2.

        // Ejemplo de comparación por coordenada x:
        return Double.compare(p1.getX(), p2.getX());
    }

    public void swap(ArrayList<Punto> puntos, int i, int j) {
        Punto temp = puntos.get(i);
        puntos.set(i, puntos.get(j));
        puntos.set(j, temp);
    }

    public double getTiempo() {
        return this.tiempo;
    }
}
