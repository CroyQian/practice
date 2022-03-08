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
        String a = "代表号码：ADSLD2560301424；计费账期：202106；备注：光纤调测费100元/点，月租费75元/点（宽带月租50元/点+天翼看家25元/点）共计51个点，合计6879.82，其中月租费按天数折算";
        System.out.println(a.length());
        System.out.println(lengthByte(a));
        System.out.println(substrByte(a, 200));
        System.out.println(substrByte(a, 160));
    }
}
