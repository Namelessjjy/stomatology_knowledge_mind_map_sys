package scu.stomatology.knowledgemindmap.service.service;

import scu.stomatology.knowledgemindmap.util.Response;

import javax.servlet.http.HttpServletRequest;

public interface MindMapService {

    /**
     * 获取思维导图
     * @param mapName 导图名称
     * @return 返回一个树状结构的list
     */
    Response getMindMap(String mapName);

    Response getMindMap(Long rootId);

    /**
     * 保存导图到自己的列表中（登陆后）
     * @param rootId 导图id
     * @return 返回一个树状结构的list
     */
    Response saveMindMap(Long rootId, String token);

    /**
     * 获取我的保存列表
     * @return 返回一个list
     */
    Response getMyMindMap(HttpServletRequest request);

    /**
     * 模糊查询所有导图中的关键字
     * @param keyword 查找关键字
     * @return 返回一个包含了关键字的list
     */
    Response searchKeyword(String keyword);
}
