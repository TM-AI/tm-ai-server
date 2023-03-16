package com.tmai.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.tmai.system.domain.AiServerConfig;
import com.tmai.system.domain.bo.AiServerConfigBo;
import com.tmai.system.domain.vo.AiServerConfigVo;
import com.tmai.system.mapper.AiServerConfigMapper;
import com.tmai.system.service.IAiServerConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * AI服务地址配置Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-12
 */
@RequiredArgsConstructor
@Service
public class AiServerConfigServiceImpl implements IAiServerConfigService {

    private final AiServerConfigMapper baseMapper;

    @Override
    public AiServerConfigVo getNextServerByType(Long type) {
        //todo 改为用redis实现
        LambdaQueryWrapper<AiServerConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(AiServerConfig::getType,type);
        lqw.eq(AiServerConfig::getUsable,true);
        List<AiServerConfigVo> servers = baseMapper.selectVoList(lqw);


        //AiServerConfigBo param = new AiServerConfigBo();
        //param.setType(type);
        //param.setUsable(true);
        //List<AiServerConfigVo> servers = queryList(param);

        AiServerConfigVo server = servers.get(new Random().nextInt(servers.size()));
        return server;
    }

    /**
     * 查询AI服务地址配置
     */
    @Override
    public AiServerConfigVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询AI服务地址配置列表
     */
    @Override
    public TableDataInfo<AiServerConfigVo> queryPageList(AiServerConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiServerConfig> lqw = buildQueryWrapper(bo);
        Page<AiServerConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询AI服务地址配置列表
     */
    @Override
    public List<AiServerConfigVo> queryList(AiServerConfigBo bo) {
        LambdaQueryWrapper<AiServerConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AiServerConfig> buildQueryWrapper(AiServerConfigBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AiServerConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getHost()), AiServerConfig::getHost, bo.getHost());
        lqw.eq(bo.getType() != null, AiServerConfig::getType, bo.getType());
        return lqw;
    }

    /**
     * 新增AI服务地址配置
     */
    @Override
    public Boolean insertByBo(AiServerConfigBo bo) {
        AiServerConfig add = BeanUtil.toBean(bo, AiServerConfig.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改AI服务地址配置
     */
    @Override
    public Boolean updateByBo(AiServerConfigBo bo) {
        AiServerConfig update = BeanUtil.toBean(bo, AiServerConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiServerConfig entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除AI服务地址配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
