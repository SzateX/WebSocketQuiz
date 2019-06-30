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
import com.google.gson.reflect.TypeToken;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.Abstract.ICommand;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.Commands.*;
import java.lang.reflect.Type;

/**
 *
 * @author Szatku
 */
public class CommandFactory {
    public ICommand getCommand(String commandName, JsonObject obj)
    {
        Gson gson = new Gson();
        try{
            String name = this.getClass().getPackage().getName()+".Commands."+commandName+"Command";
            Class<?> cls = Class.forName(name);
            TypeToken<?> typeToken = TypeToken.get(cls);
            return gson.fromJson(obj, typeToken.getType());
        }
        catch(ClassNotFoundException ex)
        {
            return null;
        }
    }
}
