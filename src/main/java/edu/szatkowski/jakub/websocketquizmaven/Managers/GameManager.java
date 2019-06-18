/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Managers;

import edu.szatkowski.jakub.websocketquizmaven.Models.Game;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Szatku
 */
public class GameManager {
    private List<Game> games;
    
    public GameManager() {
        games = new ArrayList<>();
    }
    
    public int createGame()
    {
        Random rand = new Random();
        List<Integer> notAvailablePins = games.stream().map(game -> game.getPin()).collect(Collectors.toList());
        for(int i = 0; i < 100; i++)
        {
            int generatedPin = rand.nextInt((999999 - 100000) + 1) + 100000;
            if(!notAvailablePins.contains(generatedPin))
            {
                Game newGame = new Game(generatedPin);
                games.add(newGame);
                return generatedPin;
            }
        }
        return -1;
    }
    
    public Game getGame(int pin)
    {
        Optional<Game> opt = games.stream().filter(game -> game.getPin() == pin).findFirst();
        if(opt.isPresent())
            return opt.get();
        return null;
    }
}
