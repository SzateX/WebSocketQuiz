/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Parsers;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import edu.szatkowski.jakub.websocketquizmaven.Helpers.Enums.StatementType;
import edu.szatkowski.jakub.websocketquizmaven.Helpers.ResponseGenerator;
import edu.szatkowski.jakub.websocketquizmaven.Managers.AccountManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.GameManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.QuestionsManager;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.Abstract.ICommand;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class CommandParser {
    private CommandFactory commandFactory;
    public CommandParser(){
        this.commandFactory = new CommandFactory();
    }
    
    public void parseCommand(Session session, GameManager gameManager, AccountManager accountManager, QuestionsManager questionsManager, String txt){
        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        try{
            jsonObject = gson.fromJson(txt, JsonObject.class);
        }
        catch(Throwable e)
        {
            System.out.println("Hello");
        }
        if(jsonObject.has("command")) {
            String commandName = jsonObject.get("command").getAsString();
            jsonObject.remove("command");
            ICommand command = this.commandFactory.getCommand(commandName, jsonObject);
            if(command != null)
                command.execute(session, accountManager, gameManager, questionsManager);
            else
                try {
                    session.getBasicRemote().sendText(new ResponseGenerator().generateErrorResponse(StatementType.CommandNotFound));
                } catch (IOException ex) {
                    Logger.getLogger(CommandParser.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else
        {
            try {
                session.getBasicRemote().sendText(new ResponseGenerator().generateErrorResponse(StatementType.NotCommand));
            } catch (IOException ex) {
                Logger.getLogger(CommandParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
