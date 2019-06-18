/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands;

import edu.szatkowski.jakub.websocketquizmaven.Managers.AccountManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.GameManager;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.Abstract.ICommand;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class IsLoggedInCommand implements ICommand{

    @Override
    public void execute(Session session, AccountManager accountManager, GameManager gameManager) {
        boolean loggedIn = accountManager.isLoggedIn(session);
        try {
            session.getBasicRemote().sendObject(loggedIn);
        } catch (IOException ex) {
            Logger.getLogger(IsLoggedInCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncodeException ex) {
            Logger.getLogger(IsLoggedInCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
