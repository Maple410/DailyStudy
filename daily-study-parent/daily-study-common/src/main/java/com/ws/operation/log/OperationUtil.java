package com.ws.operation.log;

import com.ws.annotation.Remarks;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: sq
 * @Date: 2021/10/29 11:43
 */
public class OperationUtil {


    /**
     * 对比两个实体之间差异
     *
     * @param nowObject
     * @param historyObject
     * @return
     * @throws Exception
     */
    public static <T> List<ChangeVo> compareDifference(T nowObject, T historyObject) throws Exception {

        List<ChangeVo> changeVos = new ArrayList<>();

        Class<?> modelClass = historyObject.getClass();
        Field[] fields = modelClass.getDeclaredFields();
        for (Field field : fields) {
            Remarks annotation = field.getAnnotation(Remarks.class);
            if (null == annotation) {
                continue;
            }
            field.setAccessible(Boolean.TRUE);
            Object nowValue = field.get(nowObject);
            Object historyValue = field.get(historyObject);
            if (null == nowValue && null == historyValue) {
                continue;
            }
            nowValue = nowValue == null ? "" : nowValue;
            historyValue = historyValue == null ? "" : historyValue;
            if (!nowValue.equals(historyValue)) {
                ChangeVo vo = new ChangeVo();
                //特殊处理时间格式
                if (field.getGenericType().toString().equals("class java.util.Date") && StringUtils.isNotBlank(annotation.dateFormat())) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(annotation.dateFormat());
                    if (StringUtils.isNotEmpty(nowValue.toString())) {
                        nowValue = simpleDateFormat.format(nowValue);
                    }
                    if (StringUtils.isNotEmpty(historyValue.toString())) {
                        historyValue = simpleDateFormat.format(historyValue);
                    }
                }
                vo.setChangeNowValue(nowValue.toString());
                vo.setChangeHistoryValue(historyValue.toString());
                vo.setFieldDescribe(annotation.name());
                changeVos.add(vo);
            }
        }
        return changeVos;
    }

}
