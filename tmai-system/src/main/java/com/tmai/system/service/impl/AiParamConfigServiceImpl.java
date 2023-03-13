package com.tmai.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
import com.tmai.system.domain.AiParamConfig;
import com.tmai.system.domain.bo.AiParamConfigBo;
import com.tmai.system.domain.vo.AiParamConfigVo;
import com.tmai.system.mapper.AiParamConfigMapper;
import com.tmai.system.service.IAiParamConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * AI服务接口参数配置Service业务层处理
 *
 * @author ruoyi
 * @date 2023-03-12
 */
@RequiredArgsConstructor
@Service
public class AiParamConfigServiceImpl implements IAiParamConfigService {

    private final AiParamConfigMapper baseMapper;

    @Override
    public AiParamConfigVo queryByCode(String code) {
        LambdaQueryWrapper<AiParamConfig> lqw = Wrappers.lambdaQuery();
        //QueryWrapper<AiParamConfigVo>().lambda()
        lqw.eq(AiParamConfig::getCode, code);
        return baseMapper.selectVoOne(lqw);
    }

    /**
     * 查询AI服务接口参数配置
     */
    @Override
    public AiParamConfigVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    //@Override
    //public TableDataInfo<AiParamConfigVo> queryPageList(AiParamConfigBo bo, PageQuery pageQuery) {
    //    return null;
    //}

    /**
     * 查询AI服务接口参数配置列表
     */
    @Override
    public TableDataInfo<AiParamConfigVo> queryPageList(AiParamConfigBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiParamConfig> lqw = buildQueryWrapper(bo);
        Page<AiParamConfigVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询AI服务接口参数配置列表
     */
    @Override
    public List<AiParamConfigVo> queryList(AiParamConfigBo bo) {
        LambdaQueryWrapper<AiParamConfig> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AiParamConfig> buildQueryWrapper(AiParamConfigBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AiParamConfig> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), AiParamConfig::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getParamStr()), AiParamConfig::getParamStr, bo.getParamStr());
        return lqw;
    }

    /**
     * 新增AI服务接口参数配置
     */
    @Override
    public Boolean insertByBo(AiParamConfigBo bo) {
        AiParamConfig add = BeanUtil.toBean(bo, AiParamConfig.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改AI服务接口参数配置
     */
    @Override
    public Boolean updateByBo(AiParamConfigBo bo) {
        AiParamConfig update = BeanUtil.toBean(bo, AiParamConfig.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AiParamConfig entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除AI服务接口参数配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
