package scu.stomatology.knowledgemindmap.dto;

import lombok.Data;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.dto 
 *  * @ClassName:   RegisterRequest 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/28 22:09     
 *  * @Version:    v1.0 
 *  *    
 *  
 */

@Data
public class RegisterRequest {

    private String username;

    private String password;
}
