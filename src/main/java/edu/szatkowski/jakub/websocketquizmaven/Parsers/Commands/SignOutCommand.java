/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands;

import edu.szatkowski.jakub.websocketquizmaven.Helpers.Enums.StatementType;
import edu.szatkowski.jakub.websocketquizmaven.Helpers.ResponseGenerator;
import edu.szatkowski.jakub.websocketquizmaven.Managers.AccountManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.GameManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.QuestionsManager;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.Abstract.ICommand;
import edu.szatkowski.jakub.websocketquizmaven.Responses.SignOutResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class SignOutCommand implements ICommand{

    @Override
    public void execute(Session session, AccountManager accountManager, GameManager gameManager, QuestionsManager questionsManager) {
        ResponseGenerator responseGenerator = new ResponseGenerator();
        StatementType result = accountManager.signOut(session);
        try {
            if(result == StatementType.SignOutSuccessful)
            {
                SignOutResponse response = new SignOutResponse(false);
                session.getBasicRemote().sendText(responseGenerator.generateResponse(response));
                return;
            }
        
            session.getBasicRemote().sendText(responseGenerator.generateErrorResponse(result));
        } catch (IOException ex) {
            Logger.getLogger(SignOutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
