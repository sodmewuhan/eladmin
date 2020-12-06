package me.zhengjie.fish.service;

import me.zhengjie.fish.domain.BoxInfo;
import me.zhengjie.fish.service.dto.BoxInfoDto;
import me.zhengjie.fish.service.dto.BoxInfoQueryCriteria;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BoxInfoService {


    /**
     * 查询数据分页
     * @param criteria 条件
     * @param pageable 分页参数
     * @return Map<String,Object>
     */
    Map<String,Object> queryAll(BoxInfoQueryCriteria criteria, Pageable pageable);

    /**
     * 查询所有数据不分页
     * @param criteria 条件参数
     * @return List<BoxInfoTDto>
     */
    List<BoxInfoDto> queryAll(BoxInfoQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id ID
     * @return BoxInfoTDto
     */
    BoxInfoDto findById(Integer id);

    /**
     * 创建
     * @param resources /
     * @return BoxInfoTDto
     */
    BoxInfoDto create(BoxInfo resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(BoxInfo resources);

    /**
     * 多选删除
     * @param ids /
     */
    void deleteAll(Integer[] ids);

    /**
     * 导出数据
     * @param all 待导出的数据
     * @param response /
     * @throws IOException /
     */
    void download(List<BoxInfoDto> all, HttpServletResponse response) throws IOException;
}
