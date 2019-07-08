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
import edu.szatkowski.jakub.websocketquizmaven.Responses.SignInResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class SignInCommand implements ICommand{
    String username;
    String password;
    
    @Override
    public void execute(Session session, AccountManager accountManager, GameManager gameManager, QuestionsManager questionsManager) {
        ResponseGenerator responseGenerator = new ResponseGenerator();
        StatementType result = accountManager.signIn(session, username, password);
        try {
            if (result == StatementType.SignInSuccessful) {
                SignInResponse response = new SignInResponse(true);
                session.getBasicRemote().sendText(responseGenerator.generateResponse(response));
                return;
            }
            session.getBasicRemote().sendText(responseGenerator.generateErrorResponse(result));
        } catch (IOException ex) {
            Logger.getLogger(SignInCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
