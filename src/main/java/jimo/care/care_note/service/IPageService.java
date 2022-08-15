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
    com.baomidou.mybatisplus.extension.plugins.pagination.Page<Page> getPageList(com.baomidou.mybatisplus.extension.plugins.pagination.Page<Page> page, QueryWrapper<Page> queryWrapper);

    /***
     * @return 增加访问次数
     */
    boolean AddVisit(Integer pID);

    /***
     * @param pID 通过请求的路径获取
     * @return Page信息
     */
    Page getPage(Integer pID);
    /***
     * @param url 通过请求地址，增加一层处理，数据访问次数！
     * @return 获取本地地址
     */
     String getPageUrl(String url,boolean add);
    /***
     * 由Url进行获取全部信息
     */
    Page getPageByUrl(String url);
}
