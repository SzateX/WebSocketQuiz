/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import edu.szatkowski.jakub.websocketquizmaven.Helpers.Enums.StatementType;
import java.lang.reflect.Type;
/**
 *
 * @author Szatku
 */
public class ResponseGenerator {
    private Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    public <T> String generateResponse(T response)
    {
        JsonObject obj = new JsonObject();
        JsonElement serializedResponse = gson.toJsonTree(response);
        obj.add("response", serializedResponse);
        obj.addProperty("type", "response");
        obj.addProperty("className", response.getClass().getSimpleName());
        return obj.toString();
    }
    
    public String generateErrorResponse(StatementType errorType)
    {
        JsonObject obj = new JsonObject();
        obj.addProperty("type", "error");
        obj.addProperty("className", errorType.toString());
        
        JsonObject error = new JsonObject();
        switch(errorType)
        {
            case WrongCredentials:
                error.addProperty("message", "Inserted Credentials are incorrect");
                break;
            case AlreadyLoggedIn:
                error.addProperty("message", "User is currently logged in");
                break;
            case NotLoggedIn:
                error.addProperty("message", "Cannot perform action - user not logged in");
                break;
            case GameNotCreated:
                error.addProperty("message", "Server cannot create game");
                break;
            case CommandNotFound:
                error.addProperty("message", "Command not found");
                break;
            case GameNotExist:
                error.addProperty("message", "Game not found");
                break;
            case NotCommand:
                error.addProperty("message", "Not a Command");
                break;
            default:
                throw new AssertionError(errorType.name());
        }
        obj.add("response", error);
        return obj.toString();
    }
}
