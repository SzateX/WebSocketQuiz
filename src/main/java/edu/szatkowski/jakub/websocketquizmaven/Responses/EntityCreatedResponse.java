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
public class EntityCreatedResponse<T> {
    private Long id;
    private String entityClass;
    
    public EntityCreatedResponse(Long id, T entity)
    {
        this.id = id;
        this.entityClass = entity.getClass().getName();
    }
}
