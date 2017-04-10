package com.order.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;


public class UploadFile {
    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String ACCESS_KEY = "eXoXrwZ21Gfyh9DCfymt1OJtdRSVZf67djRvC0kA";
    private static String SECRET_KEY = "SUMISZbx2mSqk52Nb7eqd8q2ud2YILIrJQNo2QZU";
    //要上传的空间
    private static String bucketname = "lfeng";
    //上传到七牛后保存的文件名
    private static String key;
    //上传文件的路径
    private static String FilePath;

    //密钥配置
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    ///////////////////////指定上传的Zone的信息//////////////////
    //第一种方式: 指定具体的要上传的zone
    //注：该具体指定的方式和以下自动识别的方式选择其一即可
    //要上传的空间(bucket)的存储区域为华东时
    private static Zone z = Zone.zone0();
    //要上传的空间(bucket)的存储区域为华北时
    // Zone z = Zone.zone1();
    //要上传的空间(bucket)的存储区域为华南时
    // Zone z = Zone.zone2();

    //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
    //Zone z = Zone.autoZone();
    private static Configuration c = new Configuration(z);

    //创建上传对象
    private static UploadManager uploadManager = new UploadManager(c);

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private static String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    /**
     * 上传图片
     * @param path 图片的路径
     * @return 七牛云的url
     * @throws IOException
     */
    public static String upload(String path) throws IOException {
        try {
        	FileInputStream fis = new FileInputStream(new File(path));

        	key = CreateUUID.createUUID().toString() + ".jpg";
        	String yu = "http://oh59g24op.bkt.clouddn.com/" + key;
            //调用put方法上传
            //Response res = uploadManager.put(path, key, getUpToken());
        	Response res = uploadManager.put(fis, key, getUpToken(), null, null);
            //打印返回的信息
            return yu;
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
        return null;
    }
}
