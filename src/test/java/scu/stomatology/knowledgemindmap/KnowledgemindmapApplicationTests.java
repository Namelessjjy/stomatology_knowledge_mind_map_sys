package scu.stomatology.knowledgemindmap;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import scu.stomatology.knowledgemindmap.repository.MapNodeRepository;
import scu.stomatology.knowledgemindmap.repository.MindMapRepository;
import scu.stomatology.knowledgemindmap.repository.entity.MapNodeMeta;
import scu.stomatology.knowledgemindmap.repository.entity.MindMap;
import scu.stomatology.knowledgemindmap.service.service.MindMapService;
import scu.stomatology.knowledgemindmap.vo.MapNode;
import scu.stomatology.knowledgemindmap.vo.Temp;
import scu.stomatology.knowledgemindmap.vo.TempJson;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class KnowledgemindmapApplicationTests {

    @Test
    void contextLoads() {

        String json = "{\n" +
                "    \"id\": 1,\n" +
                "    \"children\": [\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"children\": [\n" +
                "                {\n" +
                "                    \"id\": 3,\n" +
                "                    \"children\": [\n" +
                "                        {\n" +
                "                            \"id\": 5,\n" +
                "                            \"children\": [\n" +
                "                                {\n" +
                "                                    \"id\": 13,\n" +
                "                                    \"children\": [\n" +
                "                                        {\n" +
                "                                            \"id\": 21,\n" +
                "                                            \"children\": []\n" +
                "                                        },\n" +
                "                                        {\n" +
                "                                            \"id\": 22,\n" +
                "                                            \"children\": []\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"id\": 14,\n" +
                "                                    \"children\": [\n" +
                "                                        {\n" +
                "                                            \"id\": 23,\n" +
                "                                            \"children\": [\n" +
                "                                                {\n" +
                "                                                    \"id\": 24,\n" +
                "                                                    \"children\": [\n" +
                "                                                        {\n" +
                "                                                            \"id\": 26,\n" +
                "                                                            \"children\":[]\n" +
                "                                                        }\n" +
                "                                                    ]\n" +
                "                                                },\n" +
                "                                                {\n" +
                "                                                    \"id\": 25,\n" +
                "                                                    \"children\": [\n" +
                "                                                        {\n" +
                "                                                            \"id\": 27,\n" +
                "                                                            \"children\": []\n" +
                "                                                        }\n" +
                "                                                    ]\n" +
                "                                                }\n" +
                "                                            ]\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                },\n" +
                "                                {\n" +
                "                                    \"id\": 15,\n" +
                "                                    \"children\": [\n" +
                "                                        {\n" +
                "                                            \"id\": 28,\n" +
                "                                            \"children\": [\n" +
                "                                                {\n" +
                "                                                    \"id\": 29,\n" +
                "                                                    \"children\": [\n" +
                "                                                        {\n" +
                "                                                            \"id\": 31,\n" +
                "                                                            \"children\": []\n" +
                "                                                        }\n" +
                "                                                    ]\n" +
                "                                                },\n" +
                "                                                {\n" +
                "                                                    \"id\": 30,\n" +
                "                                                    \"children\": [\n" +
                "                                                        {\n" +
                "                                                            \"id\": 32,\n" +
                "                                                            \"children\": []\n" +
                "                                                        }\n" +
                "                                                    ]\n" +
                "                                                }\n" +
                "                                            ]\n" +
                "                                        }\n" +
                "                                    ]\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 6,\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 7,\n" +
                "                            \"children\": []\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"id\": 8,\n" +
                "                            \"children\": []\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": 4,\n" +
                "                    \"children\": []\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        MapNode mapNode = JSON.parseObject(json, MapNode.class);
        System.out.println();
        String test = JSON.toJSONString(mapNode);
        System.out.println(test);
    }

    @Resource
    MindMapService mindMapService;
    @Resource
    MindMapRepository mindMapRepository;
    @Resource
    MapNodeRepository mapNodeRepository;

    @Test
    void test2() {
        mindMapService.getMindMap("牙周组织病");
    }

    @Test
    void jsoupTest() throws IOException {
        File file = new File("F://test2.html");
        Document doc = Jsoup.parse(file, "UTF-8", "");
        Elements elements = doc.getElementsByTag("g");
        Map<Long, List<Temp>> map = new HashMap<>();
        System.out.println();
        System.out.println("id       parentid     text");
        Temp root = null;
        for(Element element: elements) {
            String id = element.attr("id");
            if (id == null || id.equals("")) {
                continue;
            }
            String parentid = element.attr("ed:parentid");
            String text = element.getElementsByTag("text").text();
            if (parentid == null || parentid.equals("")) {
                root = new Temp();
                root.setId(Long.valueOf(id));
                root.setContent(text);
                continue;
            }
            Temp temp = new Temp();
            temp.setId(Long.valueOf(id));
            temp.setParentId(Long.valueOf(parentid));
            temp.setContent(text);
            if (map.get(Long.valueOf(parentid)) == null) {
                List<Temp> list = new ArrayList<>();
                list.add(temp);
                map.put(Long.valueOf(parentid), list);
            } else {
                map.get(Long.valueOf(parentid)).add(temp);
            }
            System.out.println(id + "     " + parentid + "      " + text);
        }
        // 处理根节点
        MapNodeMeta mapNodeMeta = new MapNodeMeta();
        mapNodeMeta.setRootId(0L);
        mapNodeMeta.setContent(root.getContent());
        mapNodeRepository.saveRoot(mapNodeMeta);
        mapNodeMeta = mapNodeRepository.getByContent(root.getContent());
        mapNodeMeta.setRootId(mapNodeMeta.getId());
        mapNodeRepository.saveRoot(mapNodeMeta);

        // 处理子节点

        Map<Long, List<TempJson>> newMap = new HashMap<>();
        Map<Long, Long> newIdMap = new HashMap<>();
        newIdMap.put(root.getId(), mapNodeMeta.getId());
        Queue<List<Temp>> queue = new ArrayDeque<>();
        Long parentId = root.getId();
        Long rootId = mapNodeMeta.getRootId();

        queue.add(map.get(parentId));
        map.remove(parentId);
        while (!queue.isEmpty()) {
            List<Temp> list = queue.poll();
            Long tempParentId = list.get(0).getParentId();
            Long newParentId = newIdMap.get(tempParentId);
            list.forEach(l -> {
                if (map.get(l.getId()) != null) {
                    queue.add(map.get(l.getId()));
                    map.remove(l.getId());
                }
            });
            List<MapNodeMeta> metas = list.stream().map(l -> {
                MapNodeMeta meta = new MapNodeMeta();
                meta.setContent(l.getContent());
                meta.setRootId(rootId);
                meta.setParentId(newParentId);
                return meta;
            }).collect(Collectors.toList());
            mapNodeRepository.saveList(metas);
            List<MapNodeMeta> newMetas = mapNodeRepository.findByParentId(newParentId);
            List<TempJson> jsons = newMetas.stream().map(meta -> {
                TempJson tempJson = new TempJson();
                tempJson.setId(meta.getId());
                return tempJson;
            }).collect(Collectors.toList());
            newMap.put(newParentId, jsons);
            for (MapNodeMeta m : newMetas) {
                for (Temp t: list) {
                    if (m.getContent().equals(t.getContent())) {
                        newIdMap.put(t.getId(), m.getId());
                    }
                }
            }
        }
        transferToJsonStr(newMap, mapNodeMeta);
        System.out.println("done");
    }

    private void transferToJsonStr(Map<Long, List<TempJson>> newMap, MapNodeMeta root) {
        TempJson tempJson = new TempJson();
        tempJson.setId(root.getId());
        tempJson.setChildren(newMap.get(root.getId()));
        Queue<TempJson> queue = new ArrayDeque<>();
        for (TempJson t : tempJson.getChildren()) {
            queue.add(t);
        }
        while (!queue.isEmpty()) {
            TempJson temp = queue.poll();
            if (newMap.get(temp.getId()) == null) {
                continue;
            }
            temp.setChildren(newMap.get(temp.getId()));
            for (TempJson t: temp.getChildren()) {
                queue.add(t);
            }
        }

        String jsonStr = JSON.toJSONString(tempJson);
        MindMap mindMap = new MindMap();
        mindMap.setRootId(root.getId());
        mindMap.setName(root.getContent());
        mindMap.setJsonStr(jsonStr);
        mindMapRepository.save(mindMap);
    }
}
