/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Responses;

/**
 *
 * @author Szatku
 */
public class EndGameResponse {
    private int result;
    private int maxScore;

    public EndGameResponse(int result, int maxScore) {
        this.result = result;
        this.maxScore = maxScore;
    }
}
