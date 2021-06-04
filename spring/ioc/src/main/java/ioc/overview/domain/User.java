package ioc.overview.domain;

/**
 * @author Croy Qian
 * @createDate 2021/4/6
 * @Description TODO
 */
public class User {

    private Integer id;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + '}';
    }

    public static User createUser() {
        User user = new User();
        user.setId(4);
        user.setUsername("croy");
        return user;
    }
}
