package com.lccf.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lccf.base.controller.BaseController;
import com.lccf.domain.Menu;
import com.lccf.service.menu.IMenuService;
import com.lccf.service.menu.MenuParam;
import com.lccf.service.menu.MenuVo;
import com.lccf.service.menu.impl.MenuUtil;
import com.lccf.util.ResponseVo;
import com.lccf.util.ResponseVoUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author lichangchao
 * @Time 2017-04-24 菜单管理
 */

@RestController
public class MenuController extends BaseController {
    @Resource
    IMenuService menuService;

    @RequestMapping(value = "/menu/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取全部菜单列表", httpMethod = "GET", response = List.class)
    public ResponseVo list() {
        List<Menu> menuList = menuService.findByDeleteFlagAndParentId(false, null);
        String menuJson = MenuUtil.transMenuListTOJson(menuList);
        return ResponseVoUtil.successResult("获取成功", menuJson);
    }

    @RequestMapping(value = "/menu/parentList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取父级菜单列表-用于菜单页面下拉框", httpMethod = "GET", response = Menu.class)

    public ResponseVo parentList() {
        List<Menu> menuList = menuService.findByDeleteFlagAndParentId(false, null);
        return ResponseVoUtil.successData(menuList);

    }

    @RequestMapping(value = "/menu/page", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取菜单列表-分页", httpMethod = "GET", response = Page.class)

    public ResponseVo page(@ApiParam(value = "用户参数", required = true) MenuParam menuParam) {
        Page<MenuVo> menuPage = menuService.page(menuParam);
        return ResponseVoUtil.successData(menuPage);

    }

    @RequestMapping(value = "/menu/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "新增菜单信息", httpMethod = "POST", response = String.class)

    public ResponseVo save(@RequestBody @ApiParam(value = "用户参数", required = true) MenuParam menuParam) {
        menuService.save(menuParam);
        return ResponseVoUtil.successMsg("操作成功");
    }

    @RequestMapping(value = "/menu/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "新增菜单信息", httpMethod = "POST", response = Page.class)
    public ResponseVo getById(@ApiParam(value = "用户ID", required = true) @PathVariable Long id) {
        Menu menu = menuService.findOne(id);
        return ResponseVoUtil.successData(menu);

    }

    @RequestMapping(value = "/menu/delete/{id}", method = RequestMethod.DELETE)
    @ApiOperation(value = "删除用户", httpMethod = "DELETE", response = String.class, notes = "")
    public ResponseVo deleteById(@ApiParam(value = "用户ID", required = true) @PathVariable Long id) {
        menuService.updateDeleteFlagById(id);
        return ResponseVoUtil.successMsg("操作成功");
    }
}
