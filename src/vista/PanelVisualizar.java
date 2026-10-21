package vista;

import controlador.ControladorClientes;
import modelo.Cliente;
        
import javax.swing.*;
import java.awt.*;

public class PanelVisualizar extends JPanel {//HEREDA DE JPANEL
    //Text Field para incluir los datos
    private JTextField numField,nomField,edadField,fecField,punField;
    
    //Etiquetas JLabel para los campos de texto
    private JLabel numLabel,nomLabel,edadLabel,fecLabel,punLabel;
    
    //Los botones
    private JButton anterior,siguiente,ultimo,primero,borrar;
    
    //Constructor
    public PanelVisualizar(){
        //EN ESTE CASO HEREDAMOS DE JPANEL (this) y le damos las propiedades que le 
        //dariamos a un panel contenedor 
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));    
        
        //
        //Creamos las etiquetas
        numLabel = new JLabel("Número:");
        nomLabel = new JLabel("Nombre:");
        edadLabel = new JLabel("Edad:");
        punLabel = new JLabel("Fecha de Nacimiento:");        
        punLabel = new JLabel("PUNTOS:");       
        
        //Instanciamos los textField
        numField = new JTextField(10);
        numField.setEditable(false);//False.Solo muestran información
        nomField = new JTextField(10);
        nomField.setEditable(false);
        edadField = new JTextField(10);
        edadField.setEditable(false);
        fecField = new JTextField(10);
        fecField.setEditable(false);
        punField = new JTextField(10);
        punField.setEditable(false);
        //Pintamos el atributo de los Puntos de Azul
        punField.setForeground(Color.blue);    
        
        //Creamos los botones
        siguiente = new JButton ("Siguiente ->");
        anterior = new JButton ("<- Anterior");
        ultimo = new JButton ("Último");
        primero = new JButton ("Primero");        
        borrar = new JButton ("ELIMINAR CLIENTE");        
        
        
        
        
    }
    
    public void actualizarVista(){
        //Cogemos de la instancia static el cliente actual
        Cliente c = ControladorClientes.controladorClientes.clienteActual();
        //Este método devuelve null si no hay
        if(c != null){
            numField.setText(String.valueOf(c.getNumero()));
            nomField.setText(c.getNombre());
            edadField.setText(String.valueOf(c.getEdad()));
            fecField.setText(c.getFecha().toString());
            punField.setText(String.valueOf(c.getPuntos()));
        }else{
            numField.setText(""); nomField.setText("");
            edadField.setText(""); fecField.setText(""); punField.setText("");        
        }
        
        //Desactivamos los botones usando las funciones del controlador
        anterior.setEnabled(!ControladorClientes.controladorClientes.esVacia() && !ControladorClientes.controladorClientes.esPrimero());
        siguiente.setEnabled(!ControladorClientes.controladorClientes.esVacia() && !ControladorClientes.controladorClientes.esUltimo());
        primero.setEnabled(!ControladorClientes.controladorClientes.esVacia() && !ControladorClientes.controladorClientes.esPrimero());
        ultimo.setEnabled(!ControladorClientes.controladorClientes.esVacia() && !ControladorClientes.controladorClientes.esUltimo());
        borrar.setEnabled(!ControladorClientes.controladorClientes.esVacia());
    }
    
    //
}
