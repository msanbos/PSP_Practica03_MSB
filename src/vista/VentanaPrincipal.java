package vista;

import javax.swing.*;
import java.awt.*;

//IMPORTAMOS LAS LIBRERIAS DE ACTIONEVENTyLISTENER CONCRETOS
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * VENTANA PRINCIPAL (JFRAME): CARDLAYOUT es un layout manager de Java
 * Swing que trata cada componente del contenedor como una "tarjeta" en una
 * pila. Solo una tarjeta es visible a la vez, y puedes cambiar entre ellas.
 * SE CONTROLA CON show() O next()/previous()
 * @author watat
 */
public class VentanaPrincipal extends JFrame {//HEREDA DE JFRAME
    
    //Creamos la instancia de CARDLAYOUT y lo incluimos en un JPanel contenedor
    private final CardLayout cardLayout = new CardLayout ();
    private final JPanel contenedor = new JPanel(cardLayout);
    
    //Creamos los paneles, una sola vez asi conservan su estado al cambiar 
    private final PanelVisualizar panelVisual = new PanelVisualizar();
    private final PanelNuevo panelNuevo = new PanelNuevo();
    //Como las clases Visual y Nuevo heredan de JPanel estas serán las cards
    
    
    /**
     * En el Constructor; incluimos el nombre de la ventana, añadimos los
     * paneles (cards) en el contenedor principal (cardLayout) y añadimos el menú
     */    
    public VentanaPrincipal(){
        super("Gestor Clientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // Para que no se pueda redimensionar
        
        //Incluimos las cards en el cardLayout, que son los JPanel instanciados antes.
        contenedor.add(panelVisual,"visual");
        contenedor.add(panelNuevo,"nuevo");
        add(contenedor);//Como hereda de JFrame hace sobrecarga al contenedor principal
        
        setJMenuBar(crearMenu());//Creamos el menú con el método
        
        setSize(500,350);//Definimos el tamaño
        setLocationRelativeTo(null); // Para centrar la ventana
        
        panelVisual.actualizarVista();//Para presentar el primer cliente
    }
    
    /**
     * CREA EL MENÚ Y DEVUELVE EL MISMO COMPONENTE JMenuBar
     * @return 
     */
    private JMenuBar crearMenu(){
        //Creamos los items del menú
        JMenuItem itemA = new JMenuItem("Visualizar Clientes");
        JMenuItem itemB = new JMenuItem("Nuevo Cliente");
        JMenuItem itemSalir = new JMenuItem("Salir");
        //Luego los listener al pulsarlos
        itemA.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cardLayout.show(contenedor,"visual");
                panelVisual.actualizarVista();//Actualizamos la vista
            }
        });        
        itemB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){                
                cardLayout.show(contenedor, "nuevo"); 
            }
        });
        itemSalir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                /*
                Termina inmediatamente la JVM (toda la aplicación), sin importar
                en qué punto del código estés. 0; normal. !0; error.
                */                
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

    
    
/*    
public class VentanaPrincipal extends JFrame {//HEREDA DE JFRAME
    //PARA CONTROLAR "LO QUE SE ESCRIBE" DESDE LOS ATRIBUTOS
    private static final String CARD_VISUAL = "VISUALIZAR";
    private static final String CARD_NUEVO = "NUEVO";
*/

/*
PASO DE DATOS DE UN PANEL A OTRO del ejemplo
    panelNuevo.setNumero(panelVisual.getNumero());
*/

    
/*EXPLICACIÓN CARDLAYOUT
    
// Crear el contenedor con CardLayout
JPanel cards = new JPanel(new CardLayout());

// Crear las "tarjetas" (paneles)
JPanel card1 = new JPanel();
card1.add(new JLabel("Paso 1: Bienvenido"));

JPanel card2 = new JPanel();
card2.add(new JLabel("Paso 2: Datos"));

// Añadir con un nombre (string) que las identifica
cards.add(card1, "paso1");
cards.add(card2, "paso2");
    
    
    this (JFrame o JPanel)
 └── contenedor  (JPanel con CardLayout)
      ├── panelA   → nombre "CARTA_A"
      └── panelB   → nombre "CARTA_B"
    
    -----------------------------------------------------------------------
    
    // ESTE es el CONTENEDOR (la "baraja" que guarda todas las tarjetas)
    JPanel cards = new JPanel(new CardLayout());

    // ESTAS son las TARJETAS (cada una es un JPanel normal)
    JPanel card1 = new JPanel();
    JPanel card2 = new JPanel();
    
    Un "card" no es un tipo especial de componente. Es simplemente un JPanel 
    (o cualquier Component) que le añades al contenedor. Lo que lo hace una 
    "tarjeta" es que está dentro del JPanel que usa CardLayout.
    
    JPanel cards = new JPanel(new CardLayout());
//                                ^^^^^^^^^^
//          Aquí se CREA y se ASIGNA como layout manager del contenedor
    
    Pero para controlar el cambio de tarjetas necesitas una referencia a ese
    CardLayout. La obtienes así:
    
    CardLayout cl = (CardLayout) cards.getLayout();
//                   ^^^^^^^^^^ cast porque getLayout() devuelve LayoutManager
*/
}
