package jimo.care.care_note.service;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JIMO
 * @since 2022-08-06
 */
public interface SendFunction {
    /***
     * 每天的上午七点执行一次！
     */
    void scheduledMorning();
    /***
     * 每天的中午十二点执行一次！
     */
    void scheduledNoon();
    /***
     * 每天的晚上九点执行一次！
     */
    void scheduledEvening();
    /***
     * 每天的晚上十点执行一次！
     */
    void scheduledOlg();

    /***
     * 测试每10秒一执行
     */
    void scheduledTest();
}
