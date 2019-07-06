/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Responses;

import edu.szatkowski.jakub.websocketquizmaven.Models.Question;

/**
 *
 * @author Szatku
 */
public class QuestionResponse {
    public Question question;
    
    public QuestionResponse(Question question)
    {
        this.question = question;
    }
}
