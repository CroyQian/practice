import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author Croy Qian
 * @createDate 2023/3/7
 */
public class GetAuthToken {
    /**
     * 生成签名
     *
     * @param date
     * @param appid
     * @param appKey
     * @param echoStr
     * @return
     */
    private static String getSignature(String date, String appid, String appKey, String echoStr) {
        MessageDigest md;
        StringBuffer sb = new StringBuffer();
        try {
            md = MessageDigest.getInstance("SHA-1");
            md.update(date.getBytes("UTF-8"));
            md.update(appid.getBytes("UTF-8"));
            md.update(appKey.getBytes("UTF-8"));
            md.update(echoStr.getBytes("UTF-8"));
            byte[] result = md.digest();
            for (byte b : result) {
                int i = b & 0xff;
                if (i <= 0xf) {
                    sb.append(0);
                }
                sb.append(Integer.toHexString(i));

            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("请输入get生成token");
        while (true) {
            Scanner scanner = new Scanner(System.in);
            if (scanner.nextLine().equals("get")) {
                String echoStr = UUID.randomUUID().toString();
                System.out.println("uuid=" + echoStr);
                String date = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
                System.out.println("date=" + date);
                System.out.println(
                        "authToken:" + "@" + getSignature(date, "CBS", "123456Ab", echoStr) + "|" + date + "|" + "CBS" + "|"
                                + echoStr);
            } else {
                System.out.println("请重新输入");
            }
        }

    }
}
