package me.zhengjie.fish.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.exception.EntityExistException;
import me.zhengjie.fish.domain.BoxInfo;
import me.zhengjie.fish.repository.BoxInfoRepository;
import me.zhengjie.fish.service.BoxInfoService;
import me.zhengjie.fish.service.dto.BoxInfoDto;
import me.zhengjie.fish.service.dto.BoxInfoQueryCriteria;
import me.zhengjie.fish.service.mapstruct.BoxInfoMapper;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoxInfoServiceImpl implements BoxInfoService {

    private final BoxInfoRepository boxInfoTRepository;
    private final BoxInfoMapper boxInfoMapper;

    @Override
    public Map<String,Object> queryAll(BoxInfoQueryCriteria criteria, Pageable pageable){
        Page<BoxInfo> page = boxInfoTRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(boxInfoMapper::toDto));
    }

    @Override
    public List<BoxInfoDto> queryAll(BoxInfoQueryCriteria criteria){
        return boxInfoMapper.toDto(boxInfoTRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    @Transactional
    public BoxInfoDto findById(Integer id) {
        BoxInfo boxInfoT = boxInfoTRepository.findById(id).orElseGet(BoxInfo::new);
        ValidationUtil.isNull(boxInfoT.getId(),"BoxInfoT","id",id);
        return boxInfoMapper.toDto(boxInfoT);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BoxInfoDto create(BoxInfo resources) {
        if(boxInfoTRepository.findByBoxNumber(resources.getBoxNumber()) != null){
            throw new EntityExistException(BoxInfo.class,"box_number",resources.getBoxNumber());
        }
        return boxInfoMapper.toDto(boxInfoTRepository.save(resources));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(BoxInfo resources) {
//        BoxInfo boxInfo = boxInfoTRepository.findById(resources.getId()).orElseGet(BoxInfo::new);
//        ValidationUtil.isNull( boxInfo.getId(),"BoxInfoT","id",resources.getId());
//        boxInfo = boxInfoTRepository.findByBoxNumber(resources.getBoxNumber());
//        if(boxInfoT1 != null && !boxInfoT1.getId().equals(boxInfoT.getId())){
//            throw new EntityExistException(BoxInfoT.class,"box_number",resources.getBoxNumber());
//        }
//        boxInfoT.copy(resources);
//        boxInfoTRepository.save(boxInfoT);
    }

    @Override
    public void deleteAll(Integer[] ids) {
        for (Integer id : ids) {
            boxInfoTRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<BoxInfoDto> all, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (BoxInfoDto boxInfo : all) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put(" 盒子类型", boxInfo.getBoxTypoId());
            map.put("盒子编号", boxInfo.getBoxNumber());
            map.put("注册时间", boxInfo.getRegisteTime());
            map.put("创建人", boxInfo.getCreateDate());
            map.put("创建时间", boxInfo.getCreateBy());
            map.put("最后更新人", boxInfo.getLastUpdateDate());
            map.put("最后更新时间", boxInfo.getLastUpdateBy());
            map.put("塘口ID", boxInfo.getFishPondId());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
