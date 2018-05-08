package com.hyb.sia.chapter02.section02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

//RunWith  junit 对spring的支持
//SpringJunit4ClassRunner  在spring-test包中
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class CDPlayerTestWithAnnotationScanConfig {

    @Autowired
    private CompactDisc cd;

    @Test
    public void cdShouldNotBeNull(){
        cd.play();
        assertNotNull(cd);
    }
}
