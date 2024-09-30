/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practicaamc;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author antonio diego
 */
public class PracticaAMC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Generador g = new Generador();
        BusquedaExhaustiva b = new BusquedaExhaustiva();
        BusquedaExhaustivaPoda bp = new BusquedaExhaustivaPoda();
        DivideyVenceras d = new DivideyVenceras();
        DivideyVencerasMejorado dm = new DivideyVencerasMejorado();
        Boolean peor_caso = false;


        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (peor_caso) {
                System.out.println("Peor caso:Activado");
            } else {
                System.out.println("Peor caso:Desactivado");
            }

            System.out.println("Menú:");
            System.out.println("1.Crear fichero .tsp aleatorio");
            System.out.println("2.Comprobar todos los dataset");
            System.out.println("3.Comprobar 1 estrategia");
            System.out.println("4.Comparar todas las estrategias");
            System.out.println("5.Comparar 2 estrategias");
            System.out.println("6.Comprobar todas las estrategias");
            System.out.println("7.Activar/Desactivar peor caso");
            System.out.println("8.Comprobar estrategias de un fichero tsp");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");


            int opcion = scanner.nextInt();
            switch (opcion) {

                case 0: {
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                }
                break;
                case 1: {
                    System.out.println("¿Cuantos puntos desea generar: ?");
                    int n = scanner.nextInt();
                    g.CrearArchivoTSP(n);
                }

                break;

                case 2: {

                }

                break;

                case 3: {

                    System.out.println("Elige el algoritmo. ");
                    System.out.println("1.Exhaustivo. ");
                    System.out.println("2.Exhaustivo con poda.");
                    System.out.println("3.Divide y venceras.");
                    System.out.println("4.Divide y venceras mejorada.");
                    System.out.println("5.Volver al menu.");

                    int n = scanner.nextInt();

                    switch (n) {

                        case 1: {
                            System.out.println("Talla                Tiempo(ms)");
                            for (int i = 500; i < 5000; i += 500) {
                                ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                b.BusquedaExhaustiva(p);
                                System.out.println("      " + i + "                 " + b.getTiempo());
                            }
                            break;
                        }

                        case 2: {
                            System.out.println("Talla                Tiempo(ms)");
                            for (int i = 500; i < 5000; i += 500) {
                                ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                bp.BusquedaExhaustivaPoda(p);
                                System.out.println("      " + i + "                 " + bp.getTiempo());
                            }
                            break;

                        }
                        case 3: {
                            System.out.println("Talla                Tiempo(ms)");
                            for (int i = 500; i < 5000; i += 500) {
                                ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                d.DivideyVenceras(p, 0, p.size() - 1);
                                System.out.println("      " + i + "                 " + d.getTiempo());
                            }
                            break;

                        }
                        case 4: {
                            System.out.println("Talla                Tiempo(ms)");

                            for (int i = 500; i < 5500; i += 500) {
                                ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                dm.DivideyVencerasMejorado(p, 0, p.size() - 1);
                                System.out.println("      " + i + "               " + dm.getTiempo());
                            }

                        }
                        default: {
                            System.out.println("Volviendo al menu.");
                            break;
                        }

                    }

                }

                break;

                case 4: {
                    System.out.println("         Exhaustiva   ExhaustivaP    DivyVen       DyVMejorado");
                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                    for (int i = 500; i < 5500; i += 500) {
                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);
                        b.BusquedaExhaustiva(p);
                        bp.BusquedaExhaustivaPoda(p);
                        d.DivideyVenceras(p, 0, p.size() - 1);
                        dm.DivideyVencerasMejorado(p, 0, p.size() - 1);
                        System.out.println(" " + i + "      " + b.getTiempo() + "        " + bp.getTiempo() + "         " + d.getTiempo() + "         " + dm.getTiempo());
                    }
                }

                break;
                case 5: {
                    System.out.println("Elige los algoritmos: ");
                    System.out.println("1.Exhaustivo. ");
                    System.out.println("2.Exhaustivo con poda.");
                    System.out.println("3.Divide y venceras.");
                    System.out.println("4.Divide y venceras mejorada.");

                    System.out.println("Elige el primer algoritmo:");
                    int n1 = scanner.nextInt();

                    System.out.println("Elige el segundo algoritmo:");
                    int n2 = scanner.nextInt();


                    switch (n1) {
                        case 1:
                            switch (n2) {

                                case 2:
                                    System.out.println("         Exhaustiva            ExhaustivaP         Exhaustiva         ExhaustivaP");
                                    System.out.println("Talla     Tiempo(ms)            Tiempo(ms)          Distancias         Distancias");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int dh = b.BusquedaExhaustiva(p).getDistanciaMin();

                                        int dhp = bp.BusquedaExhaustivaPoda(p).getDistanciaMin();

                                        System.out.println(" " + i + "      " + b.getTiempo() + "               " + bp.getTiempo() + "               " + dh + "                  " + dhp);
                                    }
                                    break;
                                case 3:
                                    System.out.println("         Exhaustiva     DyV           Exhaustiva     DyV");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int dh = b.BusquedaExhaustiva(p).getDistanciaMin();

                                        int ddv = d.DivideyVenceras(p, 0, p.size() - 1).getDistanciaMin();

                                        System.out.println(" " + i + "      " + b.getTiempo() + "               " + d.getTiempo() + "               " + dh + "                  " + ddv);
                                    }
                                    break;
                                case 4:
                                    System.out.println("         Exhaustiva     DyVMejorado    Exhaustiva     DyVMejorado");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int dh = b.BusquedaExhaustiva(p).getDistanciaMin();

                                        int ddvm = dm.DivideyVencerasMejorado(p, 0, p.size() - 1).getDistanciaMin();

                                        System.out.println(" " + i + "      " + b.getTiempo() + "               " + dm.getTiempo() + "               " + dh + "                  " + ddvm);
                                    }
                                    break;
                            }
                            break;
                        case 2:
                            switch (n2) {

                                case 1:
                                    System.out.println("         ExhaustivaP   Exhaustiva    ExhaustivaP   Exhaustiva");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int dhp = bp.BusquedaExhaustivaPoda(p).getDistanciaMin();

                                        int dh = b.BusquedaExhaustiva(p).getDistanciaMin();

                                        System.out.println(" " + i + "      " + bp.getTiempo() + "               " + b.getTiempo() + "               " + dhp + "                  " + dh);
                                    }
                                    break;
                                case 3:
                                    System.out.println("         ExhaustivaP     DyV           ExhaustivaP     DyV");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int dhp = bp.BusquedaExhaustivaPoda(p).getDistanciaMin();

                                        int ddv = d.DivideyVenceras(p, 0, p.size() - 1).getDistanciaMin();

                                        System.out.println(" " + i + "      " + bp.getTiempo() + "               " + d.getTiempo() + "               " + dhp + "                  " + ddv);
                                    }
                                    break;
                                case 4:
                                    System.out.println("         ExhaustivaP     DyVMejorado    ExhaustivaP     DyVMejorado");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int dhp = bp.BusquedaExhaustivaPoda(p).getDistanciaMin();

                                        int ddvm = dm.DivideyVencerasMejorado(p, 0, p.size() - 1).getDistanciaMin();

                                        System.out.println(" " + i + "      " + bp.getTiempo() + "               " + dm.getTiempo() + "               " + dhp + "                  " + ddvm);
                                    }
                                    break;
                            }
                            break;
                        case 3:
                            switch (n2) {

                                case 1:
                                    System.out.println("          DyV           Exhaustiva     DyV            Exhaustiva");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int ddv = d.DivideyVenceras(p, 0, p.size() - 1).getDistanciaMin();

                                        int dh = b.BusquedaExhaustiva(p).getDistanciaMin();

                                        System.out.println(" " + i + "      " + d.getTiempo() + "               " + b.getTiempo() + "               " + ddv + "                  " + dh);
                                    }
                                    break;
                                case 2:
                                    System.out.println("          DyV           ExhaustivaP     DyV           ExhaustivaP");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int ddv = d.DivideyVenceras(p, 0, p.size() - 1).getDistanciaMin();

                                        int dhp = bp.BusquedaExhaustivaPoda(p).getDistanciaMin();

                                        System.out.println(" " + i + "      " + d.getTiempo() + "               " + bp.getTiempo() + "               " + ddv + "                  " + dhp);
                                    }
                                    break;
                                case 4:
                                    System.out.println("          DyV           DyVMejorado     DyV           DyVMejorado");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int ddv = d.DivideyVenceras(p, 0, p.size() - 1).getDistanciaMin();

                                        int ddvm = dm.DivideyVencerasMejorado(p, 0, p.size() - 1).getDistanciaMin();

                                        System.out.println(" " + i + "      " + d.getTiempo() + "               " + dm.getTiempo() + "               " + ddv + "                  " + ddvm);
                                    }
                                    break;
                            }
                            break;
                        case 4:
                            switch (n2) {

                                case 1:
                                    System.out.println("          DyVMejorado   Exhaustiva      DyV           Exhaustiva");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);

                                        int ddvm = dm.DivideyVencerasMejorado(p, 0, p.size() - 1).getDistanciaMin();

                                        int dh = b.BusquedaExhaustiva(p).getDistanciaMin();

                                        System.out.println(" " + i + "      " + dm.getTiempo() + "               " + b.getTiempo() + "               " + ddvm + "                  " + dh);
                                    }
                                    break;
                                case 2:
                                    System.out.println("          DyVMejorado   ExhaustivaP     DyV           ExhaustivaP");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);
                                        int ddvm = dm.DivideyVencerasMejorado(p, 0, p.size() - 1).getDistanciaMin();

                                        int dhp = bp.BusquedaExhaustivaPoda(p).getDistanciaMin();

                                        System.out.println(" " + i + "      " + dm.getTiempo() + "               " + bp.getTiempo() + "               " + ddvm + "                  " + dhp);
                                    }
                                    break;
                                case 3:
                                    System.out.println("          DyVMejorado    DyV            DyVMejorado    DyV");
                                    System.out.println("Talla     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)     Tiempo(ms)");
                                    for (int i = 500; i < 5500; i += 500) {
                                        ArrayList<Punto> p = g.RellenarPuntos(i, peor_caso);
                                        int ddvm = dm.DivideyVencerasMejorado(p, 0, p.size() - 1).getDistanciaMin();

                                        int ddv = d.DivideyVenceras(p, 0, p.size() - 1).getDistanciaMin();

                                        System.out.println(" " + i + "      " + dm.getTiempo() + "               " + d.getTiempo() + "               " + ddvm + "                  " + ddv);
                                    }
                                    break;
                            }
                            break;

                    }

                }
                break;

                case 6:{
                    System.out.println("Introduce la talla del fichero: ");
                    int n = scanner.nextInt();
                    ArrayList<Punto> p = g.RellenarPuntos(n, peor_caso);
                    b.BusquedaExhaustiva(p);
                    bp.BusquedaExhaustivaPoda(p);
                    d.DivideyVenceras(p, 0, p.size() - 1);
                    dm.DivideyVencerasMejorado(p, 0, p.size() - 1);
                    System.out.println("Estrategia Punto1  Punto2 Distancia Calculadas Tiempo(ms)");
                    System.out.println("Exhaustiva" );
                    System.out.println("Exhaustiva con poda");
                    System.out.println("Divide y venceras");
                    System.out.println("Divide y venceras mejorado");

                }
                break;
                case 7: {
                    peor_caso = !peor_caso;
                    break;
                }
                case 8: {

                }
                break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }

    }

}
