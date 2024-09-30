/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaamc;

/**
 *
 * @author antonio diego
 */
public class DistanciaPuntos {

    private double distancia;
    private Punto x1, x2;

    public DistanciaPuntos(Punto x1, Punto x2) {
        this.x1 = x1;
        this.x2 = x2;
        this.distancia();
    }

    public DistanciaPuntos() {
        this.x1 = new Punto();
        this.x2 = new Punto();
    }

    public int getDistanciaMin() {
        return (int)this.distancia;
    }

    private void distancia() {
        double distanciaX = x1.getX() - x2.getX();
        double distanciaY = x1.getY() - x2.getY();
        distancia = Math.sqrt(Math.pow(distanciaX, 2) + Math.pow(distanciaY, 2));
    }

}
