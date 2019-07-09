/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Managers;

import edu.szatkowski.jakub.websocketquizmaven.Helpers.QuestionDTO;
import edu.szatkowski.jakub.websocketquizmaven.Models.Answer;
import edu.szatkowski.jakub.websocketquizmaven.Models.Category;
import edu.szatkowski.jakub.websocketquizmaven.Models.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Szatku
 */
public class QuestionsManager {
    private SessionFactory sessionFactory;
    
    public QuestionsManager(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }
    
    public List<Question> getAllQuestions()
    {
        Session session = sessionFactory.openSession();
        List questions = session.createCriteria(Question.class).list();
        session.close();
        return questions;
    }
    
    public List<Question> getQuestionsFromCategory(String categoryName)
    {
        Session session = sessionFactory.openSession();
        List questions = session.createCriteria(Question.class)
                .createAlias("category", "c")
                .add(Restrictions.eq("c.categoryName", categoryName))
                .list();
        session.close();
        return questions;
    }
    
    public Question getQuestion(Long questionId)
    {
        Session session = sessionFactory.openSession();
        Question question = (Question) session.get(Question.class, questionId);
        session.close();
        return question;
    }
    
    public Long addQuestion(QuestionDTO question)
    {
        Question q = new Question();
        q.attachment = question.attachment;
        q.attachmentType = question.attachmentType;
        q.category = question.category;
        q.correctAnswer = question.correctAnswer;
        q.questionName = question.questionName;
        Long id = this.addEntity(q);
        q.id = id;
        for(Answer answer: question.answers)
        {
            answer.question = q;
            this.addEntity(answer);
        }
        
        return id;
    }
    
    public void removeQuestion(Question question)
    {
        Question q = this.getQuestion(question.id);
        List<Answer> answers = this.getAnswersOfQuestion(q.id);
        for(Answer a: answers)
            this.removeEntity(a);
        this.removeEntity(q);
    }
    
    public void updateQuestion(Question question)
    {
        this.updateEntity(question);
    }
    
    public List<Category> getCategories()
    {
        Session session = sessionFactory.openSession();
        List categories = session.createCriteria(Category.class).list();
        session.close();
        return categories;
    }
    
    public Category getCategory(Long categoryId)
    {
        Session session = sessionFactory.openSession();
        Category category = (Category) session.get(Category.class, categoryId);
        session.close();
        return category;
    }
    
    public Category getCategoryByName(String categoryName)
    {
        Session session = sessionFactory.openSession();
        Category category = (Category) session.createCriteria(Category.class)
                .add(Restrictions.like("categoryName", categoryName))
                .setMaxResults(1)
                .uniqueResult();
        session.close();
        return category;
    }
    
    public Long addCategory(Category category)
    {
        return this.addEntity(category);
    }
    
    public void removeCategory(Category category)
    {
        this.removeEntity(category);
    }
    
    public void updateCategory(Category category)
    {
        this.updateEntity(category);
    }
    
    public List<Answer> getAnswers()
    {
        Session session = sessionFactory.openSession();
        List answers = session.createCriteria(Answer.class).list();
        session.close();
        return answers;
    }
    
    public List<Answer> getAnswersOfQuestion(Long questionId)
    {
        Session session = sessionFactory.openSession();
        //Question question = (Question) session.get(Question.class, questionId);
        List answers = session.createCriteria(Answer.class)
                .createAlias("question", "q")
                .add(Restrictions.eq("q.id", questionId))
                .list();
        session.close();
        return new ArrayList(answers);
    }
    
    public Answer getAnswer(Long answerId)
    {
        Session session = sessionFactory.openSession();
        Answer answer = (Answer) session.get(Answer.class, answerId);
        session.close();
        return answer;
    }
    
    public Long addAnswer(Answer answer)
    {
        return this.addEntity(answer);
    }
    
    public void removeAnswer(Answer answer)
    {
        this.removeEntity(answer);
    }
    
    public void updateAnswer(Answer answer)
    {
        this.updateEntity(answer);
    }
    
    private Long addEntity(Object obj)
    {
        Session session = sessionFactory.openSession();
        Long id = (Long) session.save(obj);
        session.close();
        return id;
    }
    
    private void removeEntity(Object obj)
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(obj);
        session.getTransaction().commit();
        session.close();
    }
    
    private void updateEntity(Object obj)
    {
        Session session = sessionFactory.openSession();
        //session.saveOrUpdate(obj);
        session.beginTransaction();
        session.merge(obj);
        session.getTransaction().commit();
        session.close();
    }
}
