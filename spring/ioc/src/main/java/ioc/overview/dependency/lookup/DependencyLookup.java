package ioc.overview.dependency.lookup;

import ioc.overview.Annotation.Super;
import ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author Croy Qian
 * @createDate 2021/4/6
 * @Description TODO
 */
public class DependencyLookup {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:dependency-lookup.xml");
        lookupInRealTime(beanFactory);
        lookupInLazy(beanFactory);
        lookupByType(beanFactory);
        lookupCollectionByType(beanFactory);
        lookupCollectionByAnnotation(beanFactory);
    }

    private static void lookupInRealTime(BeanFactory beanFactory) {
        User user = (User) beanFactory.getBean("user");
        System.out.println("id实时查找" + user);
    }

    private static void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> objectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = objectFactory.getObject();
        //说是延迟原因时在objectFactory时不会实时查找关联类型的 User，要到getObject时才会查找
        System.out.println("id延迟查找" + user);
    }

    private static void lookupByType(BeanFactory beanFactory) {
        User user = beanFactory.getBean(User.class);
        System.out.println("type实时查找" + user);
    }

    private static void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = listableBeanFactory.getBeansOfType(User.class);
            System.out.println("通过type查找到的user数组" + userMap);
        }
    }

    private static void lookupCollectionByAnnotation(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) beanFactory;
            Map<String, User> userMap = (Map) listableBeanFactory.getBeansWithAnnotation(Super.class);
            System.out.println("通过注解查找到的user数组" + userMap);
        }
    }
}
