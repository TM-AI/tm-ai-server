package com.tmai.app.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
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
import com.tmai.system.domain.bo.AiParamConfigBo;
import com.tmai.system.domain.vo.AiParamConfigVo;
import com.tmai.system.service.IAiParamConfigService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * AI服务接口参数配置
 *
 * @author ruoyi
 * @date 2023-03-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/paramConfig")
public class AiParamConfigController extends BaseController {

    private final IAiParamConfigService iAiParamConfigService;

    /**
     * 查询AI服务接口参数配置列表
     */
    @SaCheckPermission("system:paramConfig:list")
    @GetMapping("/list")
    public TableDataInfo<AiParamConfigVo> list(AiParamConfigBo bo, PageQuery pageQuery) {
        return iAiParamConfigService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出AI服务接口参数配置列表
     */
    @SaCheckPermission("system:paramConfig:export")
    @Log(title = "AI服务接口参数配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiParamConfigBo bo, HttpServletResponse response) {
        List<AiParamConfigVo> list = iAiParamConfigService.queryList(bo);
        ExcelUtil.exportExcel(list, "AI服务接口参数配置", AiParamConfigVo.class, response);
    }

    /**
     * 获取AI服务接口参数配置详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:paramConfig:query")
    @GetMapping("/{id}")
    public R<AiParamConfigVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iAiParamConfigService.queryById(id));
    }

    /**
     * 新增AI服务接口参数配置
     */
    @SaCheckPermission("system:paramConfig:add")
    @Log(title = "AI服务接口参数配置", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiParamConfigBo bo) {
        return toAjax(iAiParamConfigService.insertByBo(bo));
    }

    /**
     * 修改AI服务接口参数配置
     */
    @SaCheckPermission("system:paramConfig:edit")
    @Log(title = "AI服务接口参数配置", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiParamConfigBo bo) {
        return toAjax(iAiParamConfigService.updateByBo(bo));
    }

    /**
     * 删除AI服务接口参数配置
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:paramConfig:remove")
    @Log(title = "AI服务接口参数配置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iAiParamConfigService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
