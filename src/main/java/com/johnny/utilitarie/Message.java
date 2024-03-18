package com.johnny.utilitarie;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Message {
    
    public static void msg(String message) {
         FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(message));
    }
    
}
