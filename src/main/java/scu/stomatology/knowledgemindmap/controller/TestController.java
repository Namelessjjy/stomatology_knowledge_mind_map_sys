package scu.stomatology.knowledgemindmap.controller;

import org.springframework.web.bind.annotation.*;
import scu.stomatology.knowledgemindmap.util.Response;
import scu.stomatology.knowledgemindmap.util.ResponseStatusEnum;

import java.util.Map;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.controller 
 *  * @ClassName:   TestController 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/10/1 0:20     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/get")
    public Response getMappingTest(@RequestHeader Map<String, Object> header) {
        System.out.println(header);
        return Response.valueOf(ResponseStatusEnum.OK);
    }

    @PostMapping("/post")
    public Response postMappingTest(@RequestHeader Map<String, Object> header) {
        System.out.println(header);
        return Response.valueOf(ResponseStatusEnum.OK);
    }
}
