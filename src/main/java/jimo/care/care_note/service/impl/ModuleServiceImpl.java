package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.CareModule;
import jimo.care.care_note.mapper.ModuleMapper;
import jimo.care.care_note.service.IModuleService;
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
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, CareModule> implements IModuleService {

    /***
     * @param module （全参，部分自动初始化，默认0公开）
     * @return 增
     */
    @Override
    public boolean insert(CareModule module) {
        module.setName(module.getName()==null?"JIMO关怀！":module.getName());
        module.setUId(module.getUId()==null?0:module.getUId());
        module.setStatus(module.getStatus()==null?"AUTO":module.getStatus());
        module.setTemp(module.getTemp()==null?"AUTO":module.getTemp());
        module.setMorning(module.getMorning()==null?"AUTO":module.getMorning());
        module.setNoon(module.getNoon()==null?"AUTO":module.getNoon());
        module.setEvening(module.getEvening()==null?"AUTO":module.getEvening());
        return baseMapper.insert(module)>0;
    }

    /***
     * @param mID ByUID&ByMID、
     * @param uID ByUID&ByMID、
     * @return 真删除
     */
    @Override
    public boolean UserDeleteModule(Integer mID, Integer uID) {
        return baseMapper.delete(Wrappers.<CareModule>update(new CareModule(mID,uID)))>0;
    }

    /***
     * @param mID （ByMID真删除）
     * @return 真删除
     */
    @Override
    public boolean AdminDeleteModule(Integer mID) {
        return baseMapper.deleteById(mID)>0;
    }

    /***
     * @param module （ByUID&ByMID改：）
     * @return ALL
     */
    @Override
    public boolean UserUpdateModule(CareModule module) {
        return baseMapper.update(module,Wrappers.<CareModule>update(new CareModule(module.getId(),module.getUId())))>0;
    }

    /***
     * @param module 改
     * @return ALL
     */
    @Override
    public boolean AdminUpdateModule(CareModule module) {
        return baseMapper.updateById(module)>0;
    }

    /***
     *
     * @param page
     * @param uID 查（ByUID|0
     * @return 均ALL
     */
    @Override
    public Page<CareModule> UserGetModules(Page<CareModule> page, Integer uID) {
        return baseMapper.selectPage(page,
                Wrappers.<CareModule>lambdaUpdate()
                        .eq(CareModule::getUId,uID)
                        .or().eq(CareModule::getUId,0)
                        .or().eq(CareModule::getUId,-1)
                        .or().eq(CareModule::getUId,-2));
    }

    /***
     *
     * @param page
     * @param queryWrapper ByMID|ByUID|0取
     * @return 均ALL
     */
    @Override
    public Page<CareModule> AdminGetModules(Page<CareModule> page, QueryWrapper<CareModule> queryWrapper) {
        return page==null?new Page<CareModule>().setRecords(baseMapper.selectList(queryWrapper)):baseMapper.selectPage(page,queryWrapper);
    }

    /***
     * @param mID ByMID
     * @return OneALL
     */
    @Override
    public CareModule getModule(Integer mID) {
        return baseMapper.selectById(mID);
    }

    /***
     * @param queryWrapper 获取模板统计
     * @return 数量
     */
    @Override
    public Integer getModuleCount(QueryWrapper queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }
}
