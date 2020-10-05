package scu.stomatology.knowledgemindmap.repository.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 *      
 *  * @ProjectName:  knowledgemindmap
 *  * @Package:    scu.stomatology.knowledgemindmap.repository.entity 
 *  * @ClassName:   UserMindMap 
 *  * @Description:    
 *  * @Author:    JiangJunYan 
 *  * @CreateDate:  2020/9/29 21:32     
 *  * @Version:    v1.0 
 *  *    
 *  
 */
@Entity
@Table(name = "user_mindmap")
@DynamicInsert
@DynamicUpdate
@Data
public class UserMindMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long userId;

    @Column
    private Long mindmapId;

}
