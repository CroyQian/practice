package bean.definition.bean.factory;

import ioc.overview.domain.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
@Configuration
public class BeanInitialization {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitialization.class);
        applicationContext.refresh();
        System.out.println("spring应用上下文启动...");//看这个打印的位置可以看出延迟初始化和非延迟初始化的区别
        UserBeanFactory userBeanFactory = applicationContext.getBean(UserBeanFactory.class);
        System.out.println(userBeanFactory);
        System.out.println("spring应用上下文准备关闭...");
        applicationContext.close();
        System.out.println("spring应用上下文已经关闭...");
    }

    @Bean(initMethod = "initByInitMethod", destroyMethod = "destoryByDestoryMethod")
    @Lazy
    public UserBeanFactory userBeanFactory() {
        return new DefaultUserBeanFactory();
    }
}
