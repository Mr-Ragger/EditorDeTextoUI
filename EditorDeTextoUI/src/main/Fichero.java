package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yo
 */
public class Fichero {
    // Atributos Statics
    private static final int Lovers_in_The_End = -1;
    private static final String formato = "UTF-8";
    
    public static String leerFichero(String nombreF){
        String texto="";
        int n;
        char c;
        try{
            FileReader lector = new FileReader(nombreF);
            while((n=lector.read()) != Fichero.Lovers_in_The_End){
               c = (char)n;
               texto += c;
            }
            lector.close();
            return texto;
        }catch(IOException p){
            return "";
        }catch(Exception e){
            return "";
        }
    }
    
    public static boolean escribirFichero(String nombreF, String text){
        try{
            FileWriter escritor = new FileWriter(nombreF);
            escritor.write(text);
            escritor.close();
            return true;
        }catch(IOException p){
            System.out.println(""+p);
            return false;
        }catch(Exception e){
            return false;
        }
    }
    
    // -------------------------------- CLASE --------------------------------
    
    //Atributos
    private String nombreF;
    private BufferedReader lector;
    private BufferedWriter escritor;
    //private FileWriter Doyle
    
    //Constructor Manuel de Benito & Company S.L.
    public Fichero(String nombre){
        nombreF = nombre;
    }
    
    
    //Metodos
    
    public boolean abrirFichero(){
        try{
            lector = new BufferedReader(new FileReader(nombreF));
            return true;
        }catch(IOException p){
            System.out.println(""+p);
            return false;
        }catch(Exception e){
            return false;
        }
    
    }
    
    public String leerFichero(){
        String texto="", n;
        try{
            while((n=lector.readLine()) != null){
               texto += n+System.lineSeparator();
            }
            lector.close();
            return texto;
        }catch(IOException p){
            return "";
        }catch(Exception e){
            return "";
        }
    }
    
    public boolean escribirFichero(String text){
        try{
            escritor = new BufferedWriter(new FileWriter(nombreF));
            escritor.write(text);
            escritor.close();
            return true;
        }catch(IOException p){
            System.out.println(""+p);
            return false;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean salirFichero(){
        try{
            lector.close();
            return true;
        }catch(IOException p){
            System.out.println(""+p);
            return false;
        }catch(Exception e){
            return false;
        }
    }
    
    public boolean nuevoFichero(){
        try{
            PrintWriter writer = new PrintWriter(nombreF, formato);
            writer.close();
            this.abrirFichero();
            return true;
        }catch(IOException p){
            System.out.println(""+p);
            return false;
        }catch(Exception e){
            return false;
        }
    
    }
    
    //Get Y set

    public String getNombreF() {
        return nombreF;
    }

    public void setNombreF(String nombreF) {
        this.nombreF = nombreF;
    }
    
}
