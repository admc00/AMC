/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaamc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Antonio Diego
 */
public class DivideyVencerasMejorado {

    ArrayList<Punto> puntos;
    Fichero f;
    private double tiempo;
    private int puntosRecorridos;

    public DistanciaPuntos DivideyVencerasMejorado(ArrayList<Punto> p, int izquierda, int derecha) {
        DistanciaPuntos menorDistancia = null;

        this.puntos = p;

        tiempo = 0;

        long startTime = System.nanoTime();

        if (derecha - izquierda <= 3) {
            double Mindistancia = Double.POSITIVE_INFINITY;
            // Caso base: Fuerza bruta para un número pequeño de puntos
            for (int i = izquierda; i < derecha - 1; i++) {
                for (int j = i + 1; j < derecha; j++) {
                    DistanciaPuntos actualLinea = new DistanciaPuntos(puntos.get(i), puntos.get(j));
                    double distancia = actualLinea.getDistanciaMin();
                    puntosRecorridos++;
                    if (distancia < Mindistancia) {
                        Mindistancia = distancia;
                        menorDistancia = actualLinea;
                    }
                }
            }

            return menorDistancia;
        }

        // Divide los puntos en dos mitades
        int medio = (izquierda + derecha) / 2;
        Punto puntoMedio = puntos.get(medio);

        DistanciaPuntos lineaIzquierda = DivideyVencerasMejorado(p, izquierda, medio);
        DistanciaPuntos lineaDerecha = DivideyVencerasMejorado(p, medio, derecha);

        double distanciaMin;
        // Elegir la línea más corta de las dos
        if (lineaIzquierda.getDistanciaMin() <= lineaDerecha.getDistanciaMin()) {
            distanciaMin = lineaIzquierda.getDistanciaMin();
            menorDistancia = lineaIzquierda;
        } else {
            distanciaMin = lineaDerecha.getDistanciaMin();
            menorDistancia = lineaDerecha;
        }

        // Filtrar los puntos en la franja por coordenada x
        List<Punto> puntosEnRango = new ArrayList<>();
        for (int i = izquierda; i < derecha; i++) {
            if (Math.abs(puntos.get(i).getX() - puntoMedio.getX()) < distanciaMin) {
                puntosEnRango.add(puntos.get(i));
            }
        }

        // Ordenar los puntos en la franja por coordenada y
        Collections.sort(puntosEnRango, (p1, p2) -> Double.compare(p1.getY(), p2.getY()));

        // Búsqueda de pares cercanos en la franja
        for (int i = 0; i < puntosEnRango.size() - 1; i++) {
            for (int j = i + 1; j < puntosEnRango.size() && j - i < 12; j++) {
                DistanciaPuntos linea = new DistanciaPuntos(puntosEnRango.get(i), puntosEnRango.get(j));
                double distancia = linea.getDistanciaMin();
                puntosRecorridos++;
                if (distancia < distanciaMin) {
                    distanciaMin = distancia;
                    menorDistancia = linea;
                }
            }
        }

        long endTime = System.nanoTime();
        tiempo = endTime - startTime;
        tiempo /= 1000;

        return menorDistancia;
    }

    public ArrayList<Punto> quickSort(ArrayList<Punto> p, int low, int high) {
        this.puntos = p;

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
