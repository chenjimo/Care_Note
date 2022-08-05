package jimo.care.care_note.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * IUserService 服务类
 * 增（必须全参，部分为默认设置）;
 * 删（ByUID假删除仅改power为0）;
 * 改（ByUID动态修改：4>权限name|phone|pwd、>3管理员ALL）;
 * 查（ByEmail、ALL取：4>权限name|phone|money|email|power、>3管理员ALL）
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
public interface IUserService extends IService<User> {
    /***
     * 增（必须全参，部分为默认设置）;
     * @param user
     * @return
     */
    boolean insert(User user);

    /***
     * 删（ByUID假删除仅改power为0）;
     * @param uId
     * @return
     */
    boolean deleteByUID(Integer uId);

    /***
     * 改（ByUID动态修改：4>权限name|phone|pwd、>3管理员ALL）;
     * @param user
     * @return
     */
    boolean updateByUID(User user);

    /***
     * @param page
     * @param queryWrapper ALL取
     * @return >3管理员ALL
     */
    Page<User> AdminGetUsers(Page<User> page, QueryWrapper queryWrapper);

    /***
     * @param page
     * @param email ByEmail
     * @return 4>权限ID|name|phone|money|email|power
     */
    Page<User> UserGetUsers(Page<User> page, String email);

    /***
     * @return 己注册用户总数
     */
    Integer getCount();
}
