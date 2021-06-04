package ioc.overview.iocContainer;

import ioc.overview.domain.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author Croy Qian
 * @createDate 2021/4/6
 * @Description TODO
 */
public class AnnnotionApplicationContextAsIocContainer {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnnotionApplicationContextAsIocContainer.class);
        applicationContext.refresh();
        Map<String, User> map = applicationContext.getBeansOfType(User.class);
        System.out.println(map);
    }

    @Bean
    public User user(){
        User user = new User();
        user.setId(1);
        user.setUsername("croy");
        return user;
    }
}
