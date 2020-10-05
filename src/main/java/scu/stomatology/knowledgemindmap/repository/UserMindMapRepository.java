package scu.stomatology.knowledgemindmap.repository;

import org.springframework.stereotype.Repository;
import scu.stomatology.knowledgemindmap.repository.entity.MindMap;
import scu.stomatology.knowledgemindmap.repository.entity.UserMindMap;
import scu.stomatology.knowledgemindmap.util.Response;
import scu.stomatology.knowledgemindmap.util.ResponseStatusEnum;

import javax.annotation.Resource;
import java.util.List;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.repository 
 *  * @ClassName:   UserMindMapREpository 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/29 21:35     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Repository
public class UserMindMapRepository {

    @Resource
    private UserMindMapJpaRepository userMindMapJpaRepository;


    public Response saveIfNotExist(UserMindMap userMindMap) {
        UserMindMap temp = userMindMapJpaRepository.findByUserIdAndMindmapId(userMindMap.getUserId(), userMindMap.getMindmapId());
        if (temp == null) {
            userMindMapJpaRepository.save(userMindMap);
            return Response.valueOf(ResponseStatusEnum.OK);
        } else {
            return Response.valueOf(10001, "已在列表中");
        }
    }

    public List<UserMindMap> findByUserId(Long id) {
        return userMindMapJpaRepository.findByUserId(id);
    }
}
