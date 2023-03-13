package com.tmai.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.tmai.system.domain.bo.AiParamConfigBo;
import com.tmai.system.domain.vo.AiParamConfigVo;

import java.util.Collection;
import java.util.List;

/**
 * AI服务接口参数配置Service接口
 *
 * @author ruoyi
 * @date 2023-03-12
 */
public interface IAiParamConfigService {

    /**
     * 根据code查询
     */
    AiParamConfigVo queryByCode(String code);

    /**
     * 查询AI服务接口参数配置
     */
    AiParamConfigVo queryById(Long id);

    /**
     * 查询AI服务接口参数配置列表
     */
    TableDataInfo<AiParamConfigVo> queryPageList(AiParamConfigBo bo, PageQuery pageQuery);

    /**
     * 查询AI服务接口参数配置列表
     */
    List<AiParamConfigVo> queryList(AiParamConfigBo bo);

    /**
     * 新增AI服务接口参数配置
     */
    Boolean insertByBo(AiParamConfigBo bo);

    /**
     * 修改AI服务接口参数配置
     */
    Boolean updateByBo(AiParamConfigBo bo);

    /**
     * 校验并批量删除AI服务接口参数配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
