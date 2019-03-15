package util;

import org.apache.log4j.Logger;

/**
 * @description 得到当前应用的系统路径
 */
public class SystemPathUtil {

	private static Logger logger = Logger.getLogger(SystemPathUtil.class);

	public static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径，如：D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}

	/**
	 * 获取系统根目录
	 * 
	 * @return
	 */
	public static String getRootPath() {
		String classPath = SystemPathUtil.class.getResource("/").getPath();
		logger.info("classPath路径为：" + classPath);
		String rootPath = "";
		String sysName = System.getProperties().getProperty("os.name");
		logger.info(sysName);
		// windows下
		if (sysName.toLowerCase().contains("windows")) {
			rootPath = getPorjectPath();
			logger.info("--------windows获取路径-----------"+rootPath);
			if (rootPath.indexOf("webapp") == -1) {
				// 开发环境
				rootPath = rootPath.replace(".biz", ".web") + "src/main/webapp/";
				logger.info("------------开发环境获取根目录--------------"+rootPath );
			} else {
				// 编译环境
				rootPath = rootPath + SystemConstant.PROJECT_NAME + "/";
				logger.info("---------------编译环境获取根目录-------------"+rootPath);
			}
		} else {// linux
			// /data/wwwroot/default/taro-web/WEB-INF/lib
			rootPath = classPath.substring(0, classPath.indexOf(SystemConstant.PROJECT_NAME + "/WEB-INF"));
			rootPath = rootPath.replace("\\", "/");
			rootPath = rootPath + "sc-kbs/";
			logger.info("----------linux获取路径----------------"+rootPath);
		}
		System.out.println(rootPath);
		return rootPath;
	}

	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		logger.info("--->" + path);
		String temp = path.replaceFirst("file:/", "").replaceFirst("target/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}
}
