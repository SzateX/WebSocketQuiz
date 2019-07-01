/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Models;

import edu.szatkowski.jakub.websocketquizmaven.Models.Enums.AttachmentType;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Szatku
 */

@Entity
@Table(name="Question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @Column()
    public String questionName;
    
    @Column()
    public transient int correctAnswer;
    
    @OneToMany(mappedBy="question")
    public Set<Answer> answers;
    
    @ManyToOne
    @JoinColumn(name="id_category", nullable=false)
    public Category category;
    
    @Column()
    public String attachment;
    
    @Enumerated(EnumType.STRING)
    public AttachmentType attachmentType;
}
