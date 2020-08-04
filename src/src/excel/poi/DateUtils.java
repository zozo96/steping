package excel.poi;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * @author : Sonya
 * @date : 2020/7/23 16:14
 */
public class DateUtils {
    public static final FastDateFormat DATETIME_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    public static String getFormatDateTime(java.util.Date date) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        try {
            return DATETIME_FORMAT.format(date);
        } catch (Exception e) {
            return "";
        }
    }
}
