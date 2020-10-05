package scu.stomatology.knowledgemindmap.service.impl;

import org.springframework.stereotype.Service;
import scu.stomatology.knowledgemindmap.dto.LoginResponse;
import scu.stomatology.knowledgemindmap.repository.UserRepository;
import scu.stomatology.knowledgemindmap.repository.entity.User;
import scu.stomatology.knowledgemindmap.service.service.UserService;
import scu.stomatology.knowledgemindmap.util.Response;
import scu.stomatology.knowledgemindmap.util.ResponseStatusEnum;
import scu.stomatology.knowledgemindmap.util.TokenUtil;

import javax.annotation.Resource;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.service 
 *  * @ClassName:   UserServiceImpl 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/28 18:37     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public Response login(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return Response.valueOf(ResponseStatusEnum.LOGINFAIL);
        } else {
            if (user.getPassword().equals(password)) {
                LoginResponse loginResponse = new LoginResponse();
                String token = TokenUtil.getToken(user);
                loginResponse.setToken(token);
                user.setPassword("");
                loginResponse.setUser(user);
                return Response.valueOf(ResponseStatusEnum.OK, loginResponse);
            } else {
                return Response.valueOf(ResponseStatusEnum.LOGINFAIL);
            }
        }
    }

    @Override
    public Response register(String username, String password) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setPassword(password);
            if (userRepository.insertUser(user)) {
                return Response.valueOf(ResponseStatusEnum.OK);
            } else {
                return Response.valueOf(ResponseStatusEnum.FAILED);
            }
        } else {
            return Response.valueOf(10001, "用户已存在，请重新确认");
        }
    }


}
