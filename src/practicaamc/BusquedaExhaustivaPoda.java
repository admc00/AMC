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
public class BusquedaExhaustivaPoda {

    ArrayList<Punto> puntos;
    Fichero f;
    private double tiempo;
    private int puntosRecorridos;

    public DistanciaPuntos BusquedaExhaustivaPoda(ArrayList<Punto> p) {

        double distanciaMin;
        DistanciaPuntos menorDistancia = new DistanciaPuntos();
        DistanciaPuntos distanciaActual;

        tiempo = 0;

        this.puntos = p;

        Punto punto1 = puntos.get(0);
        Punto punto2 = puntos.get(1);
        distanciaActual = new DistanciaPuntos(punto1, punto2);
        distanciaMin = distanciaActual.getDistanciaMin();
        
        long startTime = System.nanoTime();

        for (int i = 0; i < puntos.size() - 1; i++) {
            Punto puntoBase = puntos.get(i);
            for (int j = i + 1; j < puntos.size(); j++) {
                Punto puntoActual = puntos.get(j);
                distanciaActual = new DistanciaPuntos(puntoBase, puntoActual);

                //Comprobamos si la distancia minima tiene que ser actualizada
                if (distanciaActual.getDistanciaMin() < distanciaMin) {
                    distanciaMin = distanciaActual.getDistanciaMin();
                    menorDistancia = distanciaActual;

                }

                //Poda
                if (Math.abs(puntoBase.getX() - puntoActual.getX()) >= distanciaMin) {
                    break;
                }
                puntosRecorridos++;
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
    
    public double getTiempo(){
        return this.tiempo;
    }
}
