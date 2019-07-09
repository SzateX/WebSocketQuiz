/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Helpers;

import edu.szatkowski.jakub.websocketquizmaven.Models.Answer;
import edu.szatkowski.jakub.websocketquizmaven.Models.Category;
import edu.szatkowski.jakub.websocketquizmaven.Models.Enums.AttachmentType;
import edu.szatkowski.jakub.websocketquizmaven.Models.Question;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Szatku
 */
public class NextQuestionDTO {
    public Long id;
    public String questionName;
    public Set<Answer> answers;
    public Category category;
    public String attachment;
    public AttachmentType attachmentType;
    
    public NextQuestionDTO(Question q, List<Answer> a)
    {
                
    }
}
