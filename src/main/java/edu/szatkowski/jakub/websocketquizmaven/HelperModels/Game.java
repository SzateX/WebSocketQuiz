/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.HelperModels;

import edu.szatkowski.jakub.websocketquizmaven.Helpers.Enums.StatementType;
import edu.szatkowski.jakub.websocketquizmaven.Helpers.ResponseGenerator;
import edu.szatkowski.jakub.websocketquizmaven.Managers.QuestionsManager;
import edu.szatkowski.jakub.websocketquizmaven.Models.Category;
import edu.szatkowski.jakub.websocketquizmaven.Models.Question;
import edu.szatkowski.jakub.websocketquizmaven.Responses.BadAnswerResponse;
import edu.szatkowski.jakub.websocketquizmaven.Responses.CorrectAnswerResponse;
import edu.szatkowski.jakub.websocketquizmaven.Responses.EndGameResponse;
import edu.szatkowski.jakub.websocketquizmaven.Responses.QuestionResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.websocket.Session;

/**
 *
 * @author Szatku
 */
public class Game {
    private int pin;
    private transient HashMap<Session, Integer> players;
    private int numberOfPlayers;
    private Category category;
    
    private transient ScheduledExecutorService deffer;
    private boolean gameStared;
    
    private transient QuestionsManager questionsManager;
    
    private transient ResponseGenerator responseGenerator;
    
    private int questionNo = 0;
    List<Question> questions;
    private transient HashMap<Session, Integer> answers;
    
    public Game(int pin, Category category, QuestionsManager questionsManager)
    {
        this.pin = pin;
        players = new HashMap();
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
        this.players.put(session, 0);
        this.numberOfPlayers++;
    }
    
    public void removePlayer(Session session)
    {
        if(this.players.containsKey(session)){
            this.players.remove(session);
            this.numberOfPlayers--;
        }
    }
    
    public void startGame()
    {
        this.questionNo = 0;
        this.gameStared = true;
        this.questions = this.questionsManager.getQuestionsFromCategory(category.categoryName);
        this.answers = new HashMap();
        this.deffer = Executors.newSingleThreadScheduledExecutor();
        deffer.schedule(() -> this.sendQuestion(), 10, TimeUnit.SECONDS);
    }
    
    private void sendQuestion()
    {   
        QuestionResponse response = new QuestionResponse(this.questions.get(this.questionNo));
        this.broadcastMessage(this.responseGenerator.generateResponse(response));
        deffer.schedule(() -> this.sendAnswer(), 10, TimeUnit.SECONDS);
    }
    
    private void sendAnswer()
    {
        Set<Session> correctAnswers;
        Set<Session> badAnswers;
        int correctAnswer = this.questions.get(this.questionNo).correctAnswer;
        correctAnswers = this.answers.entrySet().stream()
                .filter(x -> x.getValue() == correctAnswer)
                .map(Map.Entry::getKey)
                .map(x -> {this.players.put(x, this.players.get(x) + 1); return x;})
                .collect(Collectors.toCollection(HashSet::new));
        badAnswers = new HashSet(this.players.keySet());
        badAnswers.removeAll(correctAnswers);
        this.sendMessage(badAnswers, this.responseGenerator.generateResponse(new BadAnswerResponse(correctAnswer)));
        this.sendMessage(correctAnswers, this.responseGenerator.generateResponse(new CorrectAnswerResponse(correctAnswer)));
        
        this.answers.clear();
        if(questionNo++ < questions.size() - 1)
            deffer.schedule(() -> this.sendQuestion(), 10, TimeUnit.SECONDS);
        else
            stopGame();
    }
    
    public StatementType answerQuestion(Session session, int answer)
    {
        if(this.players.containsKey(session)){
            this.answers.put(session, answer);
            return StatementType.AnsweredQuestion;
        }
        return StatementType.NotInGame;
    }
    
    private void stopGame()
    {
        this.gameStared = false;
        deffer = null;
        int maxPossibleScore = questions.size();
        for(Session s: players.keySet())
        {
            try {
                s.getBasicRemote().sendText(this.responseGenerator.generateResponse(new EndGameResponse(players.get(s), maxPossibleScore)));
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void broadcastMessage(String message)
    {
        this.sendMessage(this.players.keySet(), message);
    }
    
    private void sendMessage(Set<Session> players, String message)
    {
        players.stream().forEach((s) -> {
            try {
                s.getBasicRemote().sendText(message);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
