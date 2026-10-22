package vista;

import controlador.ControladorClientes;
import modelo.Cliente;
        
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class PanelVisualizar extends JPanel {//HEREDA DE JPANEL
    //Text Field para incluir los datos
    private JTextField numField,nomField,edadField,fecField,punField;
    
    //Etiquetas JLabel para los campos de texto
    private JLabel numLabel,nomLabel,edadLabel,fecLabel,punLabel;
    
    //Los botones
    private JButton anterior,siguiente,ultimo,primero,borrar;
    
    //Constructor
    public PanelVisualizar(){
        //Creamos las etiquetas
        numLabel = new JLabel("Número:");
        nomLabel = new JLabel("Nombre:");
        edadLabel = new JLabel("Edad:");
        fecLabel = new JLabel("Fecha de Nacimiento:");        
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
        siguiente = new JButton ("Siguiente >");
        anterior = new JButton ("< Anterior");
        ultimo = new JButton ("Último");
        primero = new JButton ("Primero");        
        borrar = new JButton ("BORRAR");        
        
        /*CREAMOS LOS PANELES
        
        EN ESTE CASO HEREDAMOS DE JPANEL (this) y le damos las propiedades que 
        le dariamos a un panel contenedor 
        */
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));   
        
        //Creamos el panel de los Label
        JPanel panelLabel = new JPanel();
        panelLabel.setLayout(new GridLayout(0,1));//Una columna
        panelLabel.add(numLabel);
        panelLabel.add(nomLabel);
        panelLabel.add(edadLabel);
        panelLabel.add(fecLabel);
        panelLabel.add(punLabel);
        
        //Creamos los field
        JPanel panelField = new JPanel();
        panelField.setLayout(new GridLayout(0,1));
        panelField.add(numField);
        panelField.add(nomField);
        panelField.add(edadField);
        panelField.add(fecField);
        panelField.add(punField);
        
        //Creamos el panel navegación y añadimos los componentes
        JPanel panelNav = new JPanel();
        panelNav.setLayout(new GridLayout(1,0));//Solo una fila
        panelNav.add(ultimo);
        panelNav.add(anterior);
        panelNav.add(siguiente);
        panelNav.add(primero);
        
        //Creamos un panel para el botón a la derecha
        JPanel panelBorrar = new JPanel();
        panelField.setLayout(new GridLayout(0,1)); 
        panelBorrar.add(borrar);
        //Los colocamos en el contenedor que se hereda de extends JPanel (this)
        //(es lo primero que hemos definido en el constructor)
        //Creamos un JPanel para un título arriba al contenedor(this)
        add(new JLabel("CLIENTES", SwingConstants.CENTER), BorderLayout.NORTH);
        add(panelLabel, BorderLayout.WEST);
        add(panelField, BorderLayout.CENTER);
        add(panelBorrar, BorderLayout.EAST);
        add(panelNav, BorderLayout.SOUTH);
        
        //EVENTOS BOTONES
        siguiente.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ControladorClientes.controladorClientes.avanzar();
                actualizarVista();
            }
        });
        anterior.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ControladorClientes.controladorClientes.retroceder();
                actualizarVista();
            }
        });
        ultimo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ControladorClientes.controladorClientes.ultimoCliente();
                actualizarVista();
            }
        });
        primero.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ControladorClientes.controladorClientes.primerCliente();
                actualizarVista();
            }
        });
        borrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                ControladorClientes.controladorClientes.borrarCliente();
                actualizarVista();
            }
        });       
    /*  
        COMO SERÍA CON LAMBDA
        
        siguiente.addActionListener((ActionEvent e) -> {
            ControladorClientes.controladorClientes.avanzar();
            actualizarVista();
        });
        anterior.addActionListener((ActionEvent e) -> {
            ControladorClientes.controladorClientes.retroceder();
            actualizarVista();
        });
        ultimo.addActionListener((ActionEvent e) -> {
            ControladorClientes.controladorClientes.ultimoCliente();
            actualizarVista();
        });
        primero.addActionListener((ActionEvent e) -> {
            ControladorClientes.controladorClientes.primerCliente();
            actualizarVista();
        });
        borrar.addActionListener((ActionEvent e) -> {
            ControladorClientes.controladorClientes.borrarCliente();
            actualizarVista();
        });     
    */    
    }
    
    public void actualizarVista(){
        //Cogemos de la instancia static el cliente actual
        Cliente c = ControladorClientes.controladorClientes.clienteActual();
        //Este método devuelve null si no hay
        if(c != null){
            numField.setText(String.valueOf(c.getNumero()));
            nomField.setText(c.getNombre());
            edadField.setText(String.valueOf(c.getEdad()));
            //Para devolver el formato de fecha de aquí
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            fecField.setText(c.getFecha().format(formato));//Incluimos el formato
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
