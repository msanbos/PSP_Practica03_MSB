package vista;

import controlador.ControladorClientes;

import javax.swing.*;
import java.awt.*;

public class PanelNuevo extends JPanel{// HEREDAN DE JPANEL
    //Text Field para incluir los datos
    private JTextField numField,nomField,edadField,fecField,punField;    
    //Etiquetas JLabel para los campos de texto
    private JLabel numLabel,nomLabel,edadLabel,fecLabel,punLabel;    
    //Los botones
    private JButton guardarBtn,limpiarBtn;
    
    public PanelNuevo(){
        //Inicializamos lod Label
        numLabel = new JLabel("Número:");
        nomLabel = new JLabel("Nombre:");
        edadLabel = new JLabel("Edad:");
        fecLabel = new JLabel("Fecha de Nacimiento:");        
        punLabel = new JLabel("PUNTOS:");     
        
        //Creamos los campos de texto, esta vez si son modificables
        numField = new JTextField(10);
        nomField = new JTextField(10);
        edadField = new JTextField(10);
        fecField = new JTextField(10);
        punField = new JTextField(10);
        
        //Iniciamos los botones
        guardarBtn = new JButton ("GUARDAR CLIENTE");
        limpiarBtn = new JButton ("Limpiar campos");
        
        //LOS COMPONENTES:
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
        panelNav.add(guardarBtn);
        panelNav.add(limpiarBtn);
        
        add(new JLabel("ALTA CLIENTES", SwingConstants.CENTER), BorderLayout.NORTH);
        add(panelLabel, BorderLayout.WEST);
        add(panelField, BorderLayout.CENTER);
        add(panelNav, BorderLayout.SOUTH);     
        
        
               
    }
    
    /**
     * FUNCIÓN QUE LIMPIA LOS TextField
     */
    public void limpiarCampos(){
        numField.setText("");
        nomField.setText("");
        edadField.setText("");
        fecField.setText("");
        punField.setText("");
        numField.requestFocus();//Ponemos el foco en el primer término
    }
}
