package com.ws.general.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ws.general.service.ISchoolService;
import com.ws.service.entity.School;
import com.ws.service.mapper.SchoolMapper;
import org.springframework.stereotype.Service;

/**
 * @Author: wangshuo
 * @Date: 2022/3/30 11:37
 */
@Service
public class SchoolServiceImpl extends ServiceImpl<SchoolMapper, School> implements ISchoolService {
}
