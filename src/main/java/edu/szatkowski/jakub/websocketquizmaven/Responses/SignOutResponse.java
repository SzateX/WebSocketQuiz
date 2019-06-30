/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Responses;

/**
 *
 * @author Jakub.Szatkowski.2
 */
public class SignOutResponse extends IsLoggedInResponse{

    public SignOutResponse(boolean loggedIn) {
        super(loggedIn);
    }
    
}
