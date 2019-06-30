/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Responses;

import com.google.gson.Gson;

/**
 *
 * @author Jakub.Szatkowski.2
 */
public class IsLoggedInResponse{
    protected boolean loggedIn;
    
    public IsLoggedInResponse(boolean loggedIn)
    {
        this.loggedIn = loggedIn;
    }
}
