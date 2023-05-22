/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jpanelimagenconeditor;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author aga
 */
public class ImagenFondoPropertyEditorSupport extends PropertyEditorSupport {

    private ImagenFondoPanel imagenFondoPanel = new ImagenFondoPanel();
    //si hay un editor personalizado o no,por defecto: false, aqui: true 
    @Override
    public boolean supportsCustomEditor() {
        return true; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    //aqui pie el panel
    @Override
    public Component getCustomEditor() {
        return imagenFondoPanel; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    public String getJavaInitializationString() {
        ImagenFondo imagenFondo = imagenFondoPanel.getSelectedValue();
        return "new jpanelimagenconeditor.ImagenFondo(" + "new java.io.File(\"" + imagenFondo.getRutaImagen().getAbsolutePath()+"\"), " + imagenFondo.getOpacidad() + "f)"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    
    //obtiene el valor que cuando el usuario seleccionó la opción 
    //en el panel y pulsa aceptar
    //se llama al metodo getSelectedValue sobre la instancia de la
    //clase ImagenFondoPanel
    @Override
    public Object getValue() {
        return imagenFondoPanel.getSelectedValue(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
    
    
    
}
