/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Managers;

import edu.szatkowski.jakub.websocketquizmaven.Models.Game;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Szatku
 */
public class GameManager {
    private HashMap<Integer, Game> games;
    
    public GameManager() {
        games = new HashMap<>();
    }
    
    public int createGame()
    {
        Random rand = new Random();
        for(int i = 0; i < 100; i++)
        {
            int generatedPin = rand.nextInt((999999 - 100000) + 1) + 100000;
            if(!this.games.containsKey(generatedPin))
            {
                Game newGame = new Game(generatedPin);
                games.put(generatedPin, newGame);
                return generatedPin;
            }
        }
        return -1;
    }
    
    public HashMap<Integer, Game> getGames()
    {
        return this.games;
    }
    
    public Game getGame(int pin)
    {
        return this.games.getOrDefault(pin, null);
    }
    
    public boolean removeGame(int pin)
    {
        return this.games.remove(pin) != null;
    }
}
