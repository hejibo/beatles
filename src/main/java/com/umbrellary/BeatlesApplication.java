package com.umbrellary;

import com.umbrellary.beatles.CSDNNewsPageProcessor;
import com.umbrellary.beatles.DatabasePipeline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Spider;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BeatlesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BeatlesApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
//        Spider.create(new CSDNBlogPageProcessor())//
//                .addUrl("http://blog.csdn.net/?&page=2")//
//                .addPipeline(new DatabasePipeline())//
//                .thread(5)//
//                .run();

        Spider.create(new CSDNNewsPageProcessor())//
                .addUrl("http://news.csdn.net/news/2")//
                .addPipeline(databasePipeline)//
                .thread(5)//
                .run();
    }

    @Autowired
    @Qualifier("databasePipeline")
    private DatabasePipeline databasePipeline;
}
