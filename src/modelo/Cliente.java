package modelo;
//Para usar LocalDate debemos importarla de java.time
import java.time.LocalDate;

public class Cliente {
    private int numero;
    private String nombre;
    private int edad;
    private float puntos; //Cambiamos de double a float
    private LocalDate fechaNac;
    
    //Constructor sin incluir los puntos, serán 0
    public Cliente(int numero, String nombre, int edad, LocalDate fechaNac){
        this.numero = numero;
        this.nombre = nombre;
        this.edad = edad;
        this.fechaNac = fechaNac;        
        this.puntos = 0;
    }
    //Constructor incluyendo todos los atributos
    public Cliente(int numero, String nombre, int edad,LocalDate fechaNac, float puntos){
        this(numero,nombre,edad,fechaNac);
        this.puntos = puntos;
    }
    
    public int getNumero() {return numero;}
    public void setNumero(int numero) {this.numero = numero;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public int getEdad() {return edad;}
    public void setEdad(int edad) {this.edad = edad;}
    public float getPuntos() {return puntos;}
    public void setPuntos(float puntos) {this.puntos = puntos;}
    public LocalDate getFecha(){return fechaNac;}
    public void setFecha(LocalDate fechaNac){this.fechaNac = fechaNac;}
    
    
    @Override
    public String toString(){        
        return "["+this.numero+"] "+this.nombre+" - "+this.edad+" - {"+this.fechaNac+"} - PUNTOS: "+this.puntos;
    }    
}
