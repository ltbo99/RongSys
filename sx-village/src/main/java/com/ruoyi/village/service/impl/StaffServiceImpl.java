package com.ruoyi.village.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.support.Convert;
import com.ruoyi.village.domain.Staff;
import com.ruoyi.village.domain.pubObjApi;
import com.ruoyi.village.mapper.StaffMapper;
import com.ruoyi.village.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 工作员信息表 服务层实现
 *
 * @author 薛莎莎
 * @date 2019-11-25
 */
@Service
public class StaffServiceImpl implements IStaffService{
    @Autowired
    public StaffMapper staffMapper;

    /**
     * 查询工作员信息
     *
     * @param stid 工作员信息ID
     * @return 工作员信息
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public Staff selectStaffById(String stid) {
        return staffMapper.selectStaffById(stid);
    }

    /**
     * 查询工作员信息列表
     *
     * @param staff 工作员信息
     * @return 工作员信息列表
     */
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Staff> selectStaffList(Staff staff) {
        return staffMapper.selectStaffList(staff);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Staff> selectStaffListById(pubObjApi staff) {
        return staffMapper.selectStaffListById(staff);
    }
}
