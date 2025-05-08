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
    private static String getSignature(String date, String appid, String appKey, String echoStr, String mdType) {
        MessageDigest md;
        StringBuffer sb = new StringBuffer();
        try {
            md = MessageDigest.getInstance(mdType);
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
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入appId(测试环境默认CBS)");
        String appId = scanner.nextLine();
        System.out.println("请输入appKey(测试环境默认123456Ab)");
        String appKey = scanner.nextLine();
        System.out.println("请选择生成方式(1:SHA-1,2:SHA-256)");
        String mdType = "";
        while (true) {
            String type = scanner.nextLine();
            if (type.equals("1")) {
                mdType = "SHA-1";
                break;
            } else if (type.equals("2")) {
                mdType = "SHA-256";
                break;
            } else {
                System.out.println("请选择生成方式(1:SHA-1,2:SHA-256)");
            }
        }
        System.out.println("请输入g生成token");
        while (true) {
            if (scanner.nextLine().equals("g")) {
                String echoStr = UUID.randomUUID().toString();
                System.out.println("uuid=" + echoStr);
                String date = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
                System.out.println("date=" + date);
                System.out.println("authToken:" + "@" + getSignature(date, appId, appKey, echoStr, mdType) + "|" + date + "|" + appId + "|" + echoStr);
            } else {
                System.out.println("请重新输入");
            }
        }

    }
}
