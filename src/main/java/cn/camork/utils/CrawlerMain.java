package cn.camork.utils;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;


@Service
public class CrawlerMain{

	private ApplicationContext context;

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-common.xml");
        PipelineFactory springPipelineFactory = (PipelineFactory)context.getBean("springPipelineFactory");
        HttpGetRequest start = new HttpGetRequest("https://book.douban.com/tag/");

        GeccoEngine.create()
                .classpath("cn.camork.crawler.booktype")
                .pipelineFactory(springPipelineFactory)
                .interval(1000)
                .start(start)
                .run();
		context.close();
	}
}
