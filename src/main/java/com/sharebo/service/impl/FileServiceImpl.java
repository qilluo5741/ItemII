package com.sharebo.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Service;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.sharebo.config.OssConfig;
import com.sharebo.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Administrator
 */
@Service
public class FileServiceImpl implements FileService{
  private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
  private  OSSClient ossClient;
  /**
   *文件上传
   */
  public String upload(File file, String folder, String name, String contentType, String format) {
	log.debug("Upload Input Stream To OSS With File [{}].", name);
    if (StringUtils.isBlank(name)) {
    	name = UUID.randomUUID().toString();
    	String fileExt = FilenameUtils.getExtension(file.getName());
	    if (StringUtils.isNotBlank(fileExt))
	        fileExt = fileExt + "." + fileExt;
	    }
    try{
      return upload(new FileInputStream(file), folder, name, contentType, format);
    }catch (FileNotFoundException e){
      throw new RuntimeException("Upload File Error Caused, File Not Found.", e);
    }
  }
  public String upload(File file, String name){
    return upload(file, null, name, null, null);
  }
  public String upload(InputStream in, String name){
    return upload(in, null, name, null, null);
  }
  public String upload(InputStream in, String folder, String name, String contentType, String fileFormat){
    return upload(in, folder, name, contentType, fileFormat, null);
  }
  /**
   * w文件上传
   */
  public String upload(InputStream in, String folder, String name, String contentType, String fileFormat, String downloadName){
    if (in == null) {
      throw new IllegalArgumentException("Upload Input Stream Must Not Be Null.");
    }
    log.debug("Upload Input Stream To OSS With Name [{}].", name);
    boolean useFolder = true;
    String basePath = basePath();
    if (StringUtils.isNotBlank(name)) {
      log.debug("Name [{}] Is Not Blank.", name);
      if (name.startsWith(basePath)) {
        log.debug("Name Starts With Base Path [{}], Trim.", basePath);
        name = name.replaceFirst(basePath, "");
        useFolder = false;
      }
    }else{
      log.debug("No Name Specified, Use UUID Name.");
      name = UUID.randomUUID().toString();
    }if (useFolder) {
      log.debug("Name Does Not Start With Base Path [{}], Prepend Folder.", basePath);
      if (StringUtils.isNotBlank(folder)) {
        log.debug("Custom Folder [{}] Given.", folder);
        if (!folder.endsWith("/")) {
          folder = folder + "/";
        }
        name = folder + name;
        log.debug("Prepend Custom And Date Folder, Name Result [{}].", name);
      }else{
        name = DateFormatUtils.format(new Date(), "yyyyMMdd") + "/" + name;
        log.debug("No Custom Fould Found, Prepend Date Folder [{}].", name);
      }
    }
    log.debug("Upload Input Stream To OSS With Name [{}].", name);
    ObjectMetadata objMeta = new ObjectMetadata();
    if (StringUtils.isNotBlank(contentType)) {
      objMeta.setContentType(contentType);
    }if(StringUtils.isNotBlank(fileFormat)) {
      Map<String,String> meta = new HashMap<String,String>();
      meta.put("file-format", fileFormat);
      objMeta.setUserMetadata(meta);
    }
    if (StringUtils.isNotBlank(downloadName)) {
      try {
        downloadName = URLEncoder.encode(downloadName, "UTF-8");
      }catch (UnsupportedEncodingException e) {
        throw new RuntimeException("Update File [" + name + "] Encde Download Name [" + downloadName + "] Error Caused.", e);
      }
      String dispo = "attachment; filename=\"" + downloadName + "\"; filename*=utf-8''" + downloadName;
      log.debug("Set Upload File Content-Disposition [{}].", dispo);
      objMeta.setContentDisposition(dispo);
    }
    try {
      objMeta.setContentLength(in.available());
      this.ossClient.putObject(OssConfig.getOssBucket(), OssConfig.getOssFolder() + name, in, objMeta);
    }catch (Exception e) {
      throw new RuntimeException("Update File [" + name + "] Error Caused.", e);
    }finally{
      IOUtils.closeQuietly(in);
    }
    name = basePath() + name;
    log.debug("Upload Result [{}].", name);
    return name;
  }
/**
 * 获取文件
 * return 文件
 */
  public File get(String name){
    File file = new File(FileUtils.getTempDirectory(), UUID.randomUUID() + "_" + name);
    this.ossClient.getObject(new GetObjectRequest(OssConfig.getOssBucket(), OssConfig.getOssFolder()+ name.replaceFirst(basePath(), "")), file);
    return file;
  }
/**
 *删除文件
 *name  文件名字
 */
  public void delete(String name){
    if (StringUtils.isNotBlank(name)) {
      log.info("Delete File [{}].", name);
      this.ossClient.deleteObject(OssConfig.getOssBucket(), OssConfig.getOssFolder() + name.replaceFirst(basePath(), ""));
    }else{
      log.info("Delete File, File Name Or URL Is Blank, Ignore.");
    }
  }
/**
 * 总路径
 * return 路径
 */
  public String basePath(){
    return "http://" +OssConfig.getOssBucket() + "." + OssConfig.getOssHost() + "/" +OssConfig.getOssFolder();
  }
  /**
   * 获取文件路径
   * folder 文件夹名字
   * name :文件名
   */
  public String getUrl(String folder, String name){
    String url = basePath();
    if (StringUtils.isNotBlank(folder)) {
      url = url + folder;
    }
    return url + "/" + name;
  }
  /***
   * 获取文件路径
   * name  文件名
   */
  public String getUrl(String name){
    return getUrl(null, name);
  }
  public String uploadAvatar(String userId, InputStream ins){
    return uploadImage("avatar", userId, ins);
  }
  /***
   * 上传文件
   * ins:通过文件流
   * return 返回文件访问路径 文件名你通过uuis命名
   */
  public String uploadImage(InputStream ins){
    return uploadImage("image", UUID.randomUUID().toString(), ins);
  }
 /***
 *获取文件类型
 *ins 文件流 
 */
  public String imageType(InputStream ins){
    ImageInputStream iis = null;
    try {
      try {
        iis = ImageIO.createImageInputStream(ins);
      }
      catch (IOException e) {
        log.warn("Read Image Input Stream [{}] Error Caused.", ins, e);
        return null;
      }
      Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
      String format = null;
      ImageReader reader;
      if (iter.hasNext()) {
        reader = (ImageReader)iter.next();
        try {
          format = reader.getFormatName().toLowerCase();
        }catch (IOException e) {
          log.warn("Get Image Format Name Error Caused.", e);
        }
        reader.dispose();
      }
      else {
        log.info("Input Stream [{}] Is Not Image Input Stream.", ins);
      }
      return format;
    }
    finally {
      try {
		iis.close();
	} catch (IOException e) {
		System.out.println("iis close failure！");
		 log.info("iis close failure！");
	}
      IOUtils.closeQuietly(ins);
    }
  }
  //初始化
  @PostConstruct
  private void init(){
    log.info("Init OSS [{}].",OssConfig.getOssHostInternal());
    this.ossClient = new OSSClient("http://" + OssConfig.getOssHostInternal(), OssConfig.getOssAccount(), OssConfig.getOssPassword());
    if (!this.ossClient.doesBucketExist(OssConfig.getOssBucket())) {
      log.info("OSS Bucket [{}] Does Not Exist, Try To Create.", OssConfig.getOssBucket());
      this.ossClient.createBucket(OssConfig.getOssBucket());
      this.ossClient.setBucketAcl(OssConfig.getOssBucket(), CannedAccessControlList.PublicRead);
    }
    if ((StringUtils.isNotBlank(OssConfig.getOssFolder())) && (!"/".equals(OssConfig.getOssFolder())))
      try {
        /*if (!OosConfig.OSSFOLDER.endsWith("/")) {
        	OosConfig.OSSFOLDER += "/";
        }*/
        this.ossClient.getObjectMetadata(OssConfig.getOssBucket(), OssConfig.getOssFolder());
      }
      catch (OSSException e) {
        if ("NoSuchKey".equals(e.getErrorCode())) {
          log.info("OSS Foler [{}] Does Not Exist, Try To Create.", OssConfig.getOssFolder());
          InputStream bin = new ByteArrayInputStream(new byte[0]);
          ObjectMetadata objectMeta = new ObjectMetadata();
          objectMeta.setContentLength(0L);
          try {
            this.ossClient.putObject(OssConfig.getOssBucket(), OssConfig.getOssFolder(), bin, objectMeta);
          }finally{
            IOUtils.closeQuietly(bin);
          }
        }else {
          throw e;
        }
      }
  }
	/**
	 * 上传图片
	 * @param folder 图片你的文件夹
	 * @param name  图片名字
	 * @param ins 图片字节流
	 * @return 图片上传后的路径
	 */
  private String uploadImage(String folder, String name, InputStream ins){
    byte[] fileBytes = null;
    try {
      fileBytes = IOUtils.toByteArray(ins);
    }catch (IOException e) {
      throw new IllegalArgumentException("Upload Image Read File Input Stream Error Caused.", e);
    }finally
    {
      IOUtils.closeQuietly(ins);
    }
    String format = imageType(new ByteArrayInputStream(fileBytes));
    if (ArrayUtils.contains(new String[] { "jpg", "bmp", "png", "jpeg" }, format))
    {
      return upload(new ByteArrayInputStream(fileBytes), folder, name, "image/" + format, format);
    }
    throw new IllegalArgumentException("Unsupported Image File Format [" + format + "]");
  }
}