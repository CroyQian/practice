package bean.definition;

import ioc.overview.domain.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
@Import(AnnotationBeanDefinition.Config.class)
public class AnnotationBeanDefinition {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //        applicationContext.register(Config.class);
        applicationContext.register(AnnotationBeanDefinition.class);

        registerUserBeanDefinition(applicationContext, "croyUser"); //croyUser=User{id=3, username='croy'}
        registerUserBeanDefinition(applicationContext); //ioc.overview.domain.User#0=User{id=3, username='croy'}

        applicationContext.refresh();
        System.out.println("Config Bean的所有实例" + applicationContext.getBeansOfType(Config.class));
        System.out.println("User Bean的所有实例" + applicationContext.getBeansOfType(User.class));
        applicationContext.close();
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry, String beanName) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 3).addPropertyValue("username", "croy");
        if (StringUtils.hasText(beanName)) {
            beanDefinitionRegistry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
        } else {
            BeanDefinitionReaderUtils
                    .registerWithGeneratedName(beanDefinitionBuilder.getBeanDefinition(), beanDefinitionRegistry);
        }
    }

    public static void registerUserBeanDefinition(BeanDefinitionRegistry beanDefinitionRegistry) {
        registerUserBeanDefinition(beanDefinitionRegistry, null);
    }

    @Component
    public static class Config {
        @Bean
        public User user() {
            User user = new User();
            user.setId(1);
            user.setUsername("croy");
            return user;
        }
    }

}
