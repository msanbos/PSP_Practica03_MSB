package controlador;
//LO PODRIAMOS HACER STATIC, A LA HORA DE MANEJARLO ES MÁS CÓMODO
//Primero vamos a hacer la versión sin STATIC y luego si da tiempo STATIC
import modelo.Cliente;//Importamos el modelo
import java.time.LocalDate;
import  java.util.ArrayList;//Las colecciones que vamos a usar
import java.util.Collections;
import java.util.Comparator;//Para usar compare

public class ControladorClientes {
    private ArrayList<Cliente> lista;//Declaramos nuestra lista
    private int actual; //Inicializamos en el primero
    
    //Inicializamos la lista en el constructor
    public ControladorClientes(){
        this.lista = new ArrayList<>();
        this.actual = 0;
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
        lista.add(nuevoCliente);//Lo añadimos a la colección
        actual = lista.size() - 1;//Ponemos esta posicion en la lista
        return null; //Devolvemos la cadena null para indicar que no hay errores
        
    }
    
    public void borrarCliente (){
        if(!lista.isEmpty()){
            lista.remove(actual);
        }
        //Si el índice es mayor que el tamaño de la lista y que 0
        if(actual > lista.size() && actual > 0){
            actual--;//Bajamos el índice al anterior que se ha borrado
        }
    }
    
    public Cliente clienteActual(){
        if(!lista.isEmpty()){
            return null;
        }else{
            return lista.get(actual);
        }
        
    }
    
    /**Método que ordena por número la lista
     * Hemos elegido el método de añadir un comparator en una clase 
     * anónima al mismo método sort. Sobrescribimos el método compare 
     * intrínseco de Object
     * 
     */
    public void ordenarPorNumero (){
        Collections.sort(lista, new Comparator<Cliente>(){
            @Override
            public int compare(Cliente c1,Cliente c2){
                return Integer.compare(c1.getNumero(),c2.getNumero());
            }
        });
    }
    /**
     * CLASE PARA DEPURAR QUE FUNCIONA LA LISTA
     */
    public void mostrarClientes(){
        for(Cliente c : lista){
            System.out.print(c+" ");
        }
    }  
    
    
    
    

    
    /**
     * Añade cliente a la lista directamente
     * @param cliente 
     */
    /*LA VERSIÓN QUE PIDE TOMÁS CON SALIDA DE ERROR POR CONSOLA
    public void añadirCliente(Cliente cliente){
        lista.add(cliente);
        actual = lista.size()-1;//para que se ponga en esta posición
    }
    */
    
    
}
