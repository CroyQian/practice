package bean.definition.bean.factory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
public class DefaultUserBeanFactory implements UserBeanFactory, InitializingBean, DisposableBean {
    @PostConstruct
    public void initByPostConstruct() {
        System.out.println("@PostConstruct的初始化方法：initByPostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean#afterPropertiesSet方法");
    }

    public void initByInitMethod() {
        System.out.println("InitMethod方法：initByInitMethod");
    }

    @PreDestroy
    public void destroyByPreDestroy() {
        System.out.println("@PreDestroy的destroyByPreDestroy方法：destroyByPreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean的destroy方法：destroy");
    }

    public void destoryByDestoryMethod() {
        System.out.println("destroyMethod方法：destoryByDestoryMethod");
    }
}
