package com.ruoyi.api.controller.village;

import com.ruoyi.api.domain.RongApiRes;
import com.ruoyi.api.service.RongApiService;
import com.ruoyi.broad.domain.Organization;
import com.ruoyi.broad.service.IOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 村务宝典接口 控制层
 * 物联网管理
 * @author hfz
 * @date 2020-01-04
 */
@RestController
@RequestMapping("/api/iotmanagement")
@CrossOrigin
@Api(value = "村务宝典 - 物联网管理API类")
public class Iotmanagement {
    @Autowired
    private IOrganizationService organizationService;

    @GetMapping("/terminalschange")
    @CrossOrigin
    @ApiOperation(value = "应急广播参数修改列表")
    public RongApiRes searchterminalslist(Organization organization)
    {
        return RongApiService.get_list(organizationService.selectOrganizationList(organization));
    }

}
