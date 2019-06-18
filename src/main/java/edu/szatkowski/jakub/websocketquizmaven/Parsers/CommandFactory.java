/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Parsers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.Abstract.ICommand;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.*;

/**
 *
 * @author Szatku
 */
public class CommandFactory {
    public ICommand getCommand(String commandName, JsonObject obj)
    {
        Gson gson = new Gson();
        String a = obj.toString();
        switch(commandName)
        {
            case "ping":
                return gson.fromJson(a, PingCommand.class);
            case "sign_in":
                return gson.fromJson(a, SignInCommand.class);
            case "sign_out":
                return new SignInCommand();
            case "ïs_logged_in":
                return new IsLoggedInCommand();
        };
        return null;
    }
}
