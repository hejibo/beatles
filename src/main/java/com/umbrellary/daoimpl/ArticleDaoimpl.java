package com.umbrellary.daoimpl;

import com.umbrellary.dao.IArticleDao;
import com.umbrellary.entry.Articles;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManagerFactory;

@Repository("articleDaoimpl")
public class ArticleDaoimpl implements IArticleDao {

    private SessionFactory hibernateFactory;

    @Autowired
    public void SomeService(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);
    }

    @Override
    public void addOneArticle(Articles articles) {
        Session session = hibernateFactory.openSession();
        try {
            session.save(articles);
        } finally {
            session.flush();
            session.close();
        }
    }
}
