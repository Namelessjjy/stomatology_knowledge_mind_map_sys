package scu.stomatology.knowledgemindmap.dto;

import lombok.Data;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.dto 
 *  * @ClassName:   GetMapRequest 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/30 23:56     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Data
public class GetMapRequest {

    private String mapName;

    private Long rootId;
}
