/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.szatkowski.jakub.websocketquizmaven.Managers;

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
    
    public Long addQuestion(Question question)
    {
        return this.addEntity(question);
    }
    
    public void removeQuestion(Question question)
    {
        this.removeEntity(question);
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
        Category category = (Category) session.get(Question.class, categoryId);
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
        Question question = (Question) session.get(Question.class, questionId);
        session.close();
        return new ArrayList(question.answers);
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
        session.delete(obj);
        session.close();
    }
    
    private void updateEntity(Object obj)
    {
        Session session = sessionFactory.openSession();
        session.update(obj);
        session.close();
    }
}
