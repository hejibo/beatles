package com.umbrellary.serviceimpl;

import com.umbrellary.dao.IArticleDao;
import com.umbrellary.entry.Articles;
import com.umbrellary.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("articleService")
public class ArticleService implements IArticleService {

    @Autowired
    @Qualifier("articleDaoimpl")
    private IArticleDao iArticleDao;

    @Override
    public void addOneArticle(String title, String content) {
        Articles home = new Articles();
        home.setTitle(title);
        home.setContent(content);

        iArticleDao.addOneArticle(home);
    }
}
