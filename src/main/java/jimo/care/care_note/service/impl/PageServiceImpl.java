package jimo.care.care_note.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jimo.care.care_note.bean.Page;
import jimo.care.care_note.info.user.UserPower;
import jimo.care.care_note.mapper.PageMapper;
import jimo.care.care_note.service.IPageService;
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
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements IPageService {


    /***
     * @param page 全参控制,默认权限7
     * @return 增
     */
    @Override
    public boolean insert(Page page) {
        page.setName(page.getName()==null?"暂未命名":page.getName());
        page.setUrl(page.getUrl()==null?"http://wx.jimo.fun/404.html":page.getUrl());
        page.setPower(page.getPower()==null? UserPower.JIMO:page.getPower());
        page.setBz(page.getBz()==null?"正在测试的界面！！！":page.getBz());
        return baseMapper.insert(page)>0;
    }

    /***
     * @param pID 权限调为0
     * @return 假删除
     */
    @Override
    public boolean deleteByPID(Integer pID) {
        return baseMapper.updateById(new Page(pID,UserPower.DELETE))>0;
    }

    /***
     * @param page ALL
     * @return ALL
     */
    @Override
    public boolean updateByPID(Page page) {
        return baseMapper.updateById(page)>0;
    }

    /***
     * @param page
     * @param queryWrapper ByPID取
     * @return ALL
     */
    @Override
    public com.baomidou.mybatisplus.extension.plugins.pagination.Page<Page> getPageList(com.baomidou.mybatisplus.extension.plugins.pagination.Page<Page> page, QueryWrapper<Page> queryWrapper) {
        return page==null?new com.baomidou.mybatisplus.extension.plugins.pagination.Page<Page>().setRecords(baseMapper.selectList(queryWrapper))
                :baseMapper.selectPage(page,queryWrapper);
    }

    /***
     * @return 增加访问次数
     */
    @Override
    public boolean AddVisit(Integer pID) {
        Page page = baseMapper.selectOne(Wrappers.<Page>lambdaQuery().select(Page::getVisit).eq(Page::getId, pID));
        Page page1 = new Page(pID);
        page1.setVisit(page==null?1:page.getVisit()+1);
        return baseMapper.updateById(page1)>0;
    }

    /***
     * @param pID 通过请求的路径获取
     * @return Page信息
     */
    @Override
    public Page getPage(Integer pID) {
        return baseMapper.selectOne(Wrappers.<Page>lambdaQuery().eq(Page::getId,pID));
    }
    /***
     * @param url 通过请求地址，增加一层处理，选择性增加数据访问次数！
     * @return 获取本地地址
     */
    public String getPageUrl(String url,boolean add){
        Page page = baseMapper.selectOne(Wrappers.<Page>lambdaQuery().select(Page::getPageUrl, Page::getId).eq(Page::getUrl, url));
        if (add){
            AddVisit(page.getId());
        }
        return page.getPageUrl();
    }
}
