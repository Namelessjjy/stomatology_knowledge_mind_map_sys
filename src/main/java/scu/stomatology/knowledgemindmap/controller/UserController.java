package scu.stomatology.knowledgemindmap.controller;

import org.springframework.web.bind.annotation.*;
import scu.stomatology.knowledgemindmap.dto.LoginRequest;
import scu.stomatology.knowledgemindmap.dto.LoginResponse;
import scu.stomatology.knowledgemindmap.dto.RegisterRequest;
import scu.stomatology.knowledgemindmap.notation.UserLoginToken;
import scu.stomatology.knowledgemindmap.repository.entity.User;
import scu.stomatology.knowledgemindmap.service.service.UserService;
import scu.stomatology.knowledgemindmap.util.Response;
import scu.stomatology.knowledgemindmap.util.ResponseStatusEnum;
import scu.stomatology.knowledgemindmap.util.TokenUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.controller 
 *  * @ClassName:   UserController 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/27 1:21     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest loginRequest) {
        Response login = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return login;

    }

    @PostMapping("/register")
    public Response register(@RequestBody RegisterRequest registerRequest) {
        Response register = userService.register(registerRequest.getUsername(), registerRequest.getPassword());
        if (register.getCode() == 10000) {
            Response login = userService.login(registerRequest.getUsername(), registerRequest.getPassword());
            return login;
        }
        return register;
    }

    @UserLoginToken
    @GetMapping("/logout")
    public Response logout(HttpServletRequest request) {
        if (request.getSession().getAttribute("user") != null) {
            request.getSession().removeAttribute("user");
        }
        return Response.valueOf(ResponseStatusEnum.OK);
    }
}
