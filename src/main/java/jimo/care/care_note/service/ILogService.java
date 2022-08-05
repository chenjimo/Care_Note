package jimo.care.care_note.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jimo.care.care_note.bean.Log;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * <p>
 * ILogService服务类
 * 增（全参，其他信息的结果）
 * 删（ByLID假删除）
 * 改（不允许修改）
 * 查（ByUID、ALL取：均ALL）
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
public interface ILogService extends IService<Log> {
    /***
     * 增加记录
     * @param log 全部参数
     * @return 回馈成功与否
     */
    boolean insert(Log log);

    /***
     * 假删除，修改status为delete
     * @param lId LID
     * @return 成功与否
     */
    boolean deleteByLID(Integer lId);

    /***
     * 查（ALL取：ALL）自动识别加分页
     * @return
     */
    Page<Log> AdminGetLog(Page<Log> page, QueryWrapper<Log> queryWrapper);

    /***
     * 查（ByUID取：ALL）自动识别加分页
     * @return
     */
    Page<Log> UserGetLog(Page<Log> page,Integer uID);

    /***
     * @param type 周报查询成功与否数、
     * @return Integer
     */
    Integer getCount(Integer type);
}
