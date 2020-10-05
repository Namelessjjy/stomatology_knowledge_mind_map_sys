package scu.stomatology.knowledgemindmap.repository.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.repository.entity 
 *  * @ClassName:   MapNodeMeta 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/28 18:39     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Entity
@Table(name = "map_node")
@DynamicInsert
@DynamicUpdate
@Data
public class MapNodeMeta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long rootId;

    @Column
    private String content;

    @Column
    private Long parentId;

}
