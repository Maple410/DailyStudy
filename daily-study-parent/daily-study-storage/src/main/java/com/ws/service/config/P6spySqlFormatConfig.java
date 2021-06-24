package com.ws.service.config;


import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.ws.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import java.util.Date;

/**
 * @Author: wangshuo
 * @Date: 2021/6/24 16:00
 */
public class P6spySqlFormatConfig implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtil.formatDateTime(new Date())
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + StringUtils.LF + sql.replaceAll("[\\s]+", StringUtils.SPACE) + ";" : StringUtils.EMPTY;
    }
}
