package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private static final String CARD_A = "VISUALIZAR";
    private static final String CARD_B = "NUEVO";
    
    //Usado para mostrar un panel u otro
    private final CardLayout cardLayout = new CardLayout ();
    private final JPanel contenedor = new JPanel(cardLayout);
    
    //Creamos los paneles 
    private final PanelVisualizar panelVisual = new PanelVisualizar();
    private final PanelNuevo panelNuevo = new PanelNuevo();
    
    /**
     * En el Constructor; incluimos el nombre de la ventana, añadimos los
     * paneles en el contenedor principal y añadimos el menú
     */    
    public VentanaPrincipal(){
        super("Gestor Clientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        contenedor.add(panelVisual,CARD_A);
        contenedor.add(panelNuevo,CARD_B);
        add(contenedor);
        
        setJMenuBar(crearMenu());
        
        setSize(500,350);
        setLocationRelativeTo(null); // Para centrar la ventana
    }
    
    private JMenuBar CrearMenu(){
        //Creamos los items del menú
        JMenuItem itemA = new JMenuItem("Visualizar Cliente");
        JMenuItem itemB = new JMenuItem("Nuevo Cliente");
        JMenuItem itemSalir = new JMenuItem("Salir");
        //Luego los listener al pulsarlos
        itemA.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(contenedor, CARD_A);
            }
        });        
        itemB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                /*
                PASO DE DATOS DE UN PANEL A OTRO del ejemplo
                panelNuevo.setNumero(panelVisual.getNumero());
                */
                cardLayout.show(contenedor, CARD_B);
            }
        });
        itemSalir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        
        /*Los mismos Listener con LAMBDA
        itemA.addActionListener( e -> cardLayout.show(contenedor,CARD_A);
        itemB.addActionListener( e -> cardLayout.show(contenedor,CARD_B);
        itemSalir.addActionListener( e -> System.exit(0));        
        */
        
        //Añadimos los elementos del menú
        JMenu menu = new JMenu("Menú");
        menu.add(itemA);
        menu.add(itemB);
        menu.addSeparator();
        menu.add(itemSalir);
        
        //Lo incluimos en una barra típica de menú
        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        return bar;
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                final VentanaPrincipal venpcal = new VentanaPrincipal();
                venpcal.pack();
                venpcal.setVisible(true);
            }
        });
    }
    /* CON LAMBDA según el ejemplo:
    public static void main(String[] args){
        SwingUtilities.InvokeLater(() -> new VentanaPrincipal().setVisible(true))
    }    
    */
}
