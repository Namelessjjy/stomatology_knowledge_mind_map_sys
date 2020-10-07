package scu.stomatology.knowledgemindmap.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import scu.stomatology.knowledgemindmap.repository.entity.User;

import java.util.Date;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.util 
 *  * @ClassName:   TokenUtil 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/10/4 13:38     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
public class TokenUtil {

    public static String getToken(User user) {
        String token = "";
        token = JWT.create().withExpiresAt(new Date(System.currentTimeMillis() + 10 * 1000)).withAudience(user.getUsername()).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public static String getUsername(String token) {
        String username = "";
        username = JWT.decode(token).getAudience().get(0);
        return username;
    }
}
