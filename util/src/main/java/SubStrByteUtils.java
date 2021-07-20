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
        String a = "计费周期:201811;号码:18926155229;其中预付金:100.00元;付款流水号:170000021550625;合同总金额210427元，其中设备终端费124171元(一次付清），通信费86256元，通信费分三期付，每期付28752元，本次为第二期付款";
        System.out.println(a.length());
        System.out.println(lengthByte(a));
        System.out.println(substrByte(a, 200));
        System.out.println(substrByte(a, 130));
    }
}
