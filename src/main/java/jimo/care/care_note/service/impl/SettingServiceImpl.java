package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Setting;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.user.UserSettingStatus;
import jimo.care.care_note.mapper.SettingMapper;
import jimo.care.care_note.service.ISettingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@Service
public class SettingServiceImpl extends ServiceImpl<SettingMapper, Setting> implements ISettingService {

    /***
     * @param setting （全参并）
     * @return 增回馈SID
     */
    @Override
    public boolean insert(Setting setting) {
        setting.setName(setting.getName()==null?"宝儿":setting.getName());
        setting.setLocal(setting.getLocal()==null?"上海":setting.getLocal());
        setting.setPhone(setting.getPhone()==null?"17761612832":setting.getPhone());
        setting.setStatus(setting.getStatus()==null? UserSettingStatus.ALL:setting.getStatus());
        setting.setSex(setting.getSex()==null?0:setting.getSex());
        setting.setLaw(setting.getLaw()==null?"EVERY_DAY":setting.getLaw());
        return baseMapper.insert(setting)>0;
    }

    /***
     * @param sID （BySID假删除）
     * @return 删
     */
    @Override
    public boolean deleteBySID(Integer sID) {
        return baseMapper.updateById(new Setting(sID,UserSettingStatus.DELETE))>0;
    }

    /***
     * @param setting 改（BySID
     * @return 改：ALL）
     */
    @Override
    public boolean updateBySIF(Setting setting) {
        return baseMapper.updateById(setting)>0;
    }

    /***
     * @param sID BySID
     * @return 均ALL
     */
    @Override
    public Setting UserGetSetting(Integer sID) {
        return baseMapper.selectOne(Wrappers.<Setting>lambdaQuery().eq(Setting::getId,sID));
    }

    /***
     * @param page
     * @param queryWrapper ALL
     * @return 均ALL
     */
    @Override
    public Page<Setting> AdminGetSettings(Page<Setting> page, QueryWrapper<Setting> queryWrapper) {
        return page==null?new Page<Setting>().setRecords(baseMapper.selectList(queryWrapper)):baseMapper.selectPage(page,queryWrapper);
    }

    /***
     * @param queryWrapper 定时服务ByStatus
     * @return 取：|Count）
     */
    @Override
    public Integer getSettingCount(QueryWrapper<Setting> queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }
}
