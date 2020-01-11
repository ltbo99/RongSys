package com.ruoyi.broad.utils;

import com.ruoyi.broad.domain.Program;
import com.ruoyi.common.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class bFileUtil {
    private static Logger logger = LoggerFactory.getLogger(bFileUtil.class);
    /**
     * 图片存储 完整路径（{user.home}/img/coldStone/XXX.jpg）
     * @param file
     * @return 返回相对路径
     */
    public static String saveImg(MultipartFile file,String saveName) {
        String  path = bConst.UPLOAD_PATH + bConst.VIRTUAL_IMG_PATH; //图片存储路径
        saveName = bCommonUtil.getUuid() + "." + getFileSuffix(file.getOriginalFilename());
        logger.info(" --- 终端图片保存路径：{}, 终端图片保存名称：{},终端图片名称：{} --- ", path, saveName,file.getOriginalFilename());
        try {
            File targetFile = new File(path);
                if (!targetFile.exists()) {
                    targetFile.mkdirs();
            }
            file.transferTo(new File(path + "/" + saveName));  //文件保存，写入硬盘
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("--- 音频保存异常：{} ---" + e.getMessage());
            return null;
        }
        return "profile/img/virtual/"+saveName;
    }
    /**
     * mp3文件存储 完整路径（{user.home}/img/coldStone/XXX.jpg）
     * @param file
     * @return 返回相对路径
     */
    public static String saveMusic(MultipartFile file,String saveName) {
//        String  path = bConst.UPLOAD_PATH + bConst.MP3_FILE_NAME;
//        logger.info(" --- 音频保存路径：{}, 音频保存名称：{},文件名称：{} --- ", path, saveName,file.getOriginalFilename());
//        try {
//            File targetFile = new File(path);
//            if (!targetFile.exists()) {
//                targetFile.mkdirs();
//            }
//            System.out.println("=======1");
////            file.transferTo(new File(path, saveName));  //文件保存，写入内存
//            file.transferTo(new File(path + "/" + saveName));
//            System.out.println("=======2");
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.debug("--- 音频保存异常：{} ---" + e.getMessage());
//            return null;
//        }
//        return saveName;

        //获取文件上传的根目录 C:\Users\wanghao/upload/img
        String  path = bConst.UPLOAD_PATH + bConst.VIRTUAL_IMG_PATH; //图片存储路径
        saveName = bCommonUtil.getUuid() + "." + getFileSuffix(file.getOriginalFilename());
        logger.info(" --- 终端图片保存路径：{}, 终端图片保存名称：{},终端图片名称：{} --- ", path, saveName,file.getOriginalFilename());
        try {
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(new File(path + "/" + saveName));  //文件保存，写入硬盘
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("--- 音频保存异常：{} ---" + e.getMessage());
            return null;
        }
        return "profile/img/virtual/"+saveName;
    }

    /**
     * 返回截取的文件后缀
     * @param path
     * @return
     */
    public static String getFileSuffix(String path) {
        return getFileSuffix(path, "2");
    }

    /**
     * 获取文件名称或后缀(最后一个"."之后内容)
     * @param path
     * @param type 1名称 2后缀
     * @return
     */
    public static String getFileSuffix(String path, String type){
        if(StringUtils.isNotEmpty(path) && path.indexOf(".") > 0) {
            // 名称
            String name = path.substring(0, path.lastIndexOf("."));

            // 后缀
            String suffix = path.substring(path.lastIndexOf(".") + 1);

            return StringUtils.equals("1", type) ? name : suffix;
        } else {
            return null;
        }
    }

    /**
     * 文件上传封装
     * @param maxfileid
     * @param file
     * @param fname
     * @param flenth
     * @param fsize
     * @param year
     * @return Program
     */
    public static Program uplodeFile(String maxfileid,MultipartFile file,String fname,String flenth,String fsize,String year,String uname){
        Program g = new Program();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        int fileid = 0;
        if(maxfileid!=null&& Integer.parseInt(maxfileid)>0){
            fileid = Integer.parseInt(maxfileid)+1;
        }else{
            fileid = 1;
        }
        int j=0;
        String filename ="";
        if(file!=null ){
            filename = fileid+j+"";
            while(filename.length()<5){
                filename = "0"+filename;
            }
            filename = year.substring(2)+filename;
            if (null != file && !file.isEmpty()) {
                filename =filename+"."+bFileUtil.getFileSuffix(file.getOriginalFilename()); //filename字段
                if(flenth!="NaN"&&!flenth.equals("")){
                    g.setFlenth(flenth);
                }
                g.setFname(fname);
                //保存mp3文件
                String filetype= getFileSuffix(filename);
                String mp3 = null;
                if("mp3".equals(filetype)){
                    mp3 = saveMusic(file,filename);//保存mp3文件
                }else{
                    mp3 = uplodeDocFile(file);//保存doc文件
                }
                g.setFilename(filename);
                g.setAddress(bPathUtil.getClasspath() + bConst.FILEPATHPER+mp3);
                g.setUrls(bConst.FILEPATHPER+mp3);
                g.setUserid(uname);
                g.setCreatedtime(df.format(new Date()));
                g.setIspublic(false);
                g.setIslisten(true);
                if(fsize!=null&&!fsize.equals("")){
                    BigDecimal b   =   new   BigDecimal(fsize);
                    g.setFsize(b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue() );//四舍五入 两位小数
                }
                j++;
            }
        }
        return g;
    }

    /**
     * 删除文件
     * @param filePathAndName
     * @param filePathAndName
     * @return boolean
     */
    public static boolean delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            if(myDelFile.exists()) {
                myDelFile.delete();
                return true;
            }
        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Doc文件上传封装
     * @param file
     * @return docName 保存成功的文件名
     */
    public static String uplodeDocFile(MultipartFile file){
        String filename =file.getOriginalFilename();
        String fileurl="";
        if(file!=null ){
            if ( file != null  && !file.isEmpty()) {
                fileurl = saveDoc(file,filename);
            }
        }
        return fileurl;
    }

    public static String saveDoc(MultipartFile file,String saveName) {
        String  path = bConst.UPLOAD_PATH+"applyfile";
        logger.info(" --- doc保存路径：{}, doc保存名称：{},doc名称：{} --- ", path, saveName,file.getOriginalFilename());
        try {
            File targetFile = new File(path);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(new File(path + "/" + saveName));  //文件保存，写入硬盘
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("--- doc保存异常：{} ---" + e.getMessage());
            return null;
        }
        return "profile/applyfile/"+saveName;
    }

}