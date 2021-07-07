import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Croy Qian
 * @createDate 2021/6/10
 * @Description 字符替换测试
 */
public class StringReplaceUtils {
    public static String filter(String str) {
        String regEx = "[`~!@#$%^&*()\\-+={}':;,\\[\\].<>/?￥%…（）_+|【】‘；：”“’。，、？！？a-zA-Z0-9 \\s]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static void main(String[] args) {
        String a = "“红船”《武汉1927》演出票";
        a = a.replaceAll("\\+\\!","");
        System.out.println(a);
        String reg = "好";
        Pattern p = Pattern.compile(reg);
        System.out.println(p.matcher(a).toString());
        System.out.println(filter(a));
    }
}
