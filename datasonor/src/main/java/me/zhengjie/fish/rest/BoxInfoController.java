package me.zhengjie.fish.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.fish.domain.BoxInfo;
import me.zhengjie.fish.service.BoxInfoService;
import me.zhengjie.fish.service.dto.BoxInfoQueryCriteria;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Api(tags = "盒子信息管理")
@RequestMapping("/api/boxInfo")
public class BoxInfoController {

    private final BoxInfoService boxInfoService;

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('boxInfo:list')")
    public void download(HttpServletResponse response, BoxInfoQueryCriteria criteria) throws IOException {
        boxInfoService.download(boxInfoService.queryAll(criteria), response);
    }

    @GetMapping
    @Log("查询盒子信息")
    @ApiOperation("查询盒子信息")
    @PreAuthorize("@el.check('boxInfoT:list')")
    public ResponseEntity<Object> query(BoxInfoQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(boxInfoService.queryAll(criteria,pageable), HttpStatus.OK);
    }

    @PostMapping
    @Log("新增盒子信息")
    @ApiOperation("新增盒子信息")
    @PreAuthorize("@el.check('boxInfoT:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody BoxInfo resources){
        return new ResponseEntity<>(boxInfoService.create(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改盒子信息")
    @ApiOperation("修改盒子信息")
    @PreAuthorize("@el.check('boxInfoT:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody BoxInfo resources){
        boxInfoService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除盒子信息")
    @ApiOperation("删除盒子信息")
    @PreAuthorize("@el.check('boxInfoT:del')")
    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Integer[] ids) {
        boxInfoService.deleteAll(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
