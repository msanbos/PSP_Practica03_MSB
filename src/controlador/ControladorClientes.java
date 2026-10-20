package controlador;

import modelo.Cliente;//Importamos el modelo
import java.time.LocalDate;//Importamos LocalDate
import  java.util.ArrayList;//Las colecciones que vamos a usar

public class ControladorClientes {
    private ArrayList<Cliente> lista;//Declaramos nuestra lista
    private int actual = 0 ; //Inicializamos en el primero
    
    //Inicializamos la lista en el constructor
    public ControladorClientes(){
        this.lista = new ArrayList<>();
    }
    /**
     * Método que comprueba si la lista está vacía
     * @return 
     */
    public boolean esVacia(){
        return lista.isEmpty(); //Este método de colecciones es boolean
    }
    
    public void avanzar(){
        if(!lista.isEmpty() && actual<lista.size()-1){
            actual++;
        }
    }
    
    public void retroceder(){
        if(!lista.isEmpty() && actual>0){
            actual--;
        }
    }
    
    public void primerCliente(){
        if(!lista.isEmpty()){
            actual = 0;
        }
    }
    
    public void ultimoCliente(){
        if(!lista.isEmpty()){
            actual = lista.size()-1;
        }
    }
    
    public boolean esPrimero(){
        return !lista.isEmpty() && actual == 0;
    }
    
    public boolean esUltimo(){
        return !lista.isEmpty() && actual == lista.size() - 1;
    }
    
    /**
     * Función que recoge los String de la vista y los válida para añadir Cliente
     * Si se da algún error devuelve el String de error concreto
     * @param numero
     * @param nombre
     * @param edad
     * @param puntos
     * @param fechaNac
     * @return 
     */
    public String añadirCliente(String numero,String nombre, String edad, String puntos, String fechaNac){
        //Parseamos los Stringd de la Vista a sus valores reales
        int num = Integer.parseInt(numero);
        int ed = Integer.parseInt(edad);
        float punt = Float.parseFloat(puntos);
        LocalDate fecha;
        
        //Validamos los puntos (0 a 1000)
        if(punt < 0 || punt > 1000){
            return "Error: Los puntos deben estar entre 0 y 1000";
        }
        
        //Validamos la fecha, tenemos en cuenta que puede fallar el parse
        try{
            fecha = LocalDate.parse(fechaNac);
        }catch(Exception ex){
            return "Error: El formato de fecha no es correcto (YYYY-MM-DD)";
        }
        //Obtenemos el año de la fecha con el método .getYear() de LocalDate
        int anyo = fecha.getYear();
        if (anyo < 1900 || anyo > 2015){
            return "Error: La fecha debe estar comprendida entre los años 1900 y 2015";
        }
        
        //Una vez validado todo -> Añadimos cliente
        Cliente nuevoCliente = new Cliente(num,nombre,ed,fecha,punt);
        //Lo añadimos a la colección
        lista.add(nuevoCliente);
        //Actualizamos el índice del controlador
        actual = lista.size() - 1;
        return ""; //Devolvemos la cadena vacía para indicar que no hay errores
        
    }
    
    
    
    
    
    
}
