package scu.stomatology.knowledgemindmap.repository;

import org.springframework.stereotype.Repository;
import scu.stomatology.knowledgemindmap.repository.entity.MindMap;

import javax.annotation.Resource;
import java.util.List;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.repository 
 *  * @ClassName:   MapRepository 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/28 18:38     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Repository
public class MindMapRepository {

    @Resource
    private MindMapJpaRepository mindMapJpaRepository;

    public MindMap findMapByMapName(String mapName) {
        return mindMapJpaRepository.findByName(mapName);
    }

    public MindMap findMapByRootId(Long rootId) {
        return mindMapJpaRepository.findByRootId(rootId);
    }

    public List<MindMap> findMindMapInIds(List<Long> mingmapIds) {
        return mindMapJpaRepository.findByRootIdIn(mingmapIds);
    }

    public void save(MindMap mindMap) {
        mindMapJpaRepository.save(mindMap);
    }
}
