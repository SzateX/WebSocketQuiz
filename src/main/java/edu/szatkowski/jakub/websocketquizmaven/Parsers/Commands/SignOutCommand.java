/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands;

import edu.szatkowski.jakub.websocketquizmaven.Managers.AccountManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.GameManager;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.Abstract.ICommand;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class SignOutCommand implements ICommand{

    @Override
    public void execute(Session session, AccountManager accountManager, GameManager gameManager) {
        accountManager.signOut(session);
    }
    
}
