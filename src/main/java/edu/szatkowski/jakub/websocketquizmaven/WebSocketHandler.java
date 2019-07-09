/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven;

import com.google.gson.Gson;
import edu.szatkowski.jakub.websocketquizmaven.HelperModels.Game;
import edu.szatkowski.jakub.websocketquizmaven.Managers.AccountManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.GameManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.QuestionsManager;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.CommandParser;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Szatku
 */
@ServerEndpoint("/ws")
public class WebSocketHandler {
    private static GameManager gameManager;
    private static CommandParser commandParser = new CommandParser();
    private static AccountManager accountManager = new AccountManager();
    private static SessionFactory sessionFactory;
    private static QuestionsManager questionsManager;
    private Session session;
    
    public WebSocketHandler()
    {
        if(sessionFactory == null){
            try{
		Configuration configuration = new Configuration().configure();
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
                questionsManager = new QuestionsManager(sessionFactory);
                gameManager = new GameManager(questionsManager);
            }
            catch(Throwable e)
            {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    
    @OnOpen
    public void open(Session session) {
        this.session = session;
    }

    @OnClose
    public void close(Session session) {
        HashMap<Integer, Game> games = gameManager.getGames();
        for(Game game: games.values())
        {
            game.removePlayer(session);
        }
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException{
        AtomicBoolean m = new AtomicBoolean(session.equals(this.session));
        //session.getBasicRemote().sendText(m.toString());
        session.getBasicRemote().sendText(message);
        try{
            commandParser.parseCommand(session, gameManager, accountManager, questionsManager, message);
        }
        catch(Throwable e)
        {
            Gson gson = new Gson();
            session.getBasicRemote().sendText(e.toString());
            e.printStackTrace();
        }
    }
}
