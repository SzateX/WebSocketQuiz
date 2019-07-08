/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands;

import edu.szatkowski.jakub.websocketquizmaven.HelperModels.Game;
import edu.szatkowski.jakub.websocketquizmaven.Helpers.Enums.StatementType;
import edu.szatkowski.jakub.websocketquizmaven.Helpers.ResponseGenerator;
import edu.szatkowski.jakub.websocketquizmaven.Managers.AccountManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.GameManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.QuestionsManager;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.Abstract.ICommand;
import edu.szatkowski.jakub.websocketquizmaven.Responses.AnsweredQuestionResponse;
import edu.szatkowski.jakub.websocketquizmaven.Responses.PlayerAddedResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class AnswerQuestionCommand implements ICommand{
    private int pin;
    private int answer;

    @Override
    public void execute(Session session, AccountManager accountManager, GameManager gameManager, QuestionsManager questionsManager) {
        ResponseGenerator responseGenerator = new ResponseGenerator();
        Game game = gameManager.getGame(pin);
        try{
            if(game == null)
            {
                session.getBasicRemote().sendText(responseGenerator.generateErrorResponse(StatementType.GameNotExist));
                return;
            }
            StatementType result = game.answerQuestion(session, answer);
            if(result != StatementType.AnsweredQuestion)
            {
                session.getBasicRemote().sendText(responseGenerator.generateErrorResponse(result));
                return;
            }
            AnsweredQuestionResponse response = new AnsweredQuestionResponse(answer);
            session.getBasicRemote().sendText(responseGenerator.generateResponse(response));
        }
        catch (IOException ex)
        {
            Logger.getLogger(CreateGameCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
