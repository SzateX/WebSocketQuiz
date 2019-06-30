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
public class GameCreatedResponse {
    private int pin;
    
    public GameCreatedResponse(int pin)
    {
        this.pin = pin;
    }
}
