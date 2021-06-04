package bean.definition;

import ioc.overview.domain.User;
import org.springframework.beans.BeanMetadataAttribute;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
public class BeanDefinitionCreator {
    public static void main(String[] args) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder.addPropertyValue("id", 2);
        beanDefinitionBuilder.addPropertyValue("username", "croy");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();

        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
//        mutablePropertyValues.addPropertyValue("id", 3);
//        mutablePropertyValues.addPropertyValue("username", "croy");
        mutablePropertyValues.add("id", 3).add("username", "croy");
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);

    }
}
