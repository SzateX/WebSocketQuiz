/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Helpers;

import edu.szatkowski.jakub.websocketquizmaven.Models.Answer;
import edu.szatkowski.jakub.websocketquizmaven.Models.Category;
import edu.szatkowski.jakub.websocketquizmaven.Models.Enums.AttachmentType;
import java.util.Set;

/**
 *
 * @author Szatku
 */
public class QuestionDTO {
    public Long id;
    public String questionName;
    public int correctAnswer;
    public Set<Answer> answers;
    public Category category;
    public String attachment;
    public AttachmentType attachmentType;
}
