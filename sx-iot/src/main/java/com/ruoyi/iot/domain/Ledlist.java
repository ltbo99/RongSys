package com.ruoyi.iot.domain;

import com.ruoyi.common.base.BaseEntity;

import java.util.List;

/**
 * led显示屏播出信息表 tb_ledlist
 *
 * @author hfz
 * @date 2020-01-01
 */
public class Ledlist extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer id;
    /** led显示编号 */
    private Integer lsid;
    /** 人员编号 */
    private String userid;
    /** 终端编号 */
    private String tid;
    /** 终端编号集合参数传入 */
    private List<String> tids;
    /** 内容 */
    private String content;
    /** 开始时间 */
    private String startdate;
    /** 结束时间 */
    private String enddate;
    /** 创建时间 */
    private String createdate;
    /** 特效*/
    private String effect;
    /** 颜色 */
    private String color;
    /** 备注说明 */
    private String demo;
    /** 信息状态  */
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLsid() {
        return lsid;
    }

    public void setLsid(Integer lsid) {
        this.lsid = lsid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public List<String> getTids() {
        return tids;
    }

    public void setTids(List<String> tids) {
        this.tids = tids;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Ledlist{" +
                "id=" + id +
                ", lsid=" + lsid +
                ", userid='" + userid + '\'' +
                ", tid='" + tid + '\'' +
                ", content='" + content + '\'' +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", createdate='" + createdate + '\'' +
                ", effect='" + effect + '\'' +
                ", color='" + color + '\'' +
                ", demo='" + demo + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
