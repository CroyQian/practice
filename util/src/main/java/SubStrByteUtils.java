import org.apache.commons.lang3.StringUtils;

/**
 * @author Croy Qian
 * @createDate 2021/7/20
 * @Description TODO
 */
public class SubStrByteUtils {
    /**
     * 按字节长度截取字符串
     */
    public static String substrByte(String str, int len) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        if (lengthByte(str) <= len) {
            return str;
        }
        int b = 0;
        int i = 0;
        for (; i < str.length() && b < len; i++) {
            if (str.charAt(i) > 255) {
                b = b + 2;
            } else {
                b = b + 1;
            }
        }
        return str.substring(0, i);
    }

    /**
     * 计算字符串字节长度
     *
     * @param str
     * @return
     */
    public static int lengthByte(String str) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        int b = 0;
        int len = str.length();
        if (len != 0) {
            for (int i = 0; i < len; i++) {
                if (str.charAt(i) > 255) {
                    b += 2;
                } else {
                    b++;
                }
            }
            return b;
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        String a = "你好";
        System.out.println(a.length());
        System.out.println(lengthByte(a));
        System.out.println(substrByte(a, 2));
        System.out.println(substrByte(a, 4));
    }
}
