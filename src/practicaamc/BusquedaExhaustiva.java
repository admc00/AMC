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
public class BusquedaExhaustiva {

    ArrayList<Punto> puntos;
    Fichero f;
    private double tiempo;
    private int puntosRecorridos;
    private DistanciaPuntos menorDistancia = new DistanciaPuntos();

    private double distanciaMin = 10000000;

    public DistanciaPuntos BusquedaExhaustiva(ArrayList<Punto> p) {



        tiempo = 0;

        this.puntos = p;

        long startTime = System.nanoTime();
        for (int i = 0; i < this.puntos.size(); i++) {
            for (int j = i + 1; j < this.puntos.size(); j++) {
                DistanciaPuntos d = new DistanciaPuntos(puntos.get(i), puntos.get(j));
                puntosRecorridos++;
                if (d.getDistanciaMin() < distanciaMin) {
                    distanciaMin = d.getDistanciaMin();
                    menorDistancia = d;
                }
            }
        }

        long endTime = System.nanoTime();
        tiempo = endTime - startTime;
        tiempo /= 1000;

        return menorDistancia;

    }
    
    public double getTiempo(){
        return this.tiempo;
    }

    public int getPuntosRecorridos(){
        return this.puntosRecorridos;
    }

}
