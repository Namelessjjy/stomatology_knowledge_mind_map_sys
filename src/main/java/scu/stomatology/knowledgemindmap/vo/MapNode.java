package scu.stomatology.knowledgemindmap.vo;

import lombok.Data;
import scu.stomatology.knowledgemindmap.repository.entity.MapNodeMeta;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.vo 
 *  * @ClassName:   MapNode 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/29 12:33     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Data
public class MapNode {

    private Long id;

    private Long rootId;

    private String content;

    private List<MapNode> children;

    /**
     * 要将id格式变成 “id1-1-1” 这种形式
     * @return
     */
    public MapResponse transformToMapResponse() {
        Queue<MapNode> queue = new ArrayDeque<>();
        Queue<MapResponse> idQueue = new ArrayDeque<>();
        queue.add(this);
        MapResponse root = new MapResponse();
        root.setId("id1");
        root.setLabel(this.content);
        idQueue.add(root);
        while (!queue.isEmpty()) {
            // 分别从两个队列中取出对应的节点
            MapNode node = queue.poll();
            MapResponse mapResponse = idQueue.poll();
            // 取出当前父节点的字符串id
            String mapId = mapResponse.getId();
            if (node.getChildren() != null && !node.getChildren().isEmpty()) {
                List<MapNode> tempList = node.getChildren();
                List<MapResponse> mapResponseList = new ArrayList<>();
                int count = 1;
                for (MapNode temp : tempList) {
                    queue.add(temp);
                    MapResponse m = new MapResponse();
                    m.setId(mapId + "-" + count);
                    m.setLabel(temp.getContent());
                    idQueue.add(m);
                    mapResponseList.add(m);
                    count++;
                }
                mapResponse.setChildren(mapResponseList);
            }

        }
        return root;
    }
}
