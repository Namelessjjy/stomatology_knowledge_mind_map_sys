package scu.stomatology.knowledgemindmap.repository;

import org.springframework.stereotype.Repository;
import scu.stomatology.knowledgemindmap.repository.entity.MapNodeMeta;
import scu.stomatology.knowledgemindmap.vo.MapNode;

import javax.annotation.Resource;
import java.util.List;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.repository 
 *  * @ClassName:   MapNodeRepository 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/28 18:38     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Repository
public class MapNodeRepository {

    @Resource
    private MapNodeJpaRepository mapNodeJpaRepository;

    public List<MapNodeMeta> findByRootId(Long rootId) {
        return mapNodeJpaRepository.findByRootId(rootId);
    }

    public List<MapNodeMeta> search(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("%");
        for (Character c :  keyword.toCharArray()) {
            sb.append(c).append("%");
        }
        return mapNodeJpaRepository.search(sb.toString());
    }

    public void saveRoot(MapNodeMeta mapNodeMeta) {
        mapNodeJpaRepository.save(mapNodeMeta);
    }

    public MapNodeMeta getByContent(String contnet) {
        return mapNodeJpaRepository.findByContentAndRootId(contnet, 0L);
    }

    public void saveList(List<MapNodeMeta> metas) {
        mapNodeJpaRepository.saveAll(metas);
    }

    public List<MapNodeMeta> findByParentId(Long parentId) {
        return mapNodeJpaRepository.findByParentId(parentId);
    }
}
