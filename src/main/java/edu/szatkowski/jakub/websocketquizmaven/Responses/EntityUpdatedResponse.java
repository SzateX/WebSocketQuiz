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
public class EntityUpdatedResponse<T> {
    private T entity;
    private String entityClass;

    public EntityUpdatedResponse(T entity) {
        this.entity = entity;
        this.entityClass = entity.getClass().getName();
    }
}
