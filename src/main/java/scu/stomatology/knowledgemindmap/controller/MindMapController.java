package scu.stomatology.knowledgemindmap.controller;

import org.springframework.web.bind.annotation.*;
import scu.stomatology.knowledgemindmap.dto.GetMapRequest;
import scu.stomatology.knowledgemindmap.dto.SavaMapReuquest;
import scu.stomatology.knowledgemindmap.notation.PassToken;
import scu.stomatology.knowledgemindmap.notation.UserLoginToken;
import scu.stomatology.knowledgemindmap.service.service.MindMapService;
import scu.stomatology.knowledgemindmap.util.Response;
import scu.stomatology.knowledgemindmap.util.ResponseStatusEnum;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.controller 
 *  * @ClassName:   MainController 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/27 1:19     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping("/mindmap")
public class MindMapController {

    @Resource
    private MindMapService mindMapService;

    @PassToken
    @PostMapping("/getMindMap")
    public Response getMindMap(@RequestBody GetMapRequest getMapRequest) {
        if (getMapRequest.getMapName() != null) {
            return mindMapService.getMindMap(getMapRequest.getMapName());
        }
        if (getMapRequest.getRootId() != null) {
            return mindMapService.getMindMap(getMapRequest.getRootId());
        }

        return Response.valueOf(ResponseStatusEnum.FAILED);
    }

    @UserLoginToken
    @PostMapping("/saveMindMap")
    public Response saveMindMap(@RequestBody SavaMapReuquest savaMapReuquest, HttpServletRequest request) {
        String token = request.getHeader("token");
        return mindMapService.saveMindMap(savaMapReuquest.getRootId(), token);
    }

    @UserLoginToken
    @GetMapping("/getMyMindMap")
    public Response getMyMindMap(HttpServletRequest request) {
        return mindMapService.getMyMindMap(request);
    }

    @GetMapping
    public Response search(@RequestParam("keyword") String keyword) {
        return mindMapService.searchKeyword(keyword);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public Response notLoginExceptionHandler(RuntimeException e) {
        return Response.valueOf(20000, "请重新登录");
    }
}
