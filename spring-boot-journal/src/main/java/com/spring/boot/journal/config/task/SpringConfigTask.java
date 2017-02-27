package com.spring.boot.journal.config.task;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan({ "com.spring.boot.journal.task" })
public class SpringConfigTask {

}
