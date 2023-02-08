package com.ruoyi.system.controller.system;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.excel.utils.ExcelUtil;
import com.ruoyi.common.idempotent.annotation.RepeatSubmit;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.mybatis.core.page.PageQuery;
import com.ruoyi.common.mybatis.core.page.TableDataInfo;
import com.ruoyi.common.web.core.BaseController;
import com.ruoyi.system.domain.bo.SysTenantPackageBo;
import com.ruoyi.system.domain.vo.SysTenantPackageVo;
import com.ruoyi.system.service.ISysTenantPackageService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 租户套餐管理
 *
 * @author Michelle.Chung
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/tenant/package")
public class SysTenantPackageController extends BaseController {

    private final ISysTenantPackageService sysTenantPackageService;

    /**
     * 查询租户套餐列表
     */
    @SaCheckPermission("system:tenantPackage:list")
    @GetMapping("/list")
    public TableDataInfo<SysTenantPackageVo> list(SysTenantPackageBo bo, PageQuery pageQuery) {
        return sysTenantPackageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出租户套餐列表
     */
    @SaCheckPermission("system:tenantPackage:export")
    @Log(title = "租户套餐", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SysTenantPackageBo bo, HttpServletResponse response) {
        List<SysTenantPackageVo> list = sysTenantPackageService.queryList(bo);
        ExcelUtil.exportExcel(list, "租户套餐", SysTenantPackageVo.class, response);
    }

    /**
     * 获取租户套餐详细信息
     *
     * @param packageId 主键
     */
    @SaCheckPermission("system:tenantPackage:query")
    @GetMapping("/{packageId}")
    public R<SysTenantPackageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long packageId) {
        return R.ok(sysTenantPackageService.queryById(packageId));
    }

    /**
     * 新增租户套餐
     */
    @SaCheckPermission("system:tenantPackage:add")
    @Log(title = "租户套餐", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SysTenantPackageBo bo) {
        return toAjax(sysTenantPackageService.insertByBo(bo));
    }

    /**
     * 修改租户套餐
     */
    @SaCheckPermission("system:tenantPackage:edit")
    @Log(title = "租户套餐", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SysTenantPackageBo bo) {
        return toAjax(sysTenantPackageService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("system:tenantPackage:edit")
    @Log(title = "租户套餐", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public R<Void> changeStatus(@RequestBody SysTenantPackageBo bo) {
        return toAjax(sysTenantPackageService.updatePackageStatus(bo));
    }

    /**
     * 删除租户套餐
     *
     * @param packageIds 主键串
     */
    @SaCheckPermission("system:tenantPackage:remove")
    @Log(title = "租户套餐", businessType = BusinessType.DELETE)
    @DeleteMapping("/{packageIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] packageIds) {
        return toAjax(sysTenantPackageService.deleteWithValidByIds(List.of(packageIds), true));
    }
}
