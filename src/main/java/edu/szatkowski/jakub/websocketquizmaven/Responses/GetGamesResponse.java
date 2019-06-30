/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Responses;

import edu.szatkowski.jakub.websocketquizmaven.Models.Game;
import java.util.HashMap;

/**
 *
 * @author Jakub.Szatkowski.2
 */
public class GetGamesResponse {
    HashMap<Integer, Game> games;
    
    public GetGamesResponse(HashMap<Integer, Game> games)
    {
        this.games = games;
    }
}
