package com.umbrellary.beatles;

import java.util.Map;

import com.umbrellary.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

@Service("databasePipeline")
public class DatabasePipeline implements Pipeline {

    @Override
    public void process(ResultItems resultItems, Task task) {

        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {

            //System.out.println(entry.getKey() + " : " + entry.getValue().toString());

            if (iArticleService == null) {
                System.out.println("iArticleService is null");
            }

            iArticleService.addOneArticle(entry.getKey(), entry.getValue().toString());
        }
    }

    @Autowired
    @Qualifier("articleService")
    private IArticleService iArticleService;

}
