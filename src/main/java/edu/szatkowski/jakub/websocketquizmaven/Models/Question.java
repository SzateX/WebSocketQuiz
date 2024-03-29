/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Models;

import edu.szatkowski.jakub.websocketquizmaven.Models.Enums.AttachmentType;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

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
    public int correctAnswer;
    
    @OneToMany(mappedBy="question", cascade=CascadeType.ALL, orphanRemoval = true)
    public Set<Answer> answers;
    
    @ManyToOne
    @JoinColumn(name="id_category", nullable=false)
    public Category category;
    
    @Column()
    public String attachment;
    
    @Enumerated(EnumType.STRING)
    public AttachmentType attachmentType;

    public Question()
    {
    }
    
    public Question(Question q) {
        this.id = q.id;
        this.questionName = q.questionName;
        this.correctAnswer = q.correctAnswer;
        this.answers = q.answers;
        this.category = q.category;
        this.attachment = q.attachment;
        this.attachmentType = q.attachmentType;
    }
    
    
}
