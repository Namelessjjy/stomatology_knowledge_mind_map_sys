package scu.stomatology.knowledgemindmap.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;
import scu.stomatology.knowledgemindmap.repository.MapNodeRepository;
import scu.stomatology.knowledgemindmap.repository.MindMapRepository;
import scu.stomatology.knowledgemindmap.repository.UserMindMapRepository;
import scu.stomatology.knowledgemindmap.repository.UserRepository;
import scu.stomatology.knowledgemindmap.repository.entity.MapNodeMeta;
import scu.stomatology.knowledgemindmap.repository.entity.MindMap;
import scu.stomatology.knowledgemindmap.repository.entity.User;
import scu.stomatology.knowledgemindmap.repository.entity.UserMindMap;
import scu.stomatology.knowledgemindmap.service.service.MindMapService;
import scu.stomatology.knowledgemindmap.util.Response;
import scu.stomatology.knowledgemindmap.util.ResponseStatusEnum;
import scu.stomatology.knowledgemindmap.util.TokenUtil;
import scu.stomatology.knowledgemindmap.util.Translator;
import scu.stomatology.knowledgemindmap.vo.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.service.impl 
 *  * @ClassName:   MindMapServiceImpl 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/29 11:31     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Service
public class MindMapServiceImpl implements MindMapService {

    @Resource
    private MindMapRepository mindMapRepository;
    @Resource
    private MapNodeRepository mapNodeRepository;
    @Resource
    private UserMindMapRepository userMindMapRepository;
    @Resource
    private UserRepository userRepository;
    @Resource
    private Translator translator;

    @Override
    public Response getMindMap(String mapName) {

        Long rootId = translator.getTranslator().get(mapName);
        MindMap mindMap = mindMapRepository.findMapByRootId(rootId);

        // 转换成对应的node节点(目前只有id)
        MapNode mapNode = JSON.parseObject(mindMap.getJsonStr(), MapNode.class);
        List<MapNodeMeta> metas = mapNodeRepository.findByRootId(mindMap.getRootId());
        Map<Long, MapNodeMeta> map = metas.stream().collect(Collectors.toMap(meta -> meta.getId(), Function.identity()));
        Queue<MapNode> queue = new ArrayDeque<>();
        queue.add(mapNode);
        while (!queue.isEmpty()) {
            MapNode node = queue.poll();
            if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                for (MapNode temp : node.getChildren()) {
                    queue.add(temp);
                }
            }
            MapNodeMeta mapNodeMeta = map.get(node.getId());
            node.setRootId(mapNodeMeta.getRootId());
            node.setContent(mapNodeMeta.getContent());
        }
        MapResponse mapResponse = mapNode.transformToMapResponse();
        return Response.valueOf(ResponseStatusEnum.OK, mapResponse);
    }

    @Override
    public Response getMindMap(Long rootId) {
        MindMap mindMap = mindMapRepository.findMapByRootId(rootId);
        return getMindMap(mindMap.getName());
    }

    @Override
    public Response saveMindMap(Long rootId, String token) {
        String username = TokenUtil.getUsername(token);
        User user = userRepository.findUserByUsername(username);
        UserMindMap userMindMap = new UserMindMap();
        userMindMap.setUserId(user.getId());
        userMindMap.setMindmapId(rootId);
        return userMindMapRepository.saveIfNotExist(userMindMap);
    }

    @Override
    public Response getMyMindMap(HttpServletRequest request) {
        String token = request.getHeader("token");
        String username = TokenUtil.getUsername(token);
        User user = userRepository.findUserByUsername(username);
        List<UserMindMap> userMindMaps = userMindMapRepository.findByUserId(user.getId());
        List<Long> mingmapIds = userMindMaps.stream().map(UserMindMap::getMindmapId).collect(Collectors.toList());
        if (mingmapIds == null || mingmapIds.isEmpty()) {
            Response.valueOf(ResponseStatusEnum.OK, new ArrayList<MindMap>());
        }
        List<MindMap> mindMaps = mindMapRepository.findMindMapInIds(mingmapIds);
        List<MindMapVO> list = mindMaps.stream().map(mindMap -> {
            MindMapVO mindMapVO = new MindMapVO();
            mindMapVO.setRootId(mindMap.getRootId());
            mindMapVO.setName(mindMap.getName());
            return mindMapVO;
        }).collect(Collectors.toList());
        MyList myList = new MyList();
        myList.setList(list);
        return Response.valueOf(ResponseStatusEnum.OK, myList);
    }

    @Override
    public Response searchKeyword(String keyword) {
        List<MapNodeMeta> mapNodes = mapNodeRepository.search(keyword);
        if (mapNodes == null || mapNodes.isEmpty()) {
            return Response.valueOf(10001, "数据库中找不到匹配数据");
        }
        List<SearchVO> result = mapNodes.stream().map(mapNodeMeta -> {
            SearchVO searchVO = new SearchVO();
            searchVO.setRootId(mapNodeMeta.getRootId());
            searchVO.setContent(mapNodeMeta.getContent());
            return searchVO;
        }).collect(Collectors.toList());
        SearchResult searchResult = new SearchResult();
        searchResult.setResult(result);
        return Response.valueOf(ResponseStatusEnum.OK, searchResult);
    }

}
