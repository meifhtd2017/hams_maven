package com.metime.common.downUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DownPictureUtils {
	
	
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String jdbcUrl = "jdbc:mysql://192.168.1.125:3306/saas20190820";
	private static String user = "hamsadmin";
	private static String pwd = "hzly$2017";

	public static JdbcTemplate getJdbcTemplate() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverName);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(user);
		dataSource.setPassword(pwd);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		return jdbcTemplate;
	}

	public static void getPicture(List<Map<String, Object>> list) {
		HttpURLConnection conn = null;
		InputStream inputStream = null;
		BufferedInputStream bis = null;
		FileOutputStream out = null;
		try {
			for (Map<String, Object> map : list) {
				String file = (String)map.get("房源编号");
				String url = (String)map.get("图片路径");
				String[] split = url.split("\\/");
				
				File file0 = new File("E:\\picture\\"+file);
				if (!file0.isDirectory() && !file0.exists()) {
					file0.mkdirs();
				}
				out = new FileOutputStream(file0 + "\\"+split[split.length - 1]);
				URL httpUrl = new URL(url);
				conn = (HttpURLConnection) httpUrl.openConnection();
				// 建立链接
				// 以Post方式提交表单，默认get方式
				conn.setRequestMethod("GET");
				conn.setDoInput(true);
				conn.setDoOutput(true);
				// post方式不能使用缓存
				conn.setUseCaches(false);
				// 连接指定的资源
				conn.connect();
				// 获取网络输入流
				inputStream = conn.getInputStream();
				bis = new BufferedInputStream(inputStream);
				byte b[] = new byte[1024];
				int len = 0;
				while ((len = bis.read(b)) != -1) {
					out.write(b, 0, len);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (bis != null) {
					bis.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		String sql = "SELECT fh.fy_house_prono '房源编号',"+
		"CONCAT('http://img.12157.top/upload/' , sa.sz_attachment_url) '图片路径'"+
		"FROM sz_attachment sa left join fy_house fh on sa.sz_attachment_belongid=fh.fy_house_id "+
		"WHERE sz_attachment_comno = 'qGCzNL' and fh.fy_house_prono<>'' ORDER BY fh.fy_house_prono";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		getPicture(queryForList);
	}

}
