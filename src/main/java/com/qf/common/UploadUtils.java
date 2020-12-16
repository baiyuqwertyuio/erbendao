package com.qf.common;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class UploadUtils {

    @Value("${qiniu.ak}")
    private String ak;
    @Value("${qiniu.sk}")
    private String sk;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.url}")
    private String url;
    public BaseResp upload(MultipartFile multipartFile){
        Configuration cfg = new Configuration(Region.region1());
        UploadManager uploadManager = new UploadManager(cfg);
        BaseResp baseResp = new BaseResp();
        String key = null;
        try {
            byte[] bytes = multipartFile.getBytes();
            Auth auth = Auth.create(ak, sk);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(bytes, key, upToken);
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                baseResp.setCode(100);
                baseResp.setMsg("上传成功");
                baseResp.setData(url+putRet.key);
                return baseResp;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                }
            }
        } catch (UnsupportedEncodingException ex) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        baseResp.setCode(101);
        baseResp.setMsg("上传失败");
        return baseResp;
    }
}
