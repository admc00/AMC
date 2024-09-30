/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practicaamc;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author antonio diego
 */
public class Fichero {

    File archivo;
    FileReader fr;
    BufferedReader br;
    ArrayList <Punto> p;
    
    public Fichero(){
        archivo = null;
        fr = null;
        br = null;
        p = new ArrayList<>();
    }

    public ArrayList<Punto> LeerFichero(String texto)
    {
        try {
            archivo = new File("C:\\Users\\antonio diego\\Documentos\\NetBeansProjects\\PracticaAMC\\dataset\\berlin52.tsp\\berlin52.tsp");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                
                if(linea.contains("EOF")){
                    break;
                }
                String[] parte = linea.split(" ");
                if(parte.length == 3){
                    for (int i = 0; i < parte.length; i++) {
                        //System.out.println(parte[i]);
                    }
                    p.add(new Punto(Float.parseFloat(parte[1]),Float.parseFloat(parte[2]),Integer.parseInt(parte[0])));
                }
                //System.out.println(linea);
            }
        } catch (Exception ex) {
            Logger.getLogger(Fichero.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return p;
    }

}
