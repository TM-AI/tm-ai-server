package com.ruoyi.web.controller.tmai;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.tmai.system.domain.bo.AiServerConfigBo;
import com.tmai.system.domain.vo.AiServerConfigVo;
import com.tmai.system.service.IAiServerConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * AI服务地址配置
 *
 * @author ruoyi
 * @date 2023-03-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/serverConfig")
public class AiServerConfigController extends BaseController {

    private final IAiServerConfigService iAiServerConfigService;

    /**
     * 查询AI服务地址配置列表
     */
    //@SaCheckPermission("system:serverConfig:list")
    @GetMapping("/list")
    public TableDataInfo<AiServerConfigVo> list(AiServerConfigBo bo, PageQuery pageQuery) {
        return iAiServerConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出AI服务地址配置列表
     */
    //@SaCheckPermission("system:serverConfig:export")
    @Log(title = "AI服务地址配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiServerConfigBo bo, HttpServletResponse response) {
        List<AiServerConfigVo> list = iAiServerConfigService.queryList(bo);
        ExcelUtil.exportExcel(list, "AI服务地址配置", AiServerConfigVo.class, response);
    }

    /**
     * 获取AI服务地址配置详细信息
     *
     * @param id 主键
     */
    //@SaCheckPermission("system:serverConfig:query")
    @GetMapping("/{id}")
    public R<AiServerConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iAiServerConfigService.queryById(id));
    }

    /**
     * 新增AI服务地址配置
     */
    //@SaCheckPermission("system:serverConfig:add")
    @Log(title = "AI服务地址配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiServerConfigBo bo) {
        return toAjax(iAiServerConfigService.insertByBo(bo));
    }

    /**
     * 修改AI服务地址配置
     */
    //@SaCheckPermission("system:serverConfig:edit")
    @Log(title = "AI服务地址配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiServerConfigBo bo) {
        return toAjax(iAiServerConfigService.updateByBo(bo));
    }

    /**
     * 删除AI服务地址配置
     *
     * @param ids 主键串
     */
    //@SaCheckPermission("system:serverConfig:remove")
    @Log(title = "AI服务地址配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iAiServerConfigService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
