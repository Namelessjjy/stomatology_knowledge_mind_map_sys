package scu.stomatology.knowledgemindmap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import scu.stomatology.knowledgemindmap.repository.entity.User;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.repository 
 *  * @ClassName:   UserJpaRepository 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/28 18:38     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Component
public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
