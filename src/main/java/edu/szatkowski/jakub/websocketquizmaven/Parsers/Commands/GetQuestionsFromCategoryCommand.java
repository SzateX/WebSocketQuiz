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
import edu.szatkowski.jakub.websocketquizmaven.Models.Answer;
import edu.szatkowski.jakub.websocketquizmaven.Models.Question;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.Abstract.ICommand;
import edu.szatkowski.jakub.websocketquizmaven.Responses.EntityListResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class GetQuestionsFromCategoryCommand implements ICommand {
    private String categoryName;
            
    @Override
    public void execute(Session session, AccountManager accountManager, GameManager gameManager, QuestionsManager questionsManager) {
        ResponseGenerator responseGenerator = new ResponseGenerator();
        try{
            if(!accountManager.isLoggedIn(session))
            {
                session.getBasicRemote().sendText(responseGenerator.generateErrorResponse(StatementType.NotLoggedIn));
                return;
            }
            
            List<Question> questions = questionsManager.getQuestionsFromCategory(categoryName);
            for(Question q: questions)
            {
                for(Answer a: q.answers)
                {
                    a.question = null;
                }
            }
            EntityListResponse response = new EntityListResponse(questions, Question.class);
            session.getBasicRemote().sendText(responseGenerator.generateResponse(response));
        }
        catch (IOException ex)
        {
            Logger.getLogger(CreateGameCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
