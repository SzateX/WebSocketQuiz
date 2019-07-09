/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Responses;

/**
 *
 * @author Szatku
 */
public class GameStartedResponse {
    private int pin;

    public GameStartedResponse(int pin) {
        this.pin = pin;
    }
}
