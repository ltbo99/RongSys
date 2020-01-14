package com.ruoyi.iot.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.iot.domain.Ledlist;
import com.ruoyi.iot.mapper.LedlistMapper;
import com.ruoyi.iot.service.ILedlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * led显示屏播出信息
 *
 * @author hfz
 * @date 2020-01-01
 */
@Service
public class LedlistServiceImpl implements ILedlistService {
    @Autowired
    private LedlistMapper ledlistMapper;

    @Override
    @DataSource(value = DataSourceType.SLAVE)
    public List<Ledlist> selectLedList(Ledlist ledlist) {
        return ledlistMapper.selectLedList(ledlist);
    }
}
