/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven;

import edu.szatkowski.jakub.websocketquizmaven.Managers.AccountManager;
import edu.szatkowski.jakub.websocketquizmaven.Managers.GameManager;
import edu.szatkowski.jakub.websocketquizmaven.Parsers.CommandParser;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
/**
 *
 * @author Szatku
 */
@ServerEndpoint("/ws")
public class WebSocketHandler {
    private static GameManager gameManager = new GameManager();
    private static CommandParser commandParser = new CommandParser();
    private static AccountManager accountManager = new AccountManager();
    private Session session;
    @OnOpen
    public void open(Session session) {
        this.session = session;
    }

    @OnClose
    public void close(Session session) {
    }

    @OnError
    public void onError(Throwable error) {
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException{
        AtomicBoolean m = new AtomicBoolean(session.equals(this.session));
        //session.getBasicRemote().sendText(m.toString());
        session.getBasicRemote().sendText(message);
        commandParser.parseCommand(session, gameManager, accountManager, message);
    }
}
