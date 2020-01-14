package com.ruoyi.api.controller.village;

import com.ruoyi.api.domain.RongApiRes;
import com.ruoyi.api.service.RongApiService;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.utils.DateUtil;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.village.domain.*;
import com.ruoyi.village.service.IVareaService;
import com.ruoyi.village.service.IVillagefamilyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/xiaocun")
@CrossOrigin
@Api(value = "村务模块 - 小村家事")
public class Vxiaocun extends BaseController {
    @Autowired
    private IVillagefamilyService VillagefamilyService;
    @Autowired
    private IVareaService vareaService;

    //小村家事 调用业务逻辑处理接口方法，并封装返回值
    @GetMapping("/vilfamily_all")
    @CrossOrigin
    @ApiOperation(value = "返回小村家事项目")
    public RongApiRes searchProAll (Villagefamily villagefamily)
    {
        return RongApiService.get_list(VillagefamilyService.selectvillagefamilylist(villagefamily));
    }

}
