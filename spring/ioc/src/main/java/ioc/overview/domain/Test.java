package ioc.overview.domain;

import ioc.overview.Annotation.Super;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;

/**
 * @author Croy Qian
 * @createDate 2021/5/6
 * @Description TODO
 */
public class Test {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            NoSuchMethodException {
        User user = new User();
        user.setId(1);
        user.setUsername("a");
        System.out.println(user);
        System.out.println("============================");
        BeanCopier beanCopier = BeanCopier.create(User.class, User.class, false);
        User user2 = new User();
        beanCopier.copy(user, user2, null);
        System.out.println(user2);
        System.out.println("============================");
//        SuperUser user1 = SuperUser.class.newInstance();
        Super annotation = SuperUser.class.getAnnotation(Super.class);
        Constructor<?>[] declaredConstructors = SuperUser.class.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor.getName());
            System.out.println(declaredConstructor.getParameterTypes());
        }
        AccessibleObject.setAccessible(declaredConstructors, true);
        SuperUser user1 = SuperUser.class.newInstance();
    }
}
