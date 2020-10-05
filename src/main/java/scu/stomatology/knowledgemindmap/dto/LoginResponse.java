package scu.stomatology.knowledgemindmap.dto;

import lombok.Data;
import scu.stomatology.knowledgemindmap.repository.entity.User;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.dto 
 *  * @ClassName:   LoginResponse 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/10/4 14:06     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Data
public class LoginResponse {

    private User user;

    private String token;
}
