/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platzi.gatos_app;

/**
 *
 * @author ASUS
 */
public class GatoFavorito {
    
    private String id;
    private String image_id;
    private String apiKey = "live_6UUfYRPkV8bNbo1mfzDqsbXpvSy3qLJNWW6U8ubnemof7Ke6DBER3ZoRhdeOEq5E";
    private Image image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "GatoFavorito{" + "id=" + id + ", image_id=" + image_id + ", apiKey=" + apiKey + ", image=" + image + '}';
    }
    
    
    
}
