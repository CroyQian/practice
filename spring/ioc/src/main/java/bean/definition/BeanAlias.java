package bean.definition;

import ioc.overview.domain.User;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
public class BeanAlias {
    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:/bean-definitions-context.xml");
        User croy = beanFactory.getBean("croy", User.class);
        User user = beanFactory.getBean("user", User.class);
        System.out.println("user的别名croy是否与原名user相等" + (croy == user));
    }
}
