import org.apache.commons.lang3.StringUtils;

public class MultiDatabaseSupportUtil {

    public enum DataSourceType {
        DATA_TYPE_ORACLE,
        CURR_DATABASE_TYPE;

    }

    public enum FormatPair {
        FORMART_16("yyyyMMddHH24:mi:ss", "%Y%m%d%H:%i:%S"), FORMART_14("yyyyMMddHH24miss", "%Y%m%d%H%i%S"), FORMART_8(
                "yyyyMMdd",
                "%Y%m%d"), FORMART_DATE_6("yyyymm", "%Y%m"), FORMART_10("yyyyMMddHH24", "%Y%m%d%H"), FORMART_10_2(
                        "yyyy-MM-dd", "%Y-%m-%d"), FORMART_6("HH24miss", "%H%i%S"), FORMART_19("yyyy-MM-dd HH24:mi:ss",
                                "%Y-%m-%d %H:%i:%S"), FORMART_19_2("yyyy.MM.dd HH24:mi:ss", "%Y.%m.%d %H:%i:%S"),;

        private FormatPair(String oracle, String mysql) {
            this.oracle = oracle;
            this.mysql = mysql;
        }

        private String oracle;
        private String mysql;

        public String getOracle() {
            return oracle;
        }

        public void setOracle(String oracle) {
            this.oracle = oracle;
        }

        public String getMysql() {
            return mysql;
        }

        public void setMysql(String mysql) {
            this.mysql = mysql;
        }

    }



    /**
     * 获取数据库当前时间
     *
     * @return
     */
    public static String now() {
        String sqlFunc = "sysdate";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "sysdate";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "now()";
        }
        return sqlFunc;
    }

    /**
     * @param days
     * @return 当前时间减days天
     */
    public static String nowSubDays(int days) {
        String sqlFunc = "sysdate-" + days;
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "sysdate-" + days;
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "date_sub(now(),interval " + days + " day)";
        }
        return sqlFunc;
    }

    /**
     * @param days
     * @return 当前时间减days天
     */
    public static String nowSubDays(String days) {
        String sqlFunc = "sysdate-" + days;
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "sysdate-" + days;
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "date_sub(now(),interval " + days + " day)";
        }
        return sqlFunc;
    }

    /**
     * @param days
     * @return 当前时间加days天
     */
    public static String nowAddDays(int days) {
        String sqlFunc = "sysdate+" + days;
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "sysdate+" + days;
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "date_add(now(),interval " + days + " day)";
        }
        return sqlFunc;
    }

    /**
     * 数据库日期格式化string输出
     *
     * @param param
     *            需要转换的字段
     * @param formatPair
     *            格式（在FormatPair中定义）
     * @return
     */
    public static String date2Str(String param, FormatPair formatPair) {
        String sqlFunc = "to_char(" + param + ",'" + formatPair.getOracle() + "')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            // yyyymmddhh24miss
            sqlFunc = "to_char(" + param + ",'" + formatPair.getOracle() + "')";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            // %Y%m%d%H%i%S
            sqlFunc = "DATE_FORMAT(" + param + ",'" + formatPair.getMysql() + "')";
        }
        return sqlFunc;
    }

    /**
     * to_date方法
     *
     * @param param
     *            字段名
     * @param formatPair
     *            日期类型
     * @param daysDelay
     *            延迟天数（可为负）
     * @return
     */
    public static String str2DateForField(String param, FormatPair formatPair, int daysDelay) {
        String sqlFunc = "to_date(" + "'" + param + "','" + formatPair.getOracle() + "')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            // yyyymmddhh24miss
            sqlFunc = "to_date(" + param + ",'" + formatPair.getOracle() + "') +  " + daysDelay;
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            // %Y%m%d%H%i%S
            String sqlDate = "str_to_date(" + param + ",'" + formatPair.getMysql() + "')";
            sqlFunc = " date_add(" + sqlDate + " ,INTERVAL " + daysDelay + " DAY)";
        }
        return sqlFunc;
    }

    /**
     * @description
     * @param param
     *            变量
     * @param formatPair
     *            日期类型
     * @return String
     */
    public static String str2Date(String param, FormatPair formatPair) {
        String sqlFunc = "to_date('" + param + "','" + formatPair.getOracle() + "')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            // yyyymmddhh24miss
            if ("?".equals(param) || param.trim().startsWith(":")) {
                sqlFunc = " to_date(" + param + ",'" + formatPair.getOracle() + "') ";
            } else {
                sqlFunc = " to_date('" + param + "','" + formatPair.getOracle() + "') ";
            }

        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            // %Y%m%d%H%i%S
            if ("?".equals(param) || param.trim().startsWith(":")) {
                sqlFunc = " str_to_date(" + param + ",'" + formatPair.getMysql() + "') ";
            } else {
                sqlFunc = " str_to_date('" + param + "','" + formatPair.getMysql() + "') ";
            }
        }
        return sqlFunc;
    }

    /**
     * to_date方法
     *
     * @param param
     *            变量
     * @param formatPair
     *            日期类型
     * @param daysDelay
     *            延迟天数（可为负）
     * @return
     */
    public static String str2Date(String param, FormatPair formatPair, int daysDelay) {
        String sqlFunc = "to_date(" + "'" + param + "','" + formatPair.getOracle() + "')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            // yyyymmddhh24miss
            if ("?".equals(param) || param.trim().startsWith(":")) {
                sqlFunc = "to_date(" + param + ",'" + formatPair.getOracle() + "') +  " + daysDelay;
            } else {
                sqlFunc = "to_date(" + "'" + param + "','" + formatPair.getOracle() + "') +  " + daysDelay;
            }
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            // %Y%m%d%H%i%S
            if ("?".equals(param) || param.trim().startsWith(":")) {
                String sqlDate = "str_to_date(" + param + ",'" + formatPair.getMysql() + "')";
                sqlFunc = " date_add(" + sqlDate + " ,INTERVAL " + daysDelay + " DAY)";
            } else {
                String sqlDate = "str_to_date(" + "'" + param + "','" + formatPair.getMysql() + "')";
                sqlFunc = " date_add(" + sqlDate + " ,INTERVAL " + daysDelay + " DAY)";
            }

        }
        return sqlFunc;
    }

    /**
     * to_date方法
     *
     * @param param
     *            变量
     * @param formatPair
     *            日期类型
     * @param daysMonth
     *            延迟天数（可为负）
     * @return
     */
    public static String str2DateDelayMonth(String param, FormatPair formatPair, int daysMonth) {
        String sqlFunc = "to_date(" + "'" + param + "','" + formatPair.getOracle() + "')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            // yyyymmddhh24miss
            if ("?".equals(param) || param.trim().startsWith(":")) {
                sqlFunc = "add_months(to_date(" + param + ",'" + formatPair.getOracle() + "') ,  " + daysMonth + ")";
            } else {
                sqlFunc = "add_months(to_date(" + "'" + param + "','" + formatPair.getOracle() + "') ,  " + daysMonth
                        + ")";
            }
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            // %Y%m%d%H%i%S
            if ("?".equals(param) || param.trim().startsWith(":")) {
                String sqlDate = "str_to_date(" + param + ",'" + formatPair.getMysql() + "')";
                sqlFunc = " date_add(" + sqlDate + " ,INTERVAL " + daysMonth + " month)";
            } else {
                String sqlDate = "str_to_date(" + "'" + param + "','" + formatPair.getMysql() + "')";
                sqlFunc = " date_add(" + sqlDate + " ,INTERVAL " + daysMonth + " month)";
            }

        }
        return sqlFunc;
    }

    /**
     * to_date方法
     *
     * @param param
     *            变量
     * @param formatPair
     *            日期类型
     * @param daysDelay
     *            延迟天数（可为负）
     * @return
     */
    public static String str2DateByDysn(String param, FormatPair formatPair, int daysDelay) {
        String sqlFunc = "to_date(" + param + ",'" + formatPair.getOracle() + "')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            // yyyymmddhh24miss
            sqlFunc = "to_date(" + param + ",'" + formatPair.getOracle() + "') + " + daysDelay;
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            // %Y%m%d%H%i%S
            String sqlDate = "str_to_date(" + param + ",'" + formatPair.getMysql() + "')";
            sqlFunc = " date_add(" + sqlDate + " ,INTERVAL " + daysDelay + " DAY)";
        }
        return sqlFunc;
    }

    /**
     * 判空
     *
     * @param param
     * @return
     */
    public static String nvl(String param, String val) {
        String sqlFunc = "nvl(" + param + "," + val + ")";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "nvl(" + param + "," + val + ")";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "ifnull(" + param + "," + val + ")";
        }
        return sqlFunc;
    }

    public static String addDay(String dateCol, int days) {
        String sqlFunc = dateCol + " + " + days;
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = dateCol + " + " + days;
        } else {
            if (dateCol.equalsIgnoreCase("sysdate")) {
                dateCol = "now()";
            }
            sqlFunc = " date_add(" + dateCol + " ,INTERVAL " + days + " DAY)";
        }
        return sqlFunc;
    }

    public static String addMinutes(String dateCol, int minutes) {
        String sqlFunc = dateCol + " + " + minutes;
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = dateCol + " + " + minutes + "/60/24";
        } else {
            if (dateCol.equalsIgnoreCase("sysdate")) {
                dateCol = "now()";
            }
            sqlFunc = " date_add(" + dateCol + " ,INTERVAL " + minutes + " minute)";
        }
        return sqlFunc;
    }

    public static String subDay(String dateCol, int days) {
        String sqlFunc = dateCol + " - " + days;
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = dateCol + " - " + days;
        } else {
            if (dateCol.equalsIgnoreCase("sysdate")) {
                dateCol = "now()";
            }
            sqlFunc = " date_sub(" + dateCol + " ,INTERVAL " + days + " DAY)";
        }
        return sqlFunc;
    }

    /**
     * 获取数据库当月第一天
     *
     * @author lixx
     */
    public static String getMonthFirstDay(String param) {
        String sqlFunc = "trunc(" + param + ",'month')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "trunc(" + param + ",'month')";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "date_add(" + param + ",interval -day(" + param + ")+1 day)";
        }
        return sqlFunc;
    }

    /**
     * 获取数据库给定月份第一天
     *
     * @author lixx
     */
    public static String getMonthFirstDayByMonthStr(String param) {
        String sqlFunc = "trunc(to_date(" + param + ",'yyyymm'),'MONTH')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "trunc(to_date(" + param + ",'yyyymm'),'MONTH')";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "date_add(date_add(last_day(" + param + "),interval 1 day),interval -1 month)";
        }
        return sqlFunc;
    }

    /**
     * 获取数据库给定月份下月最后一天
     */
    public static String getNextMonthLastDayByMonthStr(String param) {
        String sqlFunc = "last_day(to_date(" + param + ",'yyyymm'))+1";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "last_day(to_date(" + param + ",'yyyymm'))+1";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "last_day(date_add(" + param + ",interval 1 month))";
        }
        return sqlFunc;
    }

    /**
     * 获取数据库当月第一天,SQL报错不能用错
     * 改成程序生成，取代数据库函数：DateUtil.getFirstDayOfMonth()
     *
     * @author lixx
     * @see DateUtil#getFirstDayOfMonth()
     */
    @Deprecated
    public static String getMonthFirstDay() {
        String sqlFunc = "trunc(sysdate,'month')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "trunc(sysdate,'month')";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "date_add(curdate(),interval -day(curdate())+1 day)";
        }
        return sqlFunc;
    }

    /**
     * 获取数据库下月第一天
     *
     * @author lixx
     */
    public static String getNextMonthFirstDay() {
        String sqlFunc = "trunc(add_months(sysdate,1),'month')";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "trunc(add_months(sysdate,1),'month')";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "date_add(curdate()-day(curdate())+1,interval 1 month)";
        }
        return sqlFunc;
    }

    public static String limitNum(int cot) {
        String sqlFunc = "rownum<=" + cot;
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "rownum<=" + cot;
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "limit 0," + cot;
        }
        return sqlFunc;
    }

    public static String getDbDate() {
        String sqlFunc = "";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "select sysdate from dual ";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = " select now() ";
        }
        return sqlFunc;
    }

    /**
     * 默认
     *
     * @param format
     *            YYYYMMDDHH24MISS
     * @param diff
     *            0
     * @return
     */
    public static String getDbDate(String format, double diff) {
        if (StringUtils.isEmpty(format)) {
            format = "yyyymmddhh24miss";
        }
        String sqlFunc = "";
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = "SELECT to_char(sysdate";
            if (diff != 0.0) {
                sqlFunc += "+" + diff;
            }
            sqlFunc += ", '" + format + "') FROM dual";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            sqlFunc = " select now() ";
        }
        return sqlFunc;
    }

    //格式化，左边补齐
    public static String lTrim(String colName, String format) {
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            return "trim(to_char(" + colName + ",'" + format + "'))";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            return "LPAD(" + colName + "," + format.length() + ",0)";
        }
        return colName;
    }

    public static String updateMask(String colName, String value) {
        if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_ORACLE, Const.CURR_DATABASE_TYPE)) {
            return "'" + value + "'||substr(" + colName + ",2,15)";
        } else if (StringUtils.equalsIgnoreCase(Const.DATA_TYPE_MYSQL, Const.CURR_DATABASE_TYPE)) {
            return "CONCAT('" + value + "',substr(" + colName + ",2,15))";
        }
        return colName;
    }
}
