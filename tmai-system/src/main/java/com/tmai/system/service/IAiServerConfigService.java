package com.tmai.system.service;

import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.page.TableDataInfo;
import com.tmai.system.domain.bo.AiServerConfigBo;
import com.tmai.system.domain.vo.AiServerConfigVo;

import java.util.Collection;
import java.util.List;

/**
 * AI服务地址配置Service接口
 *
 * @author ruoyi
 * @date 2023-03-12
 */
public interface IAiServerConfigService {

    /**
     * 获取下一个AI服务的示例
     * @param type
     * @return
     */
    AiServerConfigVo getNextServerByType(Long type);

    /**
     * 查询AI服务地址配置
     */
    AiServerConfigVo queryById(Long id);

    /**
     * 查询AI服务地址配置列表
     */
    TableDataInfo<AiServerConfigVo> queryPageList(AiServerConfigBo bo, PageQuery pageQuery);

    /**
     * 查询AI服务地址配置列表
     */
    List<AiServerConfigVo> queryList(AiServerConfigBo bo);

    /**
     * 新增AI服务地址配置
     */
    Boolean insertByBo(AiServerConfigBo bo);

    /**
     * 修改AI服务地址配置
     */
    Boolean updateByBo(AiServerConfigBo bo);

    /**
     * 校验并批量删除AI服务地址配置信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
