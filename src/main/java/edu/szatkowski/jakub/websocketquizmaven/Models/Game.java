/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Models;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class Game {
    private int pin;
    private List<Session> sessions;
    
    public Game(int pin)
    {
        this.pin = pin;
        sessions = new ArrayList<>();
    }
    
    public int getPin()
    {
        return this.pin;
    }
    
    public void addPlayer(Session session)
    {
        this.sessions.add(session);
    }
    
    public void removePlayer(Session session)
    {
        this.sessions.remove(session);
    }
}
