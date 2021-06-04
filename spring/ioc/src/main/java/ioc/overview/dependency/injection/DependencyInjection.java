package ioc.overview.dependency.injection;

import ioc.overview.repository.UserRepository;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author Croy Qian
 * @createDate 2021/4/6
 * @Description TODO
 */
public class DependencyInjection {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:dependency-injection.xml");
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:dependency-injection.xml");
        //1.自定义Bean
        UserRepository userRepository = (UserRepository) beanFactory.getBean("userRepository");
        System.out.println(userRepository.getUsers());
        //2.内建依赖（不是springbean）
        System.out.println(userRepository.getBeanFactory());
        System.out.println(userRepository.getBeanFactory() == beanFactory);
        System.out.println(userRepository.getUserObjectFactory().getObject());
        ObjectFactory applicationContextObjectFactory = userRepository.getApplicationContextObjectFactory();
        System.out.println(applicationContextObjectFactory);
        System.out.println(applicationContextObjectFactory.getObject());
        System.out.println(applicationContextObjectFactory.getObject() == beanFactory);
        System.out.println(applicationContextObjectFactory == userRepository.getBeanFactory());
        //3.内建依赖 例Environment等
        Environment environment = beanFactory.getBean(Environment.class);
        System.out.println(environment);
    }
}
