package com.umbrellary;

import com.umbrellary.beatles.*;
import com.umbrellary.daoimpl.ArticleDaoimpl;
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

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class BeatlesApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(BeatlesApplication.class);

    @Autowired
    @Qualifier("databasePipeline")
    private DatabasePipeline databasePipeline;

    @Autowired
    @Qualifier("articleDaoimpl")
    private ArticleDaoimpl articleDaoimpl;

    @Override
    public void run(String... strings) throws Exception {

        new Thread(() -> {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                try {
                    String cmd = br.readLine();
                    String cmdarrary[] = cmd.split(" ");
                    if (cmdarrary != null) {
                        switch (cmdarrary[0]) {
                            case "help":
                                System.out.println("");
                                System.out.println("Usage:");
                                System.out.println("    help -- show this message");
                                System.out.println("    getcount -- ");
                                System.out.println("    getbyid -- ");
                                System.out.println("");
                                break;

                            case "getcount":
                                System.out.println("count is :" + articleDaoimpl.count());
                                break;

                            case "getbyid":
                                System.out.println(cmdarrary[0] + " is :" + articleDaoimpl.findByArticleId(Long.valueOf(cmdarrary[1])).getTitle());
                                break;

                            case "":
                                break;

                            default:
                                System.out.println("error: unknow command :" + cmdarrary[0]);
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.print("spring-boot# ");
            }
        }).start();

        new Thread(() -> {
            switch (strings[0]) {
                case "getweb":
                    Spider.create(new CSDNCloudPageProcessor())
                            .addUrl("http://cloud.csdn.net/cloud/2")
                            .addPipeline(databasePipeline)
                            .thread(5)
                            .run();
                    Spider.create(new CSDNMobilePageProcessor())
                            .addUrl("http://mobile.csdn.net/mobile/2")
                            .addPipeline(databasePipeline)
                            .thread(5)
                            .run();
                    Spider.create(new CSDNSdPageProcessor())
                            .addUrl("http://sd.csdn.net/sd/2")
                            .addPipeline(databasePipeline)
                            .thread(5)
                            .run();
                    Spider.create(new CSDNNewsPageProcessor())
                            .addUrl("http://news.csdn.net/news/2")
                            .addPipeline(databasePipeline)
                            .thread(5)
                            .run();
                    break;

                case "h2server":
                    logger.info("H2 started success!");
                    break;
                default:
                    logger.info("unknown boot args");
            }
        }).start();
    }

    public static void main(String[] args) {
        SpringApplication.run(BeatlesApplication.class, args);
    }
}
