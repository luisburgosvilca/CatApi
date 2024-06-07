/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.platzi.gatos_app;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Inicio {

    public static void main(String[] args) throws IOException {
        int opcionMenu = -1;
        
        String[] botones = {"1. Ver Gatos", "2. Ver Favoritos", "3. Salir"};
        
        do{
            
            String opcion = (String) JOptionPane.showInputDialog(null, "Gatitos Java", 
                    "Menú Principal", JOptionPane.INFORMATION_MESSAGE,null,botones, botones[0]);
            
            for(int i = 0; i < botones.length; i++) {
                if(opcion.equals(botones[i])){
                    opcionMenu=i;
                }
            }
            
            switch (opcionMenu) {
                case 0:
                    GatosService.verGatos();
                    break;
                case 1:
                    GatosModel gatosModel = new GatosModel();
                    GatosService.verFavoritos(gatosModel.getAPI_KEY());
                case 2:
                    break;
                default:
                    throw new AssertionError();
            }
            
        }while(opcionMenu!=1);
    }
}
