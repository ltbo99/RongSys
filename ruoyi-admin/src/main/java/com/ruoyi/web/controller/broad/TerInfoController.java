package com.ruoyi.web.controller.broad;

import com.ruoyi.broad.domain.Organization;
import com.ruoyi.broad.service.IOrganizationService;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.framework.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 终端信息操作处理
 *
 * @author cx
 * @date 2019-09-21
 */
@Controller
@RequestMapping("/broad/terstatusinfo")
public class TerInfoController extends BaseController
{
	private String prefix = "broad/terstatusinfo";

	@Autowired
	private IOrganizationService organizationService;

	@GetMapping()
	public String organization(){
		return prefix + "/list";
	}

	/**
	 * 查询终端信息列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Organization organization){
		startPage();
		List<Organization> list = organizationService.selectOrganizationList1(organization);
		return getDataTable(list);
	}

	@GetMapping("/timeedit")
	public String terinfoedit(){
		return prefix + "/timeedit";
	}

	@GetMapping("/rdsedit")
	public String rdsedit(){
		return prefix + "/rdsedit";
	}

	@GetMapping("/frequencyedit")
	public String frequencyedit(){
		return prefix + "/frequencyedit";
	}

	@GetMapping("/phoneedit")
	public String phoneedit(){
		return prefix + "/phoneedit";
	}

	@GetMapping("/phonedelete")
	public String phonedelete(){
		return prefix + "/phonedelete";
	}

	//批量设置终端交互时间
	@PostMapping("/timeedit")
	@ResponseBody
	public String terinfoedittime(@RequestParam("time")String time,@RequestParam("tids") List<String> tids){
		int rows = organizationService.terinfoedittime(time,tids);
		return "ok";
	}

	//批量设置终端rds
	@PostMapping("/rdsedit")
	@ResponseBody
	public String terinfoeditrds(@RequestParam("time")String rds,@RequestParam("tids") List<String> tids){
		int rows = organizationService.terinfoeditrds(rds,tids);
		return "ok";
	}

	//批量设置终端接收频率
	@PostMapping("/frequencyedit")
	@ResponseBody
	public String terinfoeditfrequency(@RequestParam("time")String frequency,@RequestParam("tids") List<String> tids){
		int rows = organizationService.terinfoeditfrequency(frequency,tids);
		return "ok";
	}

	//批量设置终端授权号码
	@PostMapping("/phoneedit")
	@ResponseBody
	public String terinfoeditphone(@RequestParam("time")String phone,@RequestParam("tids") List<String> tids){
		int rows = organizationService.terinfoeditphone(phone,tids);
		return "ok";
	}

	//批量删除授权号码
	@PostMapping("/phonedelete")
	@ResponseBody
	public String terinfoeditphonedelete(@RequestParam("time")String phone,@RequestParam("tids") List<String> tids){
		int rows = organizationService.terinfoeditphonedelete(phone,tids);
		return "ok";
	}
}

