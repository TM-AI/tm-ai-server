package com.ruoyi.web.controller.tmai;

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
import com.tmai.system.domain.bo.AiServerLogBo;
import com.tmai.system.domain.vo.AiServerLogVo;
import com.tmai.system.service.IAiServerLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * AI服务调用日志
 *
 * @author ruoyi
 * @date 2023-03-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/serverLog")
public class AiServerLogController extends BaseController {

    private final IAiServerLogService iAiServerLogService;

    /**
     * 查询AI服务调用日志列表
     */
    @SaCheckPermission("system:serverLog:list")
    @GetMapping("/list")
    public TableDataInfo<AiServerLogVo> list(AiServerLogBo bo, PageQuery pageQuery) {
        return iAiServerLogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出AI服务调用日志列表
     */
    @SaCheckPermission("system:serverLog:export")
    @Log(title = "AI服务调用日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiServerLogBo bo, HttpServletResponse response) {
        List<AiServerLogVo> list = iAiServerLogService.queryList(bo);
        ExcelUtil.exportExcel(list, "AI服务调用日志", AiServerLogVo.class, response);
    }

    /**
     * 获取AI服务调用日志详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("system:serverLog:query")
    @GetMapping("/{id}")
    public R<AiServerLogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(iAiServerLogService.queryById(id));
    }

    /**
     * 新增AI服务调用日志
     */
    @SaCheckPermission("system:serverLog:add")
    @Log(title = "AI服务调用日志", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AiServerLogBo bo) {
        return toAjax(iAiServerLogService.insertByBo(bo));
    }

    /**
     * 修改AI服务调用日志
     */
    @SaCheckPermission("system:serverLog:edit")
    @Log(title = "AI服务调用日志", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiServerLogBo bo) {
        return toAjax(iAiServerLogService.updateByBo(bo));
    }

    /**
     * 删除AI服务调用日志
     *
     * @param ids 主键串
     */
    @SaCheckPermission("system:serverLog:remove")
    @Log(title = "AI服务调用日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iAiServerLogService.deleteWithValidByIds(Arrays.asList(ids), true));
    }
}
