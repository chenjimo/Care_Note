package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.mapper.UserMapper;
import jimo.care.care_note.info.user.UserPower;
import jimo.care.care_note.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimo.care.care_note.util.DateUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    /***
     * 增（必须全参，部分为默认设置）;
     * @param user 参考插入信息
     * @return 初始化成果
     */
    @Override
    public boolean insert(User user) {
        user.setName(user.getName()==null?"小鬼记得起名字吆！": user.getName());
        user.setPower(user.getPower()==null?UserPower.USER:user.getPower());
        user.setBz(user.getBz()==null?"初来乍到的小鬼":user.getBz());
        user.setCreateTime(user.getCreateTime()==null? LocalDateTime.now():user.getCreateTime());
        user.setMoney(user.getMoney()==null?0:user.getMoney());
        user.setSex(user.getSex()==null?1:user.getSex());
        return baseMapper.insert(user)>0;
    }

    /***
     * 删（ByUID假删除仅改power为0）;
     * @param uId
     * @return
     */
    @Override
    public boolean deleteByUID(Integer uId) {
        return baseMapper.updateById(new User(uId, UserPower.DELETE))>0;
    }

    /***
     * 改（ByUID动态修改：4>权限name|phone|pwd、>3管理员ALL）;
     * @param user
     * @return
     */
    @Override
    public boolean updateByUID(User user) {
        return baseMapper.updateById(user)>0;
    }

    /***
     * 查（ByEmail、ALL取：4>权限name|phone|money|email|power、>3管理员ALL）
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public Page<User> AdminGetUsers(Page<User> page, QueryWrapper queryWrapper) {
        return baseMapper.selectPage(page,queryWrapper);
    }

    /***
     * @param email ByEmail
     * @return 4>权限ID|name|phone|money|email|power
     */
    @Override
    public User UserGetUser( String email) {
        return baseMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getEmail,email)
                .select(User::getId,User::getName,User::getPwd,User::getPhone,User::getMoney,User::getEmail,User::getPower,User::getSex));
    }

    /***
     * @return 己注册用户总数
     */
    @Override
    public Integer getCount(QueryWrapper queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }
}
