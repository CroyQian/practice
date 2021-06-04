package bean.definition;

import bean.definition.bean.factory.DefaultUserBeanFactory;
import bean.definition.bean.factory.UserBeanFactory;
import ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.serviceloader.ServiceLoaderFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
public class SpecialBeanInstantiation {
    public static void main(String[] args) {
        //        demoTestServiceLoad();
//        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/bean-instantiation.xml");
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/bean-instantiation.xml");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        ServiceLoader serviceLoader = beanFactory.getBean("userFactoryServiceLoader", ServiceLoader.class);
        displayServiceLoad(serviceLoader);

        UserBeanFactory userBeanFactory  = beanFactory.createBean(DefaultUserBeanFactory.class);
        System.out.println(userBeanFactory);
    }

    public static void demoTestServiceLoad() {
        ServiceLoader<UserBeanFactory> serviceLoader = ServiceLoader.load(UserBeanFactory.class);
        displayServiceLoad(serviceLoader);
    }

    public static void displayServiceLoad(ServiceLoader<?> serviceLoader) {
        Iterator<?> iterator = serviceLoader.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
