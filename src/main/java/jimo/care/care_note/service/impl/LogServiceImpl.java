package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimo.care.care_note.bean.Log;
import jimo.care.care_note.bean.User;
import jimo.care.care_note.info.LogCountType;
import jimo.care.care_note.mapper.LogMapper;
import jimo.care.care_note.service.ILogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * <p>
 * LogServiceImpl服务实现类
 * </p>
 *
 * @author JIMO
 * @since 2022-08-04
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {
    /***
     * 增加记录
     * @param log 全部参数
     * @return 回馈成功与否
     */
    @Override
    public boolean insert(Log log) {
        log.setDate(log.getDate() == null ? LocalDateTime.now() : log.getDate());
        return baseMapper.insert(log) > 0;
    }

    /***
     * 假删除，修改status为delete
     * @param lId LID
     * @return 成功与否
     */
    @Override
    public boolean deleteByLID(Integer lId) {
        return baseMapper.updateById(new Log(lId, "delete" + LocalDateTime.now())) > 0;
    }

    /***
     * 查（ALL取：ALL）自动识别加分页
     */
    @Override
    public Page<Log> AdminGetLog(Page<Log> page, QueryWrapper<Log> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }

    /***
     * 查（ByUID取：ALL）自动识别加分页
     */
    @Override
    public Page<Log> UserGetLog(Page<Log> page, Integer uID) {
        return baseMapper.selectPage(page, Wrappers.<Log>lambdaQuery().eq(Log::getUId, uID));
    }

    /***
     * 0：全部，1：全部成功记录；
     * 2：享受服务的用户数，3：成功享受服务的用户数；
     * 4：被关怀的对象数，5：成功被关怀的用户数；
     * 6：使用的模板数，7：成功使用的模板数；
     * @param type 周报查询成功与否数、
     * @return Integer
     */
    @Override
    public Integer getCount(Integer type) {
        LambdaQueryWrapper<Log> logQuery = Wrappers.<Log>lambdaQuery();
        switch (type) {
            case 0: logQuery = null;
            break;
            case 2: logQuery.select(Log::getUId);
            break;
            case 4:logQuery.select(Log::getSId);
            break;
            case 6:logQuery.select(Log::getMId);
            break;
        }
        if (type==1||type==3||type==5||type==7){
            logQuery.eq(Log::getStatus,"true");
        }
        return baseMapper.selectCount(logQuery);
    }


}
