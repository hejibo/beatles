package com.umbrellary;

import com.umbrellary.beatles.*;
import com.umbrellary.service.DatabasePipeline;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import us.codecraft.webmagic.Spider;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class BeatlesApplication {

    private static final Logger logger = LoggerFactory.getLogger(BeatlesApplication.class);

    @Bean
    public CommandLineRunner beatles(DatabasePipeline databasePipeline) {
        return (args) -> {
            new Thread(() -> {
                switch (args[0]) {
                    case "getweb":

                        Spider.create(new CSDNCloudPageProcessor())
                                .addUrl("http://mobile.csdn.net/mobile/2")
                                .addPipeline(databasePipeline)
                                .thread(100)
                                .run();
                        Spider.create(new CSDNCloudPageProcessor())
                                .addUrl("http://sd.csdn.net/sd/2")
                                .addPipeline(databasePipeline)
                                .thread(100)
                                .run();
                        Spider.create(new CSDNCloudPageProcessor())
                                .addUrl("http://cloud.csdn.net/cloud/2")
                                .addPipeline(databasePipeline)
                                .thread(100)
                                .run();
                        Spider.create(new CSDNCloudPageProcessor())
                                .addUrl("http://news.csdn.net/news/2")
                                .addPipeline(databasePipeline)
                                .thread(100)
                                .run();
                        break;

                    case "h2server":
                        logger.info("H2 started success!");
                        break;
                    default:
                        logger.info("unknown boot args");
                }
            }).start();

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
                                    System.out.println("count is :" + databasePipeline.getCount());
                                    break;

                                case "getbyid":
                                    System.out.println(cmdarrary[0] + " is :" + databasePipeline.getbyid(Long.valueOf(cmdarrary[1])));
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
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(BeatlesApplication.class, args);
    }
}
