import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Croy Qian
 */
public class ReplaceIpHost {
    public static String replaceHost(String url){
        String relUrl="";
        if(StringUtils.isEmpty(url)){
            return url;
        }

        String host=getHost(url);
        if(StringUtils.isEmpty(host)){
            System.out.println("提取host:" + host+",返回url:"+url);
            return url;
        }

        String proxyUrl="www.baidu.com";

        if(StringUtils.isEmpty(proxyUrl)){
            System.out.println("未获取到需要替换的url:" + host);
            return url;
        }
        relUrl= url.replace(host, proxyUrl);

        //        if(!"https".equals(relUrl.substring(0,5).toLowerCase())){
        //            relUrl="https"+relUrl.substring(4);//http替换成https
        //        }

        return relUrl;
    }

    private static String getHost(String url){
        if(StringUtils.isEmpty(url)){
            return url;
        }

        String host = "";
        // "(?<=//|)((\\\\w)+\\\\.)+\\\\w+(\\\\:(\\\\w)+)*"
        Pattern p =  Pattern.compile("^(http://|https://)((\\w)+\\.)+\\w+(\\:(\\w)+)*");
        Matcher matcher = p.matcher(url);
        if(matcher.find()){
            host = matcher.group();
        }
        return host;
    }

    public static void main(String[] args) {
        // 原始URL
        String originalUrl = "http://10.34.21.210:19300/statics/2024/07/29/24112000000094149929_b0eb1a10f1594d6db49a67694332b236.pdf";

        // 使用URL类解析原始URL
        try {
            java.net.URL url = new java.net.URL(originalUrl);

            // 获取协议和主机名
            String protocol = url.getProtocol();
            String host = url.getHost();

            // 新的主机名
            String newHost = "10.34.21.210:22";

            // 构建新的URL字符串
            String newUrl = protocol + "://" + newHost + url.getPath();

            // 如果URL中有查询字符串，也需要添加上
            if (url.getQuery() != null) {
                newUrl += "?" + url.getQuery();
            }

            System.out.println("原始URL: " + originalUrl);
            System.out.println("修改后的URL: " + newUrl);

        } catch (java.net.MalformedURLException e) {
            System.err.println("无效的URL格式");
        }
    }

    private String replaceAddrToNewHost(String originalUrl) {
        try {
            java.net.URL url = new java.net.URL(originalUrl);
            // 获取协议和主机名
            String protocol = url.getProtocol();
            String host = url.getHost();
            // 新的主机名
            String newHost = "www.baidu.com";
            if (StringUtils.isNotBlank(newHost)) {
                // 构建新的URL字符串
                String newUrl = protocol + "://" + newHost + url.getPath();
                // 如果URL中有查询字符串，也需要添加上
                if (url.getQuery() != null) {
                    newUrl += "?" + url.getQuery();
                }
//                getLogger().info("邮件推送进行url替换,原始URL:" + originalUrl + ",替换后的URL:" + newUrl);
                return newUrl;
            } else {
                return originalUrl;
            }
        } catch (java.net.MalformedURLException e) {
//            getLogger().error(e.getMessage());
            return originalUrl;
        }
    }
}
