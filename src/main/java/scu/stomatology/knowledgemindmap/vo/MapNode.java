package scu.stomatology.knowledgemindmap.vo;

import lombok.Data;

import java.util.List;

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
}
