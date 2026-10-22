package vista;

import controlador.ControladorClientes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        
        //Creamos los eventos de los botones
        
        /**AQUI VAMOS A DEVOLVER LOS MENSAJES DE TEXTO SEGüN LO QUE DEVUELVA
         * EL MÉTODO añadir, que devuelve un String con el mensaje
         * con JOptionPane.showMessageDialog(el panel donde aparece, mensaje, 
         * titulo, tipo) podemos lanzar un mensaje con una ventana emergente
         * 
         * Podemos usar distintos tipos: ERROR_MESSAGE, WARNING MESSAGE, 
         * INFORMATION MESSAGE
         * 
         */
        guardarBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Controlamos si no introduce números el usuario en los campos
                //necesarios con try/catch
                try{
                    //Recogemos los campos
                    String num = numField.getText();
                    String nom = nomField.getText();
                    String edad = edadField.getText();
                    String fec = fecField.getText();
                    String pun = punField.getText();
                    //Recogemos el mensaje que devuelve la función añadir
                    String mensaje = ControladorClientes.controladorClientes.añadirCliente(num, nom, edad, fec, pun);
                    
                    if (mensaje == null){
                        JOptionPane.showMessageDialog(PanelNuevo.this, "Cliente "
                                + "guardado con éxito","Cliente guardado", 
                                JOptionPane.INFORMATION_MESSAGE);
                        limpiarCampos(); //Una vez guardado, limpiamos los campos 
                    }else{
                        //Devolvemos el mensaje de error de validación
                        JOptionPane.showMessageDialog(PanelNuevo.this,mensaje,
                                "Datos no válidos",JOptionPane.WARNING_MESSAGE);
                        
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(PanelNuevo.this,"Por favor,"
                            + " revise los campos numéricos","Error de formato",
                            JOptionPane.ERROR_MESSAGE);
                }
            }       
        });
        limpiarBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                limpiarCampos();
            }
        });
        
               
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
