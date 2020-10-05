package scu.stomatology.knowledgemindmap.repository;

import org.springframework.stereotype.Repository;
import scu.stomatology.knowledgemindmap.repository.entity.User;

import javax.annotation.Resource;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.repository 
 *  * @ClassName:   UserRepository 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/28 18:38     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Repository
public class UserRepository {

    @Resource
    private UserJpaRepository userJpaRepository;


    public User findUserByUsername(String username) {
        return userJpaRepository.findByUsername(username);
    }

    public boolean insertUser(User user) {
        if (checkValid(user)) {
            userJpaRepository.saveAndFlush(user);
            return true;
        } else {
            return false;
        }
    }

    private Boolean checkValid(User user) {
        if (user == null) {
            return false;
        }
        if (user.getUsername() == null || user.getUsername().equals("")) {
            return false;
        }
        if (user.getPassword() == null || user.getPassword().equals("")) {
            return false;
        }
        return true;
    }
}
