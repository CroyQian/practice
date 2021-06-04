package bean.definition.bean.factory;

import ioc.overview.domain.User;

/**
 * @author Croy Qian
 * @createDate 2021/4/7
 * @Description TODO
 */
public interface UserBeanFactory {
    default User createUser() {
        return User.createUser();
    }
}
