package org.object.string;

import java.util.regex.Pattern;

/**
 * @author yangCHong
 */
public class StringUtil {
    /**
     * 字符串 is
     */
    public static final String IS = "is";
    /**
     * 下划线字符
     */
    public static final char UNDERLINE = '_';
    /**
     * MP 内定义的 SQL 占位符表达式，匹配诸如 {0},{1},{2} ... 的形式
     */
    public final static Pattern MP_SQL_PLACE_HOLDER = Pattern.compile("[{](?<idx>\\d+)}");

    /**
     * 是否为大写命名
     */
    private static final Pattern CAPITAL_MODE = Pattern.compile("^[0-9A-Z/_]+$");

    /**
     * 字符串去除空白内容
     *
     * <ul> <li>'"<>&*+=#-; sql注入黑名单</li> <li>\n 回车</li> <li>\t 水平制表符</li> <li>\s 空格</li> <li>\r 换行</li> </ul>
     */
    private static final Pattern REPLACE_BLANK = Pattern.compile("'|\"|\\<|\\>|&|\\*|\\+|=|#|-|;|\\s*|\t|\r|\n");

    /**
     * 判断字符串中是否全是空白字符
     *
     * @param cs 需要判断的字符串
     * @return 如果字符串序列是 null 或者全是空白，返回 true
     */
    public static boolean isBlank(CharSequence cs) {
        if (cs != null) {
            int length = cs.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 对象转为字符串去除左右空格
     *
     * @param o 带转换对象
     * @return 字符串去除左右空格
     */
    public static String toStringTrim(Object o) {
        return String.valueOf(o).trim();
    }

    /**
     * @param cs 参数
     * @return result
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !isBlank(cs);
    }

    /**
     * @param cs 参数
     * @return result
     */
    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    /**
     * @param cs 参数
     * @return result
     */
    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * 判断字符串是不是驼峰命名 包含 '_' 不算 首字母大写的不算
     * @param str 字符串
     * @return 结果
     */
    public static boolean isCamel(String str) {
        return Character.isLowerCase(str.charAt(0)) && !str.contains(StringPool.UNDERSCORE);
    }
}
