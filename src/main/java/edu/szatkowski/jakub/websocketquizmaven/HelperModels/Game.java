/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.HelperModels;

import edu.szatkowski.jakub.websocketquizmaven.Managers.QuestionsManager;
import edu.szatkowski.jakub.websocketquizmaven.Models.Category;
import edu.szatkowski.jakub.websocketquizmaven.Models.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class Game {
    private int pin;
    private transient List<Session> sessions;
    private int numberOfPlayers;
    private Category category;
    
    private transient ScheduledExecutorService deffer;
    private boolean gameStared;
    
    private transient QuestionsManager questionsManager;
    
    private int questionNo = 0;
    List<Question> questions;
    
    public Game(int pin, Category category, QuestionsManager questionsManager)
    {
        this.pin = pin;
        sessions = new ArrayList<>();
        this.numberOfPlayers = 0;
        this.gameStared = false;
        this.category = category;
        this.questionsManager = questionsManager;
    }
    
    public int getNumberOfPlayers()
    {
        return numberOfPlayers;
    }
    
    public int getPin()
    {
        return this.pin;
    }
    
    public void addPlayer(Session session)
    {
        this.sessions.add(session);
        this.numberOfPlayers++;
    }
    
    public void removePlayer(Session session)
    {
        if(this.sessions.remove(session))
            this.numberOfPlayers--;
    }
    
    public void startGame()
    {
        this.questionNo = 0;
        this.gameStared = true;
        this.questions = this.questionsManager.getQuestionsFromCategory(category.categoryName);
        this.deffer = Executors.newSingleThreadScheduledExecutor();
        deffer.schedule(() -> this.sendQuestion(), 10, TimeUnit.SECONDS);
    }
    
    private void sendQuestion()
    {   
        this.broadcastMessage("QUESTION: " + this.questionNo);
        deffer.schedule(() -> this.sendAnswer(), 10, TimeUnit.SECONDS);
    }
    
    private void sendAnswer()
    {
        this.broadcastMessage("ANSWER: " + this.questionNo);
        if(questionNo++ < 10)
            deffer.schedule(() -> this.sendQuestion(), 10, TimeUnit.SECONDS);
        else
            stopGame();
    }
    
    private void stopGame()
    {
        this.gameStared = false;
        deffer = null;
    }
    
    private void broadcastMessage(String message)
    {
        for(Session s: sessions)
        {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
