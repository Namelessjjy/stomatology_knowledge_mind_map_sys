package scu.stomatology.knowledgemindmap.vo;

import lombok.Data;

import java.util.List;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.vo 
 *  * @ClassName:   TempJson 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/30 3:09     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Data
public class TempJson {
    private Long id;
    private List<TempJson> children;
}
