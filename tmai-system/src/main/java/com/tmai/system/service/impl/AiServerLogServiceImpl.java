package com.tmai.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tmai.system.domain.bo.AiServerLogBo;
import com.tmai.system.domain.vo.AiServerLogVo;
import com.tmai.system.domain.AiServerLog;
import com.tmai.system.mapper.AiServerLogMapper;
import com.tmai.system.service.IAiServerLogService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * AI服务调用日志Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-15
 */
@RequiredArgsConstructor
@Service
public class AiServerLogServiceImpl implements IAiServerLogService {

    private final AiServerLogMapper baseMapper;

    /**
     * 查询AI服务调用日志
     */
    @Override
    public AiServerLogVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询AI服务调用日志列表
     */
    @Override
    public TableDataInfo<AiServerLogVo> queryPageList(AiServerLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiServerLog> lqw = buildQueryWrapper(bo);
        Page<AiServerLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询AI服务调用日志列表
     */
    @Override
    public List<AiServerLogVo> queryList(AiServerLogBo bo) {
        LambdaQueryWrapper<AiServerLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AiServerLog> buildQueryWrapper(AiServerLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AiServerLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getServerId() != null, AiServerLog::getServerId, bo.getServerId());
        lqw.eq(StringUtils.isNotBlank(bo.getServerComment()), AiServerLog::getServerComment, bo.getServerComment());
        lqw.eq(StringUtils.isNotBlank(bo.getServerUrl()), AiServerLog::getServerUrl, bo.getServerUrl());
        lqw.eq(bo.getElapsedTime() != null, AiServerLog::getElapsedTime, bo.getElapsedTime());
        lqw.eq(bo.getSuccess() != null, AiServerLog::getSuccess, bo.getSuccess());
        lqw.eq(bo.getCallTime() != null, AiServerLog::getCallTime, bo.getCallTime());
        return lqw;
    }

    /**
     * 新增AI服务调用日志
     */
    @Override
    public Boolean insertByBo(AiServerLogBo bo) {
        AiServerLog add = BeanUtil.toBean(bo, AiServerLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改AI服务调用日志
     */
    @Override
    public Boolean updateByBo(AiServerLogBo bo) {
        AiServerLog update = BeanUtil.toBean(bo, AiServerLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiServerLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除AI服务调用日志
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
