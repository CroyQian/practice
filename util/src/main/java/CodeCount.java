
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * <p>
 * 计算
 * </p >
 *
 * @author
 * @version V1.0
 * @date 2022-03-07
 */
public class CodeCount {

    static long codeLines = 0;
    static long commentLines = 0;
    static long blankLines = 0;
    static ArrayList<File> fileArray = new ArrayList<File>();


    public static void main(String[] args) throws Exception{

        System.out.println("请输入代码文件夹根目录:");
        System.out.println("举例:D:\\code\\aorder\\");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();  //读取字符串型输入
        System.out.println("输入结果是:"+name);

        //可以统计指定目录下以及其子目录下的所有java文件中代码
        File file = new File(name);

        ArrayList<File> al = getFile(file);
        for (File f : al) {
            if (f.getName().matches(".*\\.java$")) {
                System.out.println(f.getName());
                count(f);
            }
/*            if (f.getName().matches(".*\\.xml$"))
                  count(f);
            if (f.getName().matches(".*\\.sql$"))
                  count(f);
            if (f.getName().matches(".*\\.properties$"))
                 count(f);
            if (f.getName().matches(".*\\.jsp$"))
                count(f);
            if (f.getName().matches(".*\\.js$"))
                count(f);
            if (f.getName().matches(".*\\.css$"))
                count(f);
            if (f.getName().matches(".*\\.vue$"))
                count(f);*/
        }
        System.out.println("代码行数：" + codeLines);
        System.out.println("注释行数：" + commentLines);
//        System.out.println("空白行数： " + blankLines);
        BigDecimal code = new BigDecimal(codeLines);
        BigDecimal comment = new BigDecimal(commentLines);
        final BigDecimal divide = comment.divide(code, 5, RoundingMode.HALF_UP);

        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2); //保留多少位
        String result = percent.format(divide);
        System.out.println("注释率: "+result);
    }

    // 获得目录下的文件和子目录下的文件
    public static ArrayList<File> getFile(File f) {
        File[] ff = f.listFiles();
        if (ff != null) {
            for (File child : ff) {
                if (child.isDirectory()) {
                    getFile(child);
                } else
                    fileArray.add(child);
            }
        } else {
            fileArray.add(f);
        }
        return fileArray;

    }

    // 统计方法
    private static void count(File f) {
        BufferedReader br = null;
        boolean flag = false;
        boolean flag2 = false;
        try {
            br = new BufferedReader(new FileReader(f));
            String line = "";
            while ((line = br.readLine()) != null) {
                line = line.trim(); // 除去注释前的空格
                if (line.matches("^[ ]*$")) { // 匹配空行
                    blankLines++;
                } else if (line.startsWith("//")) {  //这种注释块我不需要算作注释，而且也不需要算为代码行
                    commentLines++;
                }  else if (line.startsWith("/**")||line.startsWith("/*!")) {  //只计算这种形式的注释块
                    commentLines++;
                    flag = true;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else if (flag == true) {
                    commentLines++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else if (line.startsWith("/*")) {  //只计算这种形式的注释块

                    flag2 = true;
                    if (line.endsWith("*/")) {
                        flag2 = false;
                    }
                } else if (flag2 == true) {

                    if (line.endsWith("*/")) {
                        flag2 = false;
                    }
                } else {
                    codeLines++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
