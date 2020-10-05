package scu.stomatology.knowledgemindmap.vo;

import lombok.Data;

import java.util.List;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.vo 
 *  * @ClassName:   SearchResult 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/29 23:11     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Data
public class SearchResult {

    private List<SearchVO> result;
}
