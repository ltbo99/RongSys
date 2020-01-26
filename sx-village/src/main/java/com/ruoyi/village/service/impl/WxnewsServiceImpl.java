package com.ruoyi.village.service.impl;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.village.domain.Wxnews;
import com.ruoyi.village.mapper.WxnewsMapper;
import com.ruoyi.village.service.IWxnewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 微信公众号新闻
 *
 * @author hfz
 * @date 2020-1-8
 */
@Service
public class WxnewsServiceImpl implements IWxnewsService {
    @Autowired
    private WxnewsMapper wxnewsmapper;
    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Wxnews> selectWxnewslist(Wxnews wxnews)
    {
        return wxnewsmapper.selectWxnewslist(wxnews);
    }

    @Override
    @DataSource(value = DataSourceType.SXVILLAGE)
    public List<Wxnews> selectWxnewsByAll(Wxnews wxnews)
    {
        return wxnewsmapper.selectWxnewsByAll(wxnews);
    }

}
