/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platzi.gatos_app;

import com.google.gson.Gson;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class GatosService {
    
    public static Request getRequest(){
        return new Request.Builder().url("https://api.thecatapi.com/v1/images/search").get().build();
    }
    
    public static void verGatos() throws IOException {
        
        //1. Traemos información de la API
        
        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(getRequest()).execute();
    
        String elJson = response.body().string();
        
        
        //########### Forma 1 ################
        //cortar los Corchetes [{}]que empaquetan a la respuesta json
//        elJson = elJson.substring(1,elJson.length());
//        elJson = elJson.substring(0,elJson.length()-1);
//        
//        System.out.println("datos recibidos: "+elJson);
//          
//        //crear un objeto de la clase Gson
//        Gson gson = new Gson();
//        GatosModel gatosModel = gson.fromJson( elJson, GatosModel.class);
          //####################################

          //##### Forma 2 ######
          Gson gson = new Gson();
          GatosModel gatosModel = gson.fromJson(elJson, GatosModel[].class)[0];
          //########################
        
        System.out.println("datos del gato: "+ gatosModel.toString());
        
        //Redimensionar, en caso se necesite
        Image image = null;
        
        try{
            URL url = new URL(gatosModel.getUrl());
            image = ImageIO.read(url);
            
            ImageIcon fondoGato = new ImageIcon(image);
            
            if(fondoGato.getIconWidth()>800){
                //redimensionar
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800,600,java.awt.Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
            }
            
            String menu = "Opciones: \n" 
                    + "1. Ver otra imagen \n"
                    + "2. Favorito \n"
                    + "3. Volver \n";
            
            String[] botones = {"Ver otra imagen", "Favorito", "Volver"};
            
            String id_gato = gatosModel.getId();//String.valueOf(gatosModel.getId());
            
            String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE, fondoGato,botones, botones[0]);
            
            int seleccion = -1;
            //validamos qué opción selecciona el usuario
            for(int i=0;i<botones.length;i++){
                if(opcion.equals(botones[i])){
                    seleccion = i;
                }
            }
            
            switch (seleccion) {
                case 0:
                    verGatos();
                    break;
                case 1:
                    añadirGatoAFavoritos(gatosModel);
                    break;
                default:
                    break;
            }
            
        }catch(IOException e){
            System.out.println(e);
        }
        
    }
    
    public static void añadirGatoAFavoritos(GatosModel gatosModel){
        
        try {
           
            OkHttpClient client = new OkHttpClient();
            
              MediaType mediaType = MediaType.parse("application/json");
              RequestBody body = RequestBody.create(mediaType, "{\r\n    \"image_id\": \""+ gatosModel.getId()+"\"\r\n}");
              Request request = new Request.Builder()
                .url("https://api.thecatapi.com/v1/favourites")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", gatosModel.getAPI_KEY())
                .build();
              
              Response response = client.newCall(request).execute();
        }
        catch (IOException e) {
            System.out.println("Error: "+e);
        }
    }
    
    public static void verFavoritos(String apiKey) throws IOException{
    
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
          .url("https://api.thecatapi.com/v1/favourites")
          .get()
          .addHeader("Content-Type", "application/json")
          .addHeader("x-api-key", apiKey)
          .build();
        
        Response response = client.newCall(request).execute();
        
        String elJson = response.body().string();
        
        //Creamos el objeto Gson
        Gson gson = new Gson();
        GatoFavorito[] gatosArray = gson.fromJson(elJson, GatoFavorito[].class);
        
        //System.out.println("gatosArray: " + gatosArray[0].toString());
        
        if(gatosArray.length>0){
            
            int min = 1;
            int max = gatosArray.length;
            int aleatorio = (int) ((Math.random() * ((max-min)+1)) + min);
            int indice = aleatorio - 1;
            
            GatoFavorito gatoFavorito = gatosArray[indice];
            
            
            Image image = null;

            try{
                URL url = new URL( gatoFavorito.getImage().getUrl());
                image = ImageIO.read(url);

                ImageIcon fondoGato = new ImageIcon(image);

                if(fondoGato.getIconWidth()>800){
                    //redimensionar
                    Image fondo = fondoGato.getImage();
                    Image modificada = fondo.getScaledInstance(800,600,java.awt.Image.SCALE_SMOOTH);
                    fondoGato = new ImageIcon(modificada);
                }

                String menu = "Opciones: \n" 
                        + "1. Ver otra imagen \n"
                        + "2. Eliminar Favorito \n"
                        + "3. Volver \n";

                String[] botones = {"Ver otra imagen", "Eliminar Favorito", "Volver"};

                String id_gato = gatoFavorito.getId();//String.valueOf(gatosModel.getId());

                String opcion = (String) JOptionPane.showInputDialog(null, menu, id_gato, JOptionPane.INFORMATION_MESSAGE, fondoGato,botones, botones[0]);

                int seleccion = -1;
                //validamos qué opción selecciona el usuario
                for(int i=0;i<botones.length;i++){
                    if(opcion.equals(botones[i])){
                        seleccion = i;
                    }
                }

                switch (seleccion) {
                    case 0:
                        verGatos();
                        break;
                    case 1:
                        eliminarFavorito(gatoFavorito);
                        break;
                    default:
                        break;
                }

            }catch(IOException e){
                System.out.println(e);
            } 
        }
    }
    
    public static void eliminarFavorito(GatoFavorito gatoFavorito){
        
        try {
            OkHttpClient client = new OkHttpClient();
            
            Request request = new Request.Builder()
                    .url("https://api.thecatapi.com/v1/favourites/"+gatoFavorito.getId())
                    .delete(null)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatoFavorito.getApiKey())
                    .build();
            
            Response response = client.newCall(request).execute();
            
        } catch (IOException e) {
            
            System.out.println("Error: " + e);
        }
        
    }
    
}
