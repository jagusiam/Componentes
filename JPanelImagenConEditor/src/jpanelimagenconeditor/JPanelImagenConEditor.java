
package jpanelimagenconeditor;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author aga
 */
public class JPanelImagenConEditor extends JPanel implements Serializable {
    private ImagenFondo imagenFondo;
    //variables globales para evento de raton
    private boolean ratonPresionado = false;
    private Point puntoPresion;
    private ArrastreListener arrastreListener; //variable para ArrastreListener

    public JPanelImagenConEditor() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //si esta presionado
                if(ratonPresionado) {
                    Point puntoActual = e.getPoint();
                    //y la dif de arrastre en horizontal es >50
                    if (Math.abs(puntoPresion.x-puntoActual.x)>50) {
                        //TODO: el sitio para alojar el evento a disparar
                        //como es para utilizar en el futuro por quien utilice este componente
                        //se va a manejara a traves de la interfaz
                        //lo implemente quien utilice el 
                        
                        //y para evitar nullPointerEx - solo llamamos al listener si no es null
                        if (arrastreListener != null) {
                            arrastreListener.arrastre();
                        }
                        
                    }
                } 
                //se vuelve a poner a false una vez acabado
                //y cerrado el ciclo
                ratonPresionado = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                ratonPresionado = true; //a true
                puntoPresion=e.getPoint(); //punto en el que se presiono el raton
            }          
                        
        });
    }
    
    //debemos añadir un metodo para añadir un listener para que nos lo pasen 
    public void addArrastreListener(ArrastreListener arrastreListener) {
        this.arrastreListener = arrastreListener;
    }
    
    //y eliminar el listener en caso de que no se quiera hacer uso del evento
    public void removeArrastreListener() {
        this.arrastreListener = null;
        
    }

    public ImagenFondo getImagenFondo() {
        return imagenFondo;
    }

    public void setImagenFondo(ImagenFondo imagenFondo) {
        this.imagenFondo = imagenFondo;
        //hay que repintar el componente para que funcione el arrastre
        repaint();
    }

    
    //metodo setComposite - pasamos canal Alpha, recogemos opacidad
    //una vez pintada la imagen, volvemos a establecer la opacidad a 1 - 100%
    //
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //para evitar el nullpointerexception: 
        //que solo se ejecute si distinto:
        
        if (imagenFondo != null) 
        {
        if (imagenFondo.getRutaImagen()!=null && imagenFondo.getRutaImagen().exists()) {
            ImageIcon imageIcon = new ImageIcon(imagenFondo.getRutaImagen().getAbsolutePath());
            
            //para cambiar la opacidad con la que dibujamos
            //convertir a 2 d
            
            
            Graphics2D g2d = (Graphics2D)g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, imagenFondo.getOpacidad()));
            g.drawImage(imageIcon.getImage(), 0, 0, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        }
        }
    }
     
}
