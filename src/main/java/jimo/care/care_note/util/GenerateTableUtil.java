package jimo.care.care_note.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/***
 * 自动化生成Mapper与Bean的工具类
 */
public class GenerateTableUtil {
    public static void generate(String parent,String moduleName,String ...table){
        AutoGenerator mpg = new AutoGenerator();
        // 1.全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        // 输出目录
        gc.setOutputDir(projectPath + "/src/main/java/jimo/care/care_note/");
        gc.setAuthor("zhou");
        gc.setServiceName("%sDao");
        gc.setFileOverride(true);
        gc.setOpen(false);
        gc.setSwagger2(true);  //实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
        // 2.设置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUsername("care_note_jimo");
        dsc.setPassword("care_note_jimo123456");
        dsc.setUrl("jdbc:mysql://jimo.fun:3306/care_note?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);
        // 3. 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(moduleName);
        pc.setParent(parent);
        pc.setEntity("bean");
        pc.setController("controller");
        pc.setService("service");
        pc.setMapper("mapper");
        mpg.setPackageInfo(pc);

        // 4.策略设置
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 重点，设置映射的表名
        strategy.setInclude(table);
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
//        // 设置逻辑删除字段
//        strategy.setLogicDeleteFieldName("deleted");
//        // 自动填充字段
//        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
//        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
//        strategy.setTableFillList(List.of(createTime,updateTime));
//        // 乐观锁字段
//        strategy.setVersionFieldName("version");
//        strategy.setRestControllerStyle(true); // Controller用rest风格
//        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setRestControllerStyle(true); // Controller用rest风格
//        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        mpg.execute();

    }

}

