/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaamc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author usuario
 */
public class Generador {

    public ArrayList<Punto> RellenarPuntos(int n, Boolean mismax) {
        ArrayList<Punto> p = new ArrayList<>();

        Random rand = new Random();
        rand.setSeed(System.nanoTime());

        int num, den;
        double x, y, aux1;

        if (mismax) { //PEOR CASO
            for (int i = 0; i < n; i++) {
                aux1 = rand.nextInt(1000) + 7; //7 y 1007
                y = aux1 / ((double) i + 1 + i * 0.100); //aux2; //+(i/3.0);
                num = rand.nextInt(3);
                y += ((i % 500) - num * (rand.nextInt(100)));
                x = 1;
                Punto pn = new Punto(x, y, i + 1);
                p.add(pn);
            }
        } else { //CASO MEDIO
            for (int i = 0; i < n; i++) {
                num = rand.nextInt(4000) + 1; //genera un número aleatorio entre 1 y 4000
                den = rand.nextInt(11) + 7; //genera un aleatorio entre 7 y 17
                x = num / ((double) den + 0.37); //division con decimales
                y = (rand.nextInt(4000) + 1) / ((double) (rand.nextInt(11) + 7) + 0.37);
                Punto pn = new Punto(x, y, i + 1);
                p.add(pn);
            }

        }
        return p;
    }

    public void CrearArchivoTSP(int size) {

        File file = new File("");
        for (int i = 0; i < 2; i++) {
            file = file.getParentFile();
        }
        file = new File("");
        String filePath = file.toString();
        Random r = new Random();
        r.setSeed(System.nanoTime());
        DecimalFormat decimalFormat = new DecimalFormat("#.##########");
        try {
            ArrayList<Punto> puntos;

            puntos = RellenarPuntos(size, true);

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write("NAME:dataset" + size + ".tsp");
            writer.newLine();
            writer.write("TYPE: TSP");
            writer.newLine();
            writer.write("COMMENT: " + size + " locations");
            writer.newLine();
            writer.write("DIMENSION: " + size);
            writer.newLine();
            writer.write("EDGE_WEIGHT_TYPE: EUC_2D");
            writer.newLine();
            writer.write("NODE_COORD_SECTION");
            writer.newLine();
            for (int i = 0; i < size; i++) {
                writer.write(i + 1 + " " + puntos.get(i).getX() + " " + puntos.get(i).getY());
                writer.newLine();
            }
            //Forzar escritura en el archivo
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //this.fichero.setFichero(file);
    }
}
