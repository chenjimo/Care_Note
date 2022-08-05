package jimo.care.care_note.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Module;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * IModuleService服务类
 * 增（全参，部分自动初始化，默认0公开）
 * 删（ByUID&ByMID、ByMID真删除）
 * 改（ByUID&ByMID改：ALL）
 * 查（ByUID|0、ByMID|ByUID|0取：均ALL）
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
public interface IModuleService extends IService<Module> {
    /***
     * @param module （全参，部分自动初始化，默认0公开）
     * @return 增
     */
    boolean insert(Module module);

    /***
     * @param mID ByUID&ByMID、
     * @param uID ByUID&ByMID、
     * @return 真删除
     */
    boolean UserDeleteModule(Integer mID,Integer uID);

    /***
     * @param mID （ByMID真删除）
     * @return 真删除
     */
    boolean AdminDeleteModule(Integer mID);

    /***
     * @param module （ByUID&ByMID改：）
     * @return ALL
     */
    boolean UserUpdateModule(Module module);

    /***
     * @param module 改
     * @return ALL
     */
    boolean AdminUpdateModule(Module module);

    /***
     *
     * @param page
     * @param uID 查（ByUID|0
     * @return 均ALL
     */
    Page UserGetModules(Page<Module> page,Integer uID);

    /***
     * @param queryWrapper ByMID|ByUID|0取
     * @return 均ALL
     */
    Page AdminGetModules(Page<Module> page, QueryWrapper queryWrapper);

    /***
     * @param mID ByMID
     * @return OneALL
     */
    Module getModule(Integer mID);

    /***
     * @param queryWrapper 获取模板统计
     * @return 数量
     */
    Integer getModuleCount(QueryWrapper queryWrapper);
}
