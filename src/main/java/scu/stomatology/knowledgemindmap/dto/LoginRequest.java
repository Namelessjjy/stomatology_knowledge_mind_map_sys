package scu.stomatology.knowledgemindmap.dto;

import lombok.Data;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.dto 
 *  * @ClassName:   LoginRequest 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/27 1:28     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Data
public class LoginRequest {

    private String username;

    private String password;
}
