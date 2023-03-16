package com.tmai.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.tmai.system.domain.bo.AiServerLogBo;
import com.tmai.system.domain.vo.AiServerLogVo;

import java.util.Collection;
import java.util.List;

/**
 * AI服务调用日志Service接口
 *
 * @author ruoyi
 * @date 2023-03-15
 */
public interface IAiServerLogService {

    /**
     * 查询AI服务调用日志
     */
    AiServerLogVo queryById(Long id);

    /**
     * 查询AI服务调用日志列表
     */
    TableDataInfo<AiServerLogVo> queryPageList(AiServerLogBo bo, PageQuery pageQuery);

    /**
     * 查询AI服务调用日志列表
     */
    List<AiServerLogVo> queryList(AiServerLogBo bo);

    /**
     * 新增AI服务调用日志
     */
    Boolean insertByBo(AiServerLogBo bo);

    /**
     * 修改AI服务调用日志
     */
    Boolean updateByBo(AiServerLogBo bo);

    /**
     * 校验并批量删除AI服务调用日志信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
