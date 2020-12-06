package me.zhengjie.fish.repository;

import me.zhengjie.fish.domain.BoxInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BoxInfoRepository extends JpaRepository<BoxInfo, Integer>, JpaSpecificationExecutor<BoxInfo> {

    /**
     * 根据 BoxNumber 查询
     * @param box_number /
     * @return /
     */
    BoxInfo findByBoxNumber(String box_number);
}
