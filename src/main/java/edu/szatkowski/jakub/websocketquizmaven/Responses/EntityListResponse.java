/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Responses;

import java.util.List;

/**
 *
 * @author Szatku
 */
public class EntityListResponse<T> {
    private List<T> entities;
    private String entityClass;

    public EntityListResponse(List<T> entities, Class entityClass) {
        this.entities = entities;
        this.entityClass = entityClass.getName();
    }
}
