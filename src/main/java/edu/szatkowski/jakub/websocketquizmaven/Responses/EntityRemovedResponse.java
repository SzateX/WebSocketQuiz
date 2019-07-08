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
public class EntityRemovedResponse<T> {
    private boolean removed;
    private String entityClass;

    public EntityRemovedResponse(boolean removed, T entity) {
        this.removed = removed;
        this.entityClass = entity.getClass().getName();
    }
    
}
