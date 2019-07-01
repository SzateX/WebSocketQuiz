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
}
