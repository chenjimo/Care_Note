package jimo.care.care_note.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import jimo.care.care_note.bean.Page;

/**
 * <p>
 * IPageService 服务类
 * 增（全参控制，默认权限7）
 * 删 （假删除，权限调为0）
 * 改（ALL改：ALL）
 * 查（ByPID取：ALL）
 * </p>
 *
 * @author JIMO
 * @since 2022-08-06
 */
public interface IPageService extends IService<Page> {
    /***
     * @param page 全参控制,默认权限7
     * @return 增
     */
    boolean insert(Page page);

    /***
     * @param pID 权限调为0
     * @return 假删除
     */
    boolean deleteByPID(Integer pID);

    /***
     * @param page ALL
     * @return ALL
     */
    boolean updateByPID(Page page);

    /***
     * @param page
     * @param queryWrapper ByPID取
     * @return ALL
     */
    com.baomidou.mybatisplus.extension.plugins.pagination.Page getPageList(com.baomidou.mybatisplus.extension.plugins.pagination.Page<Page> page, QueryWrapper queryWrapper);

    /***
     * @return 增加访问次数
     */
    boolean AddVisit(Integer pID);
}
