package com.metime.common.downUtils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * ftp工具类
 * apache FTP 处理
 *
 * @version 1.0
 */
public class FtpUtil {

    private static final Logger log = Logger.getLogger(FtpUtil.class);
    /**
     * ftp客户端
     */
    private static FTPClient FTP_CLIENT = new FTPClient();

    /**
     * 编码
     */
    private static String ENCODING = System.getProperty("file.encoding");

    /**
     * Description: 向FTP服务器上传文件
     *
     * @param url      FTP服务器hostname
     * @param port     FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param path     FTP服务器保存目录,如果是根目录则为“/”
     * @param filename 上传到FTP服务器上的文件名
     * @param input    本地文件输入流
     * @return 成功返回true，否则返回false
     * @Version1.0
     */
    public static boolean uploadFile(String url, int port, String username,
                                     String password, String path,
                                     String filename, InputStream input) {
        boolean result = false;
        try {
            int reply;
            // 连接FTP服务器
            FTP_CLIENT.connect(url, port);
            // 登录
            FTP_CLIENT.login(username, password);
            FTP_CLIENT.setControlEncoding(ENCODING);
            // 检验是否连接成功
            reply = FTP_CLIENT.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                log.error("ftp连接失败：" + url + ":" + port);
                FTP_CLIENT.disconnect();
                return result;
            }

            // 转移工作目录至指定目录下
            boolean change = FTP_CLIENT.changeWorkingDirectory(path);
            if (!change) {
                FTP_CLIENT.makeDirectory(path);
            }
            FTP_CLIENT.changeWorkingDirectory(path);
            FTP_CLIENT.setFileType(FTP.BINARY_FILE_TYPE);
            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据，防止某些端口未开启，出现阻塞
            FTP_CLIENT.enterLocalPassiveMode();
            result = FTP_CLIENT.storeFile(new String(filename.getBytes(ENCODING), "iso-8859-1"), input);
            if (result) {
                log.info(filename + "文件上传成功!");
            } else {
                log.warn(filename + "文件上传失败！");
            }
            input.close();
            FTP_CLIENT.logout();
        } catch (IOException e) {
            result = false;
        } finally {
            if (FTP_CLIENT.isConnected()) {
                try {
                    FTP_CLIENT.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * 将本地文件上传到FTP服务器上
     */
    public static void testUpLoadFromDisk() {
        try {
            FileInputStream in = new FileInputStream(new File(
                    "E:/DemoService.java"));
            boolean flag = uploadFile("127.0.0.1", 21, "intm", "intm", "/",
                    "DemoService.java", in);
            System.out.println(flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Description: 从FTP服务器下载文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @param localPath  下载后保存到本地的路径
     * @return 处理标识
     * @Version1.0
     */
    public static boolean downFile(String url, int port, String username,
                                   String password, String remotePath,
                                   String fileName, String localPath) {
        boolean result = false;
        try {
            int reply;
            FTP_CLIENT.setControlEncoding(ENCODING);

            String[] arrUrl = url.split(",");
            if (arrUrl != null && arrUrl.length > 0) {
                for (String strUrl : arrUrl) {
                    FTP_CLIENT.connect(strUrl, port);
                    // 登录
                    boolean flag = FTP_CLIENT.login(username, password);
                    if (flag) {
                        break;
                    }
                }
            }
            // 设置文件传输类型为二进制
            FTP_CLIENT.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = FTP_CLIENT.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                FTP_CLIENT.disconnect();
                System.err.println("FTP server refused connection.");
                return result;
            }
            // 转移到FTP服务器目录至指定的目录下
            FTP_CLIENT.changeWorkingDirectory(new String(
                    remotePath.getBytes(ENCODING), "iso-8859-1"));
            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据，防止某些端口未开启，出现阻塞
            FTP_CLIENT.enterLocalPassiveMode();
            // 获取文件列表
            FTPFile[] fs = FTP_CLIENT.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());
                    OutputStream is = new FileOutputStream(localFile);
                    FTP_CLIENT.retrieveFile(ff.getName(), is);
                    result = true;
                    is.close();
                }
            }

            FTP_CLIENT.logout();
        } catch (IOException e) {
            result = false;
        } finally {
            if (FTP_CLIENT.isConnected()) {
                try {
                    FTP_CLIENT.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * 将FTP服务器上文件下载到本地
     */
    public static void testDownFile() {
        try {
            boolean flag = downFile("127.0.0.1", 21, "intm", "intm", "/",
                    "20150831150701Ay4viZr8.docx", "D:/");
            System.out.println(flag);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Description: 从FTP服务器删除文件
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param fileName   要下载的文件名
     * @return 处理标识
     * @version 1.0
     */
    public static boolean deleteFile(String url, int port, String username,
                                     String password, String remotePath,
                                     String fileName) {
        boolean result = false;
        try {
            int reply;
            FTP_CLIENT.setControlEncoding(ENCODING);

            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            FTP_CLIENT.connect(url, port);
            // 登录
            FTP_CLIENT.login(username, password);
            // 设置文件传输类型为二进制
            FTP_CLIENT.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = FTP_CLIENT.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                FTP_CLIENT.disconnect();
                System.err.println("FTP server refused connection.");
                return result;
            }
            // 转移到FTP服务器目录至指定的目录下
            FTP_CLIENT.changeWorkingDirectory(new String(
                    remotePath.getBytes(ENCODING), "iso-8859-1"));
            // 获取文件列表
            FTP_CLIENT.deleteFile(fileName);

            FTP_CLIENT.logout();
            result = true;
        } catch (IOException e) {
            result = false;
        } finally {
            if (FTP_CLIENT.isConnected()) {
                try {
                    FTP_CLIENT.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * 验证ftp登录
     *
     * @param url      FTP URL
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return 处理标识
     */
    public static boolean checkLogin(String url, int port, String username,
                                     String password) {
        boolean result = true;
        try {
            int reply;
            FTP_CLIENT.setControlEncoding(ENCODING);
            FTP_CLIENT.connect(url, port);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            // 登录
            FTP_CLIENT.login(username, password);
            // 设置文件传输类型为二进制
            FTP_CLIENT.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = FTP_CLIENT.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                FTP_CLIENT.disconnect();
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    /**
     * 验证ftp登录和文件存在
     *
     * @param url        FTP URL
     * @param port       端口
     * @param username   用户名
     * @param password   密码
     * @param remotePath 服务器地址
     * @param fileName   文件名
     * @return 处理标识
     */
    public static boolean checkLoginAndFile(String url, int port,
                                            String username, String password,
                                            String remotePath, String fileName) {
        boolean result = false;
        try {
            int reply;
            FTP_CLIENT.setControlEncoding(ENCODING);
            FTP_CLIENT.connect(url, port);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            // 登录
            FTP_CLIENT.login(username, password);
            // 设置文件传输类型为二进制
            FTP_CLIENT.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = FTP_CLIENT.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                FTP_CLIENT.disconnect();
                return result;
            }
            // 转移到FTP服务器目录至指定的目录下
            FTP_CLIENT.changeWorkingDirectory(new String(
                    remotePath.getBytes(ENCODING), "iso-8859-1"));
            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据，防止某些端口未开启，出现阻塞
            FTP_CLIENT.enterLocalPassiveMode();
            // 获取文件列表
            boolean vfileFlag = false;
            FTPFile[] fs = FTP_CLIENT.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    vfileFlag = true;
                    break;
                }
            }

            FTP_CLIENT.logout();
            if (!vfileFlag) {
                result = false;
            } else {
                result = true;
            }
        } catch (IOException e) {
            result = false;
        } finally {
            if (FTP_CLIENT.isConnected()) {
                try {
                    FTP_CLIENT.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器拷贝文件并重命名
     *
     * @param url        FTP服务器hostname
     * @param port       FTP服务器端口
     * @param username   FTP登录账号
     * @param password   FTP登录密码
     * @param remotePath FTP服务器上的相对路径
     * @param oaFilePath OA文件路径
     * @param fileName   本地UUID文件名称
     * @return 文件复制后的信息
     * @Version1.0
     */
    public static Map<String, String> copyFile(String url, int port,
                                               String username,
                                               String password,
                                               String remotePath,
                                               String oaFilePath,
                                               String fileName) {
        String result = "false";
        String fileSize = "0";
        Map<String, String> rslt = new HashMap<String, String>();
        rslt.put("flag", result);
        try {
            int reply;
            FTP_CLIENT.setControlEncoding(ENCODING);

            FTP_CLIENT.connect(url, port);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            FTP_CLIENT.login(username, password);// 登录
            // 设置文件传输类型为二进制
            FTP_CLIENT.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = FTP_CLIENT.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                FTP_CLIENT.disconnect();
                System.err.println("FTP server refused connection.");
                return rslt;
            }
            // 转移到FTP服务器目录至指定的目录下
            FTP_CLIENT.changeWorkingDirectory(new String(
                    remotePath.getBytes(ENCODING), "iso-8859-1"));
            // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据，防止某些端口未开启，出现阻塞
            FTP_CLIENT.enterLocalPassiveMode();
            // 获取文件列表
            FTPFile[] fs = FTP_CLIENT.listFiles(oaFilePath);
            InputStream inputStream = null;
            for (FTPFile ff : fs) {
                if (ff != null && ff.isFile()) {
                    fileSize = ff.getSize() + "";
                    inputStream = FTP_CLIENT.retrieveFileStream(oaFilePath);
                    // 复制文件时掉用了retrieveFileStream方法
                    // 调用完之后必须调用completePendingCommand释放,否则FTP会断开连接
                    if (!FTP_CLIENT.completePendingCommand()) {
                        return rslt;
                    }
                    // 如果读取的文件流不为空则复制文件
                    if (inputStream != null) {
                        boolean sResult = FTP_CLIENT.storeFile(remotePath + "/"
                                        + fileName,
                                inputStream);
                        // 关闭文件流
                        inputStream.close();
                        if (!sResult) {
                            return rslt;
                        }
                    }
                    break;
                }
            }

            FTP_CLIENT.logout();
            result = "true";
        } catch (IOException e) {
            e.printStackTrace();
            result = "false";
        } finally {
            if (FTP_CLIENT.isConnected()) {
                try {
                    FTP_CLIENT.disconnect();
                } catch (IOException ioe) {
                    result = "false";
                }
            }
            rslt.put("flag", result);
            rslt.put("fileSize", fileSize);
        }
        return rslt;
    }

    /**
     * Description: 从FTP服务器拷贝文件并重命名
     *
     * @param url          FTP服务器IP
     * @param port         FTP服务器端口
     * @param username     FTP登录账号
     * @param password     FTP登录密码
     * @param remotePath   FTP服务器上的相对路径
     * @param ctlFileName  控制文件名称
     * @param dataFileName 数据文件名称
     * @param localPath    本地文件路径
     * @return 文件命令拷贝返回信息
     * @Version1.0
     */
    public static Map<String, String> downFileByCtlFile(String url, int port,
                                                        String username,
                                                        String password,
                                                        String remotePath,
                                                        String ctlFileName,
                                                        String dataFileName,
                                                        String localPath) {

        Map<String, String> rslt = new HashMap<String, String>();
        boolean flag = false;
        String msg = null;
        rslt.put("flag", String.valueOf(flag));
        try {
            int reply;
            FTP_CLIENT.setControlEncoding(ENCODING);

            FTP_CLIENT.connect(url, port);
            // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
            FTP_CLIENT.login(username, password);// 登录
            // 设置文件传输类型为二进制
            FTP_CLIENT.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 获取ftp登录应答代码
            reply = FTP_CLIENT.getReplyCode();
            // 验证是否登陆成功
            if (!FTPReply.isPositiveCompletion(reply)) {
                msg = "FTP服务器拒绝连接";
            } else {
                // 转移到FTP服务器目录至指定的目录下
                boolean changeFlag = FTP_CLIENT.changeWorkingDirectory(new String(
                        remotePath.getBytes(ENCODING), "iso-8859-1"));
                if (!changeFlag) {
                    msg = "数据文件目录不存在";
                } else {
                    // 每次数据连接之前，ftp client告诉ftp server开通一个端口来传输数据，防止某些端口未开启，出现阻塞
                    FTP_CLIENT.enterLocalPassiveMode();
                    // 判断控制文件是否存在
                    String ctlFilePath = remotePath + "/" + ctlFileName;
                    FTPFile[] ctlFile = FTP_CLIENT.listFiles(ctlFilePath);
                    boolean ctlFileExistFlag = false;
                    for (FTPFile ctl : ctlFile) {
                        if (ctl != null && ctl.isFile()) {
                            ctlFileExistFlag = true;
                            break;
                        }
                    }
                    if (!ctlFileExistFlag) {
                        msg = "控制文件不存在";
                    } else {
                        // 数据文件下载
                        // 获取文件列表
                        FTPFile[] fs = FTP_CLIENT.listFiles();
                        for (FTPFile ff : fs) {
                            if (ff.getName().equals(dataFileName)) {
                                File localFile = new File(localPath + "/"
                                        + ff.getName());
                                OutputStream is = new FileOutputStream(
                                        localFile);
                                FTP_CLIENT.retrieveFile(ff.getName(), is);
                                is.close();
                                flag = true;
                                break;
                            }
                        }
                        if (!flag) {
                            msg = "数据文件不存在";
                        } else {
                            msg = "下载成功";
                        }
                    }
                }
                FTP_CLIENT.logout();
            }
        } catch (IOException e) {
            msg = "FTP服务器文件下载出现异常";
        } finally {
            if (FTP_CLIENT.isConnected()) {
                try {
                    FTP_CLIENT.disconnect();
                } catch (IOException ioe) {
                    msg = "服务器关闭出现异常";
                }
            }
            rslt.put("flag", String.valueOf(flag));
            rslt.put("message", msg);
        }
        return rslt;
    }
}
