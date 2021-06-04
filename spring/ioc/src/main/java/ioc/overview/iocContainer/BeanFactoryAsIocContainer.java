package ioc.overview.iocContainer;

import ioc.overview.domain.User;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author Croy Qian
 * @createDate 2021/4/6
 * @Description BeanFactory作为底层容器 DefaultListableBeanFactory
 */
public class BeanFactoryAsIocContainer {
    public static void main(String[] args) {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        int beanCounts = xmlBeanDefinitionReader.loadBeanDefinitions("classpath:dependency-lookup.xml");
        System.out.println("创建bean的数量为" + beanCounts);
        Map<String, User> map = defaultListableBeanFactory.getBeansOfType(User.class);
        System.out.println(map);
        System.out.println();
    }
}
