import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Croy Qian
 * @createDate 2021/5/11
 * @Description TODO
 */
public class FastJsonUtils {

    public static void transModelToJSONObject(JSONObject jsonObject, Object object, String dateFormat, String[] fieldNameList) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            /**
             * Arrays.asList使用的arrayList是内部类的arrayList
             * {@link Arrays.ArrayList}
             */
            if (fieldNameList != null && fieldNameList.length != 0 && !Arrays.asList(fieldNameList).contains(fieldName)) {
                continue;
            }
            Method method = null;
            try {
                method = clazz
                        .getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1),
                                null);
                Object value = method.invoke(object);
                if (field.getType() == Date.class) {
                    if(value == null) {
                        jsonObject.put(fieldName, "");
                    } else {
                        jsonObject.put(fieldName, new SimpleDateFormat(dateFormat).format((Date) value));
                    }
                } else {
                    jsonObject.put(fieldName, value);
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        JSONObject jsonObject = new JSONObject();
        User user = new User();
        user.setAge(1);
        user.setDate(null);
        user.setName("nihao");
        transModelToJSONObject(jsonObject, user, "yyyyMMddHHmmss", new String[] {});
        System.out.println(jsonObject.toJSONString());

    }


}
