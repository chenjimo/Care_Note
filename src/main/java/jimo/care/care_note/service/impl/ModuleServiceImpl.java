package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Module;
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
public class ModuleServiceImpl extends ServiceImpl<ModuleMapper, Module> implements IModuleService {

    /***
     * @param module （全参，部分自动初始化，默认0公开）
     * @return 增
     */
    @Override
    public boolean insert(Module module) {
        module.setName(module.getName()==null?"JIMO关怀！":module.getName());
        module.setUse(module.getUse()==null?0:module.getUse());
        module.setMorning(module.getMorning()==null?"早安！":module.getMorning());
        module.setNoon(module.getNoon()==null?"午安！":module.getNoon());
        module.setEvening(module.getEvening()==null?"晚安！":module.getEvening());
        return baseMapper.insert(module)>0;
    }

    /***
     * @param mID ByUID&ByMID、
     * @param uID ByUID&ByMID、
     * @return 真删除
     */
    @Override
    public boolean UserDeleteModule(Integer mID, Integer uID) {
        return baseMapper.delete(Wrappers.<Module>update(new Module(mID,uID)))>0;
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
    public boolean UserUpdateModule(Module module) {
        return baseMapper.update(module,Wrappers.<Module>update(new Module(module.getId(),module.getUse())))>0;
    }

    /***
     * @param module 改
     * @return ALL
     */
    @Override
    public boolean AdminUpdateModule(Module module) {
        return baseMapper.updateById(module)>0;
    }

    /***
     *
     * @param page
     * @param uID 查（ByUID|0
     * @return 均ALL
     */
    @Override
    public Page UserGetModules(Page<Module> page, Integer uID) {
        return baseMapper.selectPage(page,
                Wrappers.<Module>lambdaUpdate()
                        .eq(Module::getUse,uID)
                        .or()
                        .eq(Module::getUse,0));
    }

    /***
     *
     * @param page
     * @param queryWrapper ByMID|ByUID|0取
     * @return 均ALL
     */
    @Override
    public Page AdminGetModules(Page<Module> page, QueryWrapper queryWrapper) {
        return baseMapper.selectPage(page,queryWrapper);
    }

    /***
     * @param mID ByMID
     * @return OneALL
     */
    @Override
    public Module getModule(Integer mID) {
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
