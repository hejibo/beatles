package com.umbrellary.beatles;

import java.util.Map;

import com.umbrellary.daoimpl.ArticleDaoimpl;
import com.umbrellary.entry.Articles;
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
            Articles home = new Articles();
            home.setTitle(entry.getKey());
            home.setContent(entry.getValue().toString());

            articleDaoimpl.save(home);
        }
    }

    @Autowired
    @Qualifier("articleDaoimpl")
    private ArticleDaoimpl articleDaoimpl;

}
