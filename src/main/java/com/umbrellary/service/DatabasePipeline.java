package com.umbrellary.service;

import java.util.Map;

import com.umbrellary.BeatlesApplication;
import com.umbrellary.daoimpl.ArticleRepository;
import com.umbrellary.entry.Articles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Service("databasePipeline")
public class DatabasePipeline implements Pipeline {
    private static final Logger logger = LoggerFactory.getLogger(BeatlesApplication.class);

    @Override
    public void process(ResultItems resultItems, Task task) {
        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {

            Articles home = new Articles();
            home.setTitle(entry.getKey());
            home.setContent(entry.getValue().toString());
            articleRepository.save(home);

        }
    }

    public long getCount() {
        return articleRepository.count();
    }

    public String getbyid(long id) {
        return articleRepository.findByArticleId(id).getTitle();
    }

    @Autowired
    private ArticleRepository articleRepository;

}
