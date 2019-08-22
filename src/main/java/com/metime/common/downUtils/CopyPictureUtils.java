package com.metime.common.downUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class CopyPictureUtils {
	
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
	
	private static void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}
	
	public static void main(String[] args) throws IOException {
		JdbcTemplate jdbcTemplate = getJdbcTemplate();
		String sql = "SELECT fh.fy_house_prono '房源编号',"+
		"SUBSTR(sa.sz_attachment_url,20) '图片路径'"+
		"FROM sz_attachment sa left join fy_house fh on sa.sz_attachment_belongid=fh.fy_house_id "+
		"WHERE sz_attachment_comno = 'qGCzNL' and fh.fy_house_prono<>'' ORDER BY fh.fy_house_prono";
		List<Map<String, Object>> queryForList = jdbcTemplate.queryForList(sql);
		for (Map<String, Object> map : queryForList) {
			String file = (String)map.get("房源编号");
			String pic = (String)map.get("图片路径");
			File source = new File("D:\\web\\"+pic);
			File file2 = new File("D:\\picture\\"+file);
			if (!file2.isDirectory() && !file2.exists()) {
				file2.mkdirs();
			}
			File dest = new File("D:\\picture\\"+file+"\\"+pic);
			copyFileUsingFileChannels(source,dest);
		}
		
		
		
	}
	
}
