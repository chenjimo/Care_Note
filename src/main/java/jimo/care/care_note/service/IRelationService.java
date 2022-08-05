package jimo.care.care_note.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Relation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * IRelayionService服务类
 * 增（创建关怀对象时全参插入，初始化默认m_id=1、s_id=u_id默认关怀对象为自己可以添加或更改）
 * 删（ByRID真删除）
 * 改（ByRID改：m_id|s_id）
 * 查（ByUID、ALL|ByUID|BySID|ByMID取：均ALL，一般是关联其他表信息同时查）
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
public interface IRelationService extends IService<Relation> {
    /***
     * @param relation 创建关怀对象时全参插入，初始化默认m_id=1、
     * @return 增
     */
    boolean insert(Relation relation);

    /***
     * @param rID （ByRID真删除）
     * @return 删
     */
    boolean deleteByRID(Integer rID);

    /***
     * @param relation ByRID改
     * @return 改（：m_id|s_id）
     */
    boolean updateByRID(Relation relation);

    /***
     * @param page
     * @param uID ByUID
     * @return ALL，一般是关联其他表信息同时查
     */
    Page<Relation> UserGetRelations(Page<Relation> page,Integer uID);

    /***
     * @param page
     * @param queryWrapper ALL|ByUID|BySID|ByMID取
     * @return ALL，一般是关联其他表信息同时查
     */
    Page<Relation> AdminGetRelations(Page<Relation> page, QueryWrapper queryWrapper);

}
