package com.example.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {
    @Autowired
    private DataSource dataSource;
//    @Autowired
//    private Order order;

    @Test
    void contextLoads() {
    }
    //HikariProxyConnection@1457352442 wrapping com.mysql.cj.jdbc.ConnectionImpl@4d634127
    @Test
    public void getConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
//    @Test
//    public void getlogin() {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        order.login("test02","123456",request.getSession());
//    }
}
