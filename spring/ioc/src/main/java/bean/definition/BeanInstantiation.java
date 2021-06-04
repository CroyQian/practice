package bean.definition;

import bean.definition.bean.factory.UserFactoryBean;
import ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
public class BeanInstantiation {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/bean-instantiation.xml");
        //1.通过静态方法实例化
        User userByStaticMethod = beanFactory.getBean("userByStaticMethod", User.class);
        System.out.println(userByStaticMethod);
        //2.通过工厂方法
        User userByUserBeanFactory = beanFactory.getBean("userByUserBeanFactory", User.class);
        System.out.println(userByUserBeanFactory);
        //3.通过FactoryBean
        User userByFactoryBean = beanFactory.getBean("userFactoryBean", User.class);
        System.out.println(userByFactoryBean);
    }
}
