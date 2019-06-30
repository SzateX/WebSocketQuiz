/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Managers;

import edu.szatkowski.jakub.websocketquizmaven.Helpers.Enums.StatementType;
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
    
    public StatementType signIn(Session session, String username, String password)
    {
        if(this.isLoggedIn(session))
            return StatementType.AlreadyLoggedIn;
        if(username.equals("admin") && password.equals("admin1")){
            this.loggedInSessions.add(session);
            return StatementType.SignInSuccessful;
        }
        return StatementType.WrongCredentials;
    }
    
    public StatementType signOut(Session session)
    {
        if(this.isLoggedIn(session)){
            this.loggedInSessions.remove(session);
            return StatementType.SignOutSuccessful;
        }
        return StatementType.NotLoggedIn;
    }
    
    public boolean isLoggedIn(Session session)
    {
        return this.loggedInSessions.contains(session);
    }
}
