package jimo.care.care_note.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Setting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * ISettingService服务类
 * 增（全参并回馈SID）
 * 删（BySID真删除）
 * 改（BySID改：ALL）
 * 查（BySID、ALL（定时服务ByStatus）取：均ALL|Count）
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
public interface ISettingService extends IService<Setting> {
    /***
     * @param setting （全参并）
     * @return 增回馈SID
     */
    boolean insert(Setting setting);

    /***
     * @param sID （BySID真删除）
     * @return 删
     */
    boolean deleteBySID(Integer sID);

    /***
     * @param setting 改（BySID
     * @return 改：ALL）
     */
    boolean updateBySIF(Setting setting);

    /***
     * @param sID BySID
     * @return 均ALL
     */
    Setting UserGetSetting(Integer sID);

    /***
     * @param page
     * @param queryWrapper ALL
     * @return 均ALL
     */
    Page<Setting> AdminGetSettings(Page<Setting> page, QueryWrapper<Setting> queryWrapper);

    /***
     * @param queryWrapper 定时服务ByStatus
     * @return 取：|Count）
     */
    Integer getSettingCount(QueryWrapper<Setting> queryWrapper);
}
