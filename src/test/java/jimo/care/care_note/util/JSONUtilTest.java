package jimo.care.care_note.util;

import jimo.care.care_note.pojo.WeatherDay;
import jimo.care.care_note.pojo.WeatherIndex;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class JSONUtilTest {
    private static final String weather= "{\"status\":0,\"msg\":\"ok\",\"result\":{\"city\":\"郑州\",\"cityid\":148,\"citycode\":101180101,\"date\":\"2022-08-03\",\"week\":\"星期三\",\"weather\":\"晴\",\"temp\":\"34\",\"temphigh\":\"36\",\"templow\":\"27\",\"img\":\"0\",\"humidity\":\"57\",\"pressure\":\"991\",\"windspeed\":\"5.7\",\"winddirect\":\"东风\",\"windpower\":\"4级\",\"updatetime\":\"2022-08-03 18:38:00\",\"index\":[{\"iname\":\"空调指数\",\"ivalue\":\"部分时间开启\",\"detail\":\"您将感到些燥热，建议您在适当的时候开启制冷空调来降低温度，以免中暑。\"},{\"iname\":\"运动指数\",\"ivalue\":\"较适宜\",\"detail\":\"天气较好，但风力较大，推荐您进行室内运动，若在户外运动请注意防风。\"},{\"iname\":\"紫外线指数\",\"ivalue\":\"很强\",\"detail\":\"紫外线辐射极强，建议涂擦SPF20以上、PA++的防晒护肤品，尽量避免暴露于日光下。\"},{\"iname\":\"感冒指数\",\"ivalue\":\"少发\",\"detail\":\"各项气象条件适宜，发生感冒机率较低。但请避免长期处于空调房间中，以防感冒。\"},{\"iname\":\"洗车指数\",\"ivalue\":\"较适宜\",\"detail\":\"较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。\"},{\"iname\":\"空气污染扩散指数\",\"ivalue\":\"良\",\"detail\":\"气象条件有利于空气污染物稀释、扩散和清除，可在室外正常活动。\"},{\"iname\":\"穿衣指数\",\"ivalue\":\"炎热\",\"detail\":\"天气炎热，建议着短衫、短裙、短裤、薄型T恤衫等清凉夏季服装。\"}],\"aqi\":{\"so2\":\"3\",\"so224\":\"\",\"no2\":\"6\",\"no224\":\"\",\"co\":\"0.6\",\"co24\":\"\",\"o3\":\"153\",\"o38\":\"\",\"o324\":\"\",\"pm10\":\"50\",\"pm1024\":\"\",\"pm2_5\":\"32\",\"pm2_524\":\"\",\"iso2\":\"\",\"ino2\":\"\",\"ico\":\"\",\"io3\":\"\",\"io38\":\"\",\"ipm10\":\"\",\"ipm2_5\":\"\",\"aqi\":\"52\",\"primarypollutant\":\"PM10\",\"quality\":\"良\",\"timepoint\":\"2022-08-03 17:00:00\",\"aqiinfo\":{\"level\":\"二级\",\"color\":\"#FFFF00\",\"affect\":\"空气质量可接受，但某些污染物可能对极少数异常敏感人群健康有较弱影响\",\"measure\":\"极少数异常敏感人群应减少户外活动\"}},\"daily\":[{\"date\":\"2022-08-03\",\"week\":\"星期三\",\"sunrise\":\"05:35\",\"sunset\":\"19:27\",\"night\":{\"weather\":\"多云\",\"templow\":\"27\",\"img\":\"1\",\"winddirect\":\"东南风\",\"windpower\":\"微风\"},\"day\":{\"weather\":\"晴\",\"temphigh\":\"36\",\"img\":\"0\",\"winddirect\":\"东南风\",\"windpower\":\"微风\"}},{\"date\":\"2022-08-04\",\"week\":\"星期四\",\"sunrise\":\"05:36\",\"sunset\":\"19:26\",\"night\":{\"weather\":\"晴\",\"templow\":\"29\",\"img\":\"0\",\"winddirect\":\"南风\",\"windpower\":\"3-5级\"},\"day\":{\"weather\":\"晴\",\"temphigh\":\"37\",\"img\":\"0\",\"winddirect\":\"南风\",\"windpower\":\"3-5级\"}},{\"date\":\"2022-08-05\",\"week\":\"星期五\",\"sunrise\":\"05:37\",\"sunset\":\"19:25\",\"night\":{\"weather\":\"多云\",\"templow\":\"29\",\"img\":\"1\",\"winddirect\":\"南风\",\"windpower\":\"3-5级\"},\"day\":{\"weather\":\"晴\",\"temphigh\":\"38\",\"img\":\"0\",\"winddirect\":\"南风\",\"windpower\":\"3-5级\"}},{\"date\":\"2022-08-06\",\"week\":\"星期六\",\"sunrise\":\"05:38\",\"sunset\":\"19:24\",\"night\":{\"weather\":\"阴\",\"templow\":\"29\",\"img\":\"2\",\"winddirect\":\"南风\",\"windpower\":\"3-5级\"},\"day\":{\"weather\":\"阴\",\"temphigh\":\"37\",\"img\":\"2\",\"winddirect\":\"南风\",\"windpower\":\"3-5级\"}},{\"date\":\"2022-08-07\",\"week\":\"星期日\",\"sunrise\":\"05:38\",\"sunset\":\"19:23\",\"night\":{\"weather\":\"阴\",\"templow\":\"28\",\"img\":\"2\",\"winddirect\":\"南风\",\"windpower\":\"4-5级\"},\"day\":{\"weather\":\"阴\",\"temphigh\":\"37\",\"img\":\"2\",\"winddirect\":\"南风\",\"windpower\":\"3-5级\"}},{\"date\":\"2022-08-08\",\"week\":\"星期一\",\"sunrise\":\"05:39\",\"sunset\":\"19:22\",\"night\":{\"weather\":\"小雨\",\"templow\":\"24\",\"img\":\"7\",\"winddirect\":\"东北风\",\"windpower\":\"3-5级\"},\"day\":{\"weather\":\"阴\",\"temphigh\":\"35\",\"img\":\"2\",\"winddirect\":\"南风\",\"windpower\":\"3-5级\"}},{\"date\":\"2022-08-09\",\"week\":\"星期二\",\"sunrise\":\"05:40\",\"sunset\":\"19:21\",\"night\":{\"weather\":\"小雨\",\"templow\":\"22\",\"img\":\"7\",\"winddirect\":\"东北风\",\"windpower\":\"微风\"},\"day\":{\"weather\":\"小雨\",\"temphigh\":\"28\",\"img\":\"7\",\"winddirect\":\"东北风\",\"windpower\":\"3-5级\"}}],\"hourly\":[{\"time\":\"18:00\",\"weather\":\"晴\",\"temp\":\"34\",\"img\":\"0\"},{\"time\":\"19:00\",\"weather\":\"晴\",\"temp\":\"34\",\"img\":\"0\"},{\"time\":\"20:00\",\"weather\":\"晴\",\"temp\":\"33\",\"img\":\"0\"},{\"time\":\"21:00\",\"weather\":\"晴\",\"temp\":\"33\",\"img\":\"0\"},{\"time\":\"22:00\",\"weather\":\"晴\",\"temp\":\"32\",\"img\":\"0\"},{\"time\":\"23:00\",\"weather\":\"晴\",\"temp\":\"31\",\"img\":\"0\"},{\"time\":\"0:00\",\"weather\":\"多云\",\"temp\":\"30\",\"img\":\"1\"},{\"time\":\"1:00\",\"weather\":\"多云\",\"temp\":\"29\",\"img\":\"1\"},{\"time\":\"2:00\",\"weather\":\"多云\",\"temp\":\"28\",\"img\":\"1\"},{\"time\":\"3:00\",\"weather\":\"多云\",\"temp\":\"28\",\"img\":\"1\"},{\"time\":\"4:00\",\"weather\":\"多云\",\"temp\":\"28\",\"img\":\"1\"},{\"time\":\"5:00\",\"weather\":\"多云\",\"temp\":\"28\",\"img\":\"1\"},{\"time\":\"6:00\",\"weather\":\"多云\",\"temp\":\"29\",\"img\":\"1\"},{\"time\":\"7:00\",\"weather\":\"多云\",\"temp\":\"30\",\"img\":\"1\"},{\"time\":\"8:00\",\"weather\":\"晴\",\"temp\":\"31\",\"img\":\"0\"},{\"time\":\"9:00\",\"weather\":\"晴\",\"temp\":\"32\",\"img\":\"0\"},{\"time\":\"10:00\",\"weather\":\"晴\",\"temp\":\"34\",\"img\":\"0\"},{\"time\":\"11:00\",\"weather\":\"晴\",\"temp\":\"35\",\"img\":\"0\"},{\"time\":\"12:00\",\"weather\":\"晴\",\"temp\":\"36\",\"img\":\"0\"},{\"time\":\"13:00\",\"weather\":\"晴\",\"temp\":\"36\",\"img\":\"0\"},{\"time\":\"14:00\",\"weather\":\"晴\",\"temp\":\"37\",\"img\":\"0\"},{\"time\":\"15:00\",\"weather\":\"晴\",\"temp\":\"36\",\"img\":\"0\"},{\"time\":\"16:00\",\"weather\":\"晴\",\"temp\":\"36\",\"img\":\"0\"},{\"time\":\"17:00\",\"weather\":\"晴\",\"temp\":\"35\",\"img\":\"0\"}]}}";
    @Resource
    JSONUtil jsonUtil;
    @Test
    void indexTest() {
        jsonUtil.init(weather);
        List<WeatherIndex> index = jsonUtil.indexList();
        index.forEach(System.out::println);
    }
    @Test
    void today(){
        jsonUtil.init(weather);
         jsonUtil.WeatherDays().forEach(System.out::println);
    }
}