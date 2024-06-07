/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.platzi.gatos_app;

/**
 *
 * @author ASUS
 */
public class GatosModel {
    
    private String id;
    private String url;
    private String API_KEY = "live_6UUfYRPkV8bNbo1mfzDqsbXpvSy3qLJNWW6U8ubnemof7Ke6DBER3ZoRhdeOEq5E";
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "GatosModel{" + "id=" + id + ", url=" + url + ", API_KEY=" + API_KEY + ", image=" + image + '}';
    }
    
    
            
    
}
