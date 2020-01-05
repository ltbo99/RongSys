package com.ruoyi.iot.service;

import com.ruoyi.iot.domain.Ledlist;

import java.util.List;

/**
 * led显示屏播出信息表 tb_ledlist
 *
 * @author hfz
 * @date 2020-01-01
 */
public interface ILedlistService {
    public List<Ledlist> selectLedList(Ledlist ledlist);
}
