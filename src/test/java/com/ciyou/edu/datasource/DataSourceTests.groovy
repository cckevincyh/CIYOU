package com.ciyou.edu.datasource

import com.ciyou.edu.app.App
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
/**
 * @Author C.
 * @Date 2018-02-02 11:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class DataSourceTests {

    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void testDataSource() throws Exception {
        // 获取配置的数据源
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        // 查看配置数据源信息
        System.out.println(dataSource.getClass().getName());
    }

}
