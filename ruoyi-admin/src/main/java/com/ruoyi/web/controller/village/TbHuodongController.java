package com.ruoyi.web.controller.village;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtil;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.village.domain.Files;
import com.ruoyi.village.domain.TbHuodong;
import com.ruoyi.village.service.ITbHuodongService;
import com.ruoyi.village.util.bFileUtil1;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 党员活动 信息操作处理
 * 
 * @author zx
 * @date 2019-11-16
 */
@Controller
@RequestMapping("/village/tbHuodong")
public class TbHuodongController extends BaseController
{
    private String prefix = "village/tbHuodong";
	
	@Autowired
	private ITbHuodongService tbHuodongService;
	@Autowired
	private ISysUserService sysUserService;
	@RequiresPermissions("village:tbHuodong:view")
	@GetMapping()
	public String tbHuodong()
	{
	    return prefix + "/tbHuodong";
	}
	
	/**
	 * 查询党员活动列表
	 */
	@RequiresPermissions("village:tbHuodong:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TbHuodong tbHuodong)
	{
		startPage();
        List<TbHuodong> list = tbHuodongService.selectTbHuodongList(tbHuodong);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出党员活动列表
	 */
	@RequiresPermissions("village:tbHuodong:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TbHuodong tbHuodong)
    {
    	List<TbHuodong> list = tbHuodongService.selectTbHuodongList(tbHuodong);
        ExcelUtil<TbHuodong> util = new ExcelUtil<TbHuodong>(TbHuodong.class);
        return util.exportExcel(list, "tbHuodong");
    }
	
	/**
	 * 新增党员活动
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		//从session中获取当前登陆用户的 username、phone、userid
		SysUser currentUser = ShiroUtils.getSysUser();
		String username =  currentUser.getUserName();
		Long userid =  currentUser.getUserId();
		String aid;
		int returnId = new Long(userid).intValue();
		//通过所获取的userid去广播用户表中查询用户所属区域的Aid
		aid = sysUserService.selectAid(returnId);
		//	将aid、fname、uname传至add.html中
		mmap.put("aid", aid);//这里获得的aid是来自ry-》tb_user_admin
		mmap.put("userid", userid);
		mmap.put("uname", username);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存党员活动
	 */
//	@RequiresPermissions("village:tbHuodong:add")
	@Log(title = "党员活动", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	/*这里支持多文件上传*/
	/*这里加入Project project是为了获得html页面form返回来的数据*/
	@ResponseBody
	public AjaxResult addSave(TbHuodong tbHuodong,@RequestParam(value = "files") MultipartFile file[],
							  @RequestParam(value = "filesnum", required = false) String filesnum,
							  @RequestParam(value = "filename", required = false) String fname,
							  @RequestParam(value = "flenth" ,required = false)String flenth, //时长
							  @RequestParam(value = "fsize",required = false) String fsize)
	{
		String year = DateUtil.getYear();

		Date date = new Date();
		SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMMddhhmmss");
		System.out.println(dateFormat.format(date));
		String maxfileid = dateFormat.format(date); //获取文件上传时的时间参数字符串作为文件名，防止储存同名文件
		//文件上传调用工具类
		int i;
		String fileaddress = "";
		int filesnum2=Integer.parseInt(filesnum);
		for(i=0;i<filesnum2;i++)
		{
			//保存文件
			Files g = bFileUtil1.uplodeFile(maxfileid,file[i],fname,flenth,fsize,year);
			System.out.println(g.toString());//在控制台输出文件信息
			fileaddress = fileaddress + g.getAddress() + ";";//通过fileaddress来储存文件地址
		}
		tbHuodong.setHdpic(fileaddress);
		tbHuodongService.insertTbHuodong(tbHuodong);
		//return toAjax(tbHuodongService.insertTbHuodong(tbHuodong));
		return toAjax(1);
	}

	/**
	 * 修改党员活动
	 */
	@GetMapping("/edit/{hdid}")
	public String edit(@PathVariable("hdid") Integer hdid, ModelMap mmap)
	{
		TbHuodong tbHuodong = tbHuodongService.selectTbHuodongById(hdid);
		mmap.put("tbHuodong", tbHuodong);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存党员活动
	 */
	@RequiresPermissions("village:tbHuodong:edit")
	@Log(title = "党员活动", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TbHuodong tbHuodong)
	{		
		return toAjax(tbHuodongService.updateTbHuodong(tbHuodong));
	}
	
	/**
	 * 删除党员活动
	 */
	@RequiresPermissions("village:tbHuodong:remove")
	@Log(title = "党员活动", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tbHuodongService.deleteTbHuodongByIds(ids));
	}



	/**
	 * 打开项目详情页
	 */
	@GetMapping("/detail/{hdid}")
	public String detail(@PathVariable("hdid")Integer hdid,ModelMap mmap)
	{
		mmap.put("listByid",tbHuodongService.selectTbHuodongById(hdid));
		return prefix + "/detail";
	}
}
