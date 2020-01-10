package com.ruoyi.iot.mapper;

import com.ruoyi.iot.domain.Ledlist;

import java.util.List;

/**
 * led显示屏播出信息
 *
 * @author hfz
 * @date 2020-01-01
 */
public interface LedlistMapper {
    public List<Ledlist> selectLedList(Ledlist ledlist);
}
