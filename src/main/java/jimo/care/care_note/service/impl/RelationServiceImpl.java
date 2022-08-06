package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Relation;
import jimo.care.care_note.mapper.RelationMapper;
import jimo.care.care_note.service.IRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@Service
public class RelationServiceImpl extends ServiceImpl<RelationMapper, Relation> implements IRelationService {

    /***
     * @param relation 创建关怀对象时全参插入，初始化默认m_id=1、
     * @return 增
     */
    @Override
    public boolean insert(Relation relation) {
        relation.setMId(relation.getMId()==null?1:relation.getMId());
        return baseMapper.insert(relation)>0;
    }

    /***
     * @param rID （ByRID真删除）
     * @return 删
     */
    @Override
    public boolean deleteByRID(Integer rID) {
        return baseMapper.deleteById(rID)>0;
    }

    /***
     * @param relation ByRID改
     * @return 改（：m_id|s_id）
     */
    @Override
    public boolean updateByRID(Relation relation) {
        return baseMapper.updateById(relation)>0;
    }

    /***
     * @param uID ByUID
     * @return ALL，一般是关联其他表信息同时查
     */
    @Override
    public Page<Relation> UserGetRelations(Page<Relation> page, Integer uID) {
        return baseMapper.selectPage(page, Wrappers.<Relation>lambdaQuery().eq(Relation::getUId,uID));
    }

    /***
     * @param queryWrapper ALL|ByUID|BySID|ByMID取
     * @return ALL，一般是关联其他表信息同时查
     */
    @Override
    public Page AdminGetRelations(Page<Relation> page, QueryWrapper queryWrapper) {
        return baseMapper.selectPage(page,queryWrapper);
    }
}