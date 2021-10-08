package com.ws.test;

import com.ws.annotation.Remarks;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: wangshuo
 * @Date: 2021/9/28 15:19
 * <p>
 * 对比两个实体 属性变化的记录
 */

public class TestCompare {


    public static <T> List<String> compareDifference(T historyObject, T currentObject) throws Exception {

        List<String> changeList = new ArrayList<>();
        Class<?> classObject = historyObject.getClass();
        Field[] declaredFields = classObject.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Remarks annotation = field.getAnnotation(Remarks.class);
            if (null == annotation) {
                continue;
            }
            Object historyValue = field.get(historyObject);
            Object nowValue = field.get(currentObject);
            if (null == historyValue && null == nowValue) {
                continue;
            }
            historyValue = historyValue == null ? "" : historyValue;
            nowValue = nowValue == null ? "" : nowValue;
            if (!historyValue.equals(nowValue)) {
               if(annotation.dateTimeFlag()){
                   SimpleDateFormat simpleDateFormat = new SimpleDateFormat(annotation.dateFormat());
                   if (StringUtils.isNotEmpty(nowValue.toString())) {
                       nowValue = simpleDateFormat.format(nowValue);
                   }
                   if (StringUtils.isNotEmpty(historyValue.toString())) {
                       historyValue = simpleDateFormat.format(historyValue);
                   }
               }
                changeList.add(annotation.name().concat("从 ").concat(historyValue.toString()).concat(" 变更为：").concat(nowValue.toString()));
            }
        }
        return changeList;
    }



}
