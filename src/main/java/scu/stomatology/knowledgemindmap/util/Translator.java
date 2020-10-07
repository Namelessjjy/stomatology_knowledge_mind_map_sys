package scu.stomatology.knowledgemindmap.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.util 
 *  * @ClassName:   Translator 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/10/8 1:58     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Component
public class Translator {

    private Map<String, Long> translateMap;

    public Translator() {
        translateMap = new HashMap<>();
        translateMap.put("牙体",680L);
        translateMap.put("牙周组织",0L);
        translateMap.put("嘴唇",0L);
        translateMap.put("软腭",0L);
        translateMap.put("舌下腺",0L);
        translateMap.put("下颌下腺",0L);
        translateMap.put("腮腺",0L);
        translateMap.put("颌骨",0L);
        translateMap.put("口腔颌面部其他组织来源的肿瘤和瘤样病变",0L);
    }

    public Map<String, Long> getTranslator() {
        return translateMap;
    }
}
