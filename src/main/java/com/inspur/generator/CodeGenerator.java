package com.inspur.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * 代码生成器的演示Demo
 * 1.全局配置
 * 2.数据源配置
 * 3.包配置
 */
public class CodeGenerator {

	public static void main(String[] args) {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		//1.全局配置
		GlobalConfig globalConfig = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		globalConfig.setOpen(false);                                //是否打开输出目录
		globalConfig.setActiveRecord(true);                         //是否开启AR模式(ActiveRecord)
		globalConfig.setAuthor("Yang");                             //作者
		globalConfig.setOutputDir(projectPath + "/src/main/java");  //生成文件的目录
		globalConfig.setFileOverride(true);                         //多次生成的时候是否覆盖
		globalConfig.setServiceName("%sService");                   //设置生成的接口不是I*Service的形式, 去掉I
		globalConfig.setServiceImplName("%sServiceImpl");           //设置成成接口的实现类不以I开头
		globalConfig.setBaseResultMap(true);                        //是否开启 BaseResultMap
		globalConfig.setBaseColumnList(true);                       //是否开启 baseColumnList
		// gc.setSwagger2(true); 实体属性 Swagger2 注解


		//2.数据源配置
		DataSourceConfig dataSourceConfig = new DataSourceConfig();
//		dataSourceConfig.setDbType(DbType.MYSQL);
//		dataSourceConfig.setUrl("jdbc:mysql://192.168.120.150:3306/mybatis_plus?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&autoReconnect=true&useSSL=false");
//		dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
//		dataSourceConfig.setUsername("root");
//		dataSourceConfig.setPassword("123456");
		dataSourceConfig.setDbType(DbType.ORACLE);
		dataSourceConfig.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
		dataSourceConfig.setDriverName("oracle.jdbc.driver.OracleDriver");
		dataSourceConfig.setUsername("scott");
		dataSourceConfig.setPassword("tiger");


		//3.包配置
		PackageConfig packageConfig = new PackageConfig();
		packageConfig.setParent("com.inspur.generator_code");     //指定父级package
		packageConfig.setMapper("mapper");                        //指定Mapper接口文件的目录
		packageConfig.setXml("mapper");                           //指定Mapper.xml文件的目录
		packageConfig.setService("service");                      //指定Service接口文件的目录
		packageConfig.setServiceImpl("service.impl");             //指定ServiceImpl文件的目录
		packageConfig.setController("controller");                //指定Controller文件的目录
		packageConfig.setEntity("entity");                        //指定Entity文件的目录
//		packageConfig.setModuleName("");                          //指定父模块名称


		//4.自定义配置
//		InjectionConfig injectionConfig = new InjectionConfig() {
//			@Override
//			public void initMap() {
//				// to do nothing
//
//			}
//		};
		// 如果模板引擎是 freemarker
//		String templatePath = "/templates/mapper.xml.ftl";
//		// 如果模板引擎是 velocity
//		// String templatePath = "/templates/mapper.xml.vm";
//
//		// 自定义输出配置
//		List<FileOutConfig> focList = new ArrayList<>();
//		// 自定义配置会被优先输出
//		focList.add(new FileOutConfig(templatePath) {
//			@Override
//			public String outputFile(TableInfo tableInfo) {
//				// 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
//				return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
//						+ "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
//			}
//		});
//        /*
//        cfg.setFileCreate(new IFileCreate() {
//            @Override
//            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
//                // 判断自定义文件夹是否需要创建
//                checkDir("调用默认方法创建的目录");
//                return false;
//            }
//        });
//        */
//		injectionConfig.setFileOutConfigList(focList);
//		mpg.setCfg(injectionConfig);
//
//		//5.配置模板
//		TemplateConfig templateConfig = new TemplateConfig();
//
//		// 配置自定义输出模板
//		//指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
//		// templateConfig.setEntity("templates/entity2.java");
//		// templateConfig.setService();
//		// templateConfig.setController();
//
//		templateConfig.setXml(null);
//		mpg.setTemplate(templateConfig);


		//6.策略配置
		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);            //数据库表映射到实体的命名策略 采用下划线转驼峰
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);      //数据库表字段映射到实体的属性命名策略 采用下划线转驼峰
//		strategy.setTablePrefix(packageConfig.getModuleName() + "_");     //设置数据库表名称的前缀
		/**
		 * 在Mysql中这样小写可以,但是在Oracle中必须大写否则找不到表
		 */
//		strategy.setTablePrefix("table_");                                //设置数据库表名称的前缀
//		strategy.setInclude(new String[]{"table_teacher"});               //设置具体生成哪些表

		strategy.setTablePrefix("TABLE_");                                //设置数据库表名称的前缀
		strategy.setInclude(new String[]{"TABLE_TEACHER"});               //设置具体生成哪些表


//		strategy.containsTablePrefix("table_");                           //设置具体生成哪些表(这里是写前缀就OK了,这种方式更加灵活)
		strategy.setEntityLombokModel(true);                              //设置Entity是否采用Lombok 的方式
		strategy.setRestControllerStyle(true);                            //设置生成@RestController的模式

//		strategy.setSuperEntityClass("com.baomidou.ant.common.BaseEntity");
//		strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController");
//		strategy.setSuperEntityColumns("id");
//		strategy.setControllerMappingHyphenStyle(true);

		//7.整合配置
		mpg.setGlobalConfig(globalConfig);
		mpg.setDataSource(dataSourceConfig);
		mpg.setPackageInfo(packageConfig);

		mpg.setStrategy(strategy);
//		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

}