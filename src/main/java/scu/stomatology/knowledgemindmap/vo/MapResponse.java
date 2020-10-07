package scu.stomatology.knowledgemindmap.vo;

import lombok.Data;

import java.util.List;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.vo 
 *  * @ClassName:   MapResponse 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/10/8 2:37     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Data
public class MapResponse {

    private String id;

    private String label;

    private List<MapResponse> children;

}
