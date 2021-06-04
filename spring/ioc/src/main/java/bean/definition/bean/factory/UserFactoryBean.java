package bean.definition.bean.factory;

import ioc.overview.domain.User;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
public class UserFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
