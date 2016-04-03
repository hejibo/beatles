package com.umbrellary;

import com.umbrellary.beatles.CSDNBlogPageProcessor;
import com.umbrellary.beatles.CSDNNewsPageProcessor;
import com.umbrellary.beatles.DatabasePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(BeatlesApplication.class);

    private static String[] args;

    public static String[] getArgs() {
        return args;
    }

    public static void setArgs(String[] args) {
        BeatlesApplication.args = args;
    }

    public static void main(String[] args) {
        setArgs(args);
        SpringApplication.run(BeatlesApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        if (getArgs()[0].equals("web1")) {
            Spider.create(new CSDNNewsPageProcessor())
                    .addUrl("http://news.csdn.net/news/2")
                    .addPipeline(databasePipeline)
                    .thread(5)
                    .run();
        } else if (getArgs()[0].equals("web2")) {
            Spider.create(new CSDNBlogPageProcessor())
                    .addUrl("http://blog.csdn.net/?&page=2")
                    .addPipeline(new DatabasePipeline())
                    .thread(5)
                    .run();

        } else if (getArgs()[0].equals("h2server")) {
            logger.info("H2 started success!");
        }
    }

    @Autowired
    @Qualifier("databasePipeline")
    private DatabasePipeline databasePipeline;
}
