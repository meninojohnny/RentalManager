/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.johnny.utilitarie;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author perei
 */
public class PageUtil {

    public static void changePage(String page) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        String contextPath = externalContext.getRequestContextPath();
        
        try {
            externalContext.redirect(contextPath + "/" + page);
        } catch (IOException e) {
            // Lidar com exceção de redirecionamento
            e.printStackTrace();
        }
    }
    
}


