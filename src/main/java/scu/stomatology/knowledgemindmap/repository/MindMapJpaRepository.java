package scu.stomatology.knowledgemindmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import scu.stomatology.knowledgemindmap.repository.entity.MindMap;

import java.util.List;


/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.repository 
 *  * @ClassName:   UserJpaRepository 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/28 18:38     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Component
public interface MindMapJpaRepository extends JpaRepository<MindMap, Long> {

    MindMap findByName(String mapName);

    List<MindMap> findByRootIdIn(List<Long> mindmapIds);

    MindMap findByRootId(Long rootId);
}
