package com.fh.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;

import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.common.Zone;
import com.qiniu.storage.Configuration;

public class QNUploadUtil {
//    //设置好账号的ACCESS_KEY和SECRET_KEY
//    String ACCESS_KEY = "Mpk_5erj0IjRd0m2vb8QyRTjLWD6c7XKJfQ1WCoE";
//    String SECRET_KEY = "fvdL6dBAtkZYQcL2ba0LUbqyHmH6AEE_PvsL1G11";
//    //要上传的空间
//    String bucketname = "bumofang";
//    //上传到七牛后保存的文件名
//    String key = "my-java.png";
//    //上传文件的路径
//    String FilePath = "/.../...";
//    public static String bucket_name = "bumofang";

    //设置好账号的ACCESS_KEY和SECRET_KEY
    String ACCESS_KEY = "SrxiPEEMEqBBZsPuqHhm4Ob6EtQdqJARhcDvTzOM";
    String SECRET_KEY = "crQXP2Eq_WkG1CRisUCP_CQcR-J5Vu_JCjPSyPLu";
    //要上传的空间
    //先上传到upload然后在uploadCompelete接口copy到其他空间
    String bucketname = "upload";

    String imgD3dDomain = "http://p5nv2faie.bkt.clouddn.com/";
    String imgProductDomain = "http://p5nvy298n.bkt.clouddn.com/";
    String imgSceneDomain = "http://p5nvonxfv.bkt.clouddn.com/";
    String uploadDomain = "http://p5tnsmjdj.bkt.clouddn.com/";

    //上传到七牛后保存的文件名
    String key = "my-java.png";
    //上传文件的路径
    String FilePath = "/.../...";
    public static String bucket_name = "upload";

    //密钥配置
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    // Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    Zone z = Zone.autoZone();
    Configuration c = new Configuration(z);

    //创建上传对象
    UploadManager uploadManager = new UploadManager(c);

    //设置callbackUrl以及callbackBody,七牛将文件名和文件大小回调给业务服务器
    public String getUpToken() {
//        return auth.uploadToken(bucketname, null, 3600, new StringMap()
//                .put("callbackUrl", "http://47.100.116.158/upload/callback")
//                .put("callbackBody", "filename=$(fname)&filesize=$(fsize)"));

        return auth.uploadToken(bucketname);
    }

    public String getUploadDomain(){
        return uploadDomain;
    }

    public String getImgProductDomain(){
        return imgProductDomain;
    }

    public String getImgD3dDomain(){
        return imgD3dDomain;
    }

    public String getImgSceneDomain(){
        return imgSceneDomain;
    }

    public void getList(String bucket) throws QiniuException{
        try {
            Zone z = Zone.zone0();
            Configuration c = new Configuration(z);

            BucketManager bucketManager = new BucketManager(auth, c);
            String[] domainLists = bucketManager.domainList(bucket);
            for(String domain : domainLists){
                System.out.print(domain);
            }
        } catch (QiniuException e) {

        }
    }

    public BucketManager getBucketManager(){
        Zone z = Zone.zone0();
        Configuration c = new Configuration(z);
        BucketManager bucketManager = new BucketManager(auth, c);
        return bucketManager;
    }

    public void upload() throws IOException {
        try {
            //调用put方法上传
            Response res = uploadManager.put(FilePath, null, getUpToken());
            //打印返回的信息
            System.out.println(res.bodyString());
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
    }



}