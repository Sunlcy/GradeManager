/**
 * 
 */
package cc.chaoyangliu.rmiprac.service;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author bird
 *
 */
public class DeployServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Config cfg = new Config("config/config.properties");
		String url = cfg.getProperty("url");
		String tp = cfg.getProperty("port");
		
		if (url == null) {
			url = "localhost";
		}
		if (tp == null) {
			tp = "1235";
		}
		int port = Integer.parseInt(tp);
		try {
			// 创建远程对象
			DataServiceImpl ds = new DataServiceImpl();

			// 启动注册服务,如果没有这个语句，需要手工启动：开始菜单--运行--rmiregistry，默认端口1099
			LocateRegistry.createRegistry(port);   //这里，服务端口号可任意指定
			
			// 远程对象绑定到服务
			Naming.rebind("//" + url + ":" + port + "/ds", ds);
			System.out.println("Starting RMI Server...");
			System.out.println("The URL of Server is " + url + ".");
			System.out.println("The port of Server is " + port + ".");
			System.out.println("RMI Server is Running ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
