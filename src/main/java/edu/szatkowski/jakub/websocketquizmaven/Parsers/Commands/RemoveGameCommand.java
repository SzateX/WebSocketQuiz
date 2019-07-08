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
import edu.szatkowski.jakub.websocketquizmaven.Responses.GameRemovedResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author Jakub.Szatkowski.2
 */
public class RemoveGameCommand implements ICommand{
    private int pin;
    
    @Override
    public void execute(Session session, AccountManager accountManager, GameManager gameManager, QuestionsManager questionsManager) {
        ResponseGenerator responseGenerator = new ResponseGenerator();
        try{
            if(!accountManager.isLoggedIn(session))
            {
                session.getBasicRemote().sendText(responseGenerator.generateErrorResponse(StatementType.NotLoggedIn));
                return;
            }
            
            if(!gameManager.removeGame(pin))
            {
                session.getBasicRemote().sendText(responseGenerator.generateErrorResponse(StatementType.GameNotExist));
                return;
            }
            GameRemovedResponse response = new GameRemovedResponse(true);
            session.getBasicRemote().sendText(responseGenerator.generateResponse(response));
        }
        catch (IOException ex)
        {
            Logger.getLogger(CreateGameCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
