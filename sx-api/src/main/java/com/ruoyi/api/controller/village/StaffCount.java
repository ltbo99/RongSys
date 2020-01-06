package com.ruoyi.api.controller.village;

import com.ruoyi.common.base.BaseEntity;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.village.domain.Staff;
import com.ruoyi.village.mapper.StaffMapper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.api.domain.RongApiRes;
import com.ruoyi.api.service.RongApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.village.service.IStaffService;
/**
 * 工作人员信息 控制层
 *
 * @author 薛莎莎
 * @date 2019-11-25
 */

@RestController
@RequestMapping("/api/staff")
@CrossOrigin
@Api(value = "工作人员信息")
public class StaffCount extends BaseController{
    @Autowired
    private IStaffService staffService;
    @CrossOrigin
    @GetMapping("/list")
    @ApiOperation(value = "查询工作人员信息列表")
    public RongApiRes list(Staff staff){
        return RongApiService.get_list(staffService.selectStaffList(staff));
    }
}
