package com.hyb.sia.chapter02.section02;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * spring的bean配置项
 */
@Configuration
public class CDPlayerBeanConfig {

    @Bean //默认方法的名字作为bean的id
    // 也可以指定id
    // @Bean(name = "peppers")
    public CompactDisc sgtPeppers() {
        System.out.println("sgtPeppers in ");
        return new SgtPeppers();
    }

    @Bean
    public CompactDisc randomDiscs() {
        int choice = (int) Math.floor(Math.random() * 4);
        if (choice == 0) {
            return new SgtPeppers();
        } else if (choice == 1) {
            return new WhiteAlbum();
        } else {
            return new HardDaysNight();
        }
    }

    @Bean
    //有两个CompactDisc 的Bean ，不知道自动加载哪一个，因此需要使用Qualifier指定
    public CDPlayer cdPlayer(@Qualifier("randomDiscs") CompactDisc compactDisc) {
        return new CDPlayer(compactDisc);
    }

    //当声明的Bean中有依赖其他的Bean
    public CDPlayer cdPlayer() {
        return new CDPlayer(sgtPeppers());
    }

//    @Bean
//    //有两个CompactDisc 的Bean ，不知道自动加载哪一个，因此需要使用Qualifier指定
//    public CDPlayer cdPlayer(@Qualifier("randomDiscs") CompactDisc compactDisc) {
//        return new CDPlayer(compactDisc);
//    }

    /**
     * 测试多次获取bean的时候，指定的Bean方法是否多次调用
     * 只调用一次，应该是缓存到spring内部了，不再调用Bean指定的方法
     * output:
     * sgtPeppers in
     * Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles
     * Playing Sgt. Pepper's Lonely Hearts Club Band by The Beatles
     *
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(CDPlayerBeanConfig.class);

        //[org.springframework.context.annotation.internalConfigurationAnnotationProcessor,
        // org.springframework.context.annotation.internalAutowiredAnnotationProcessor,
        // org.springframework.context.annotation.internalRequiredAnnotationProcessor,
        // org.springframework.context.annotation.internalCommonAnnotationProcessor,
        // org.springframework.context.event.internalEventListenerProcessor,
        // org.springframework.context.event.internalEventListenerFactory,
        // CDPlayerBeanConfig,
        // org.springframework.context.annotation.ConfigurationClassPostProcessor.importAwareProcessor,
        // org.springframework.context.annotation.ConfigurationClassPostProcessor.enhancedConfigurationProcessor,
        // sgtPeppers, randomDiscs, cdPlayer]
        // 注册的两个cdPlayer被覆盖了一个，覆盖的策略不清楚？？？？
        // 可以将其中一个指定另一个别名，就可以分别使用两个方法生成的Bean
        System.out.println(Arrays.toString(applicationContext.getBeanDefinitionNames()));

        CDPlayer cdPlayer = applicationContext.getBean("cdPlayer", CDPlayer.class);
        cdPlayer.play();
        CDPlayer cdPlayer1 = applicationContext.getBean("cdPlayer", CDPlayer.class);
        cdPlayer1.play();
    }
}
