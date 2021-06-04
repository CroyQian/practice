import java.util.Date;

/**
 * @author Croy Qian
 * @createDate 2021/5/11
 * @Description TODO
 */
public class User {
    private Integer age;

    private String name;

    private Date date;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
