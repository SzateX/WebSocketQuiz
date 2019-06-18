/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Managers;

import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class AccountManager {
    private List<Session> loggedInSessions;
    
    public AccountManager()
    {
        this.loggedInSessions = new ArrayList<>();
    }
    
    public void signIn(Session session, String username, String password)
    {
        if(username.equals("admin") && password.equals("admin1"))
            this.loggedInSessions.add(session);
    }
    
    public void signOut(Session session)
    {
        if(this.loggedInSessions.contains(session))
            this.loggedInSessions.remove(session);
    }
    
    public boolean isLoggedIn(Session session)
    {
        return this.loggedInSessions.contains(session);
    }
}
