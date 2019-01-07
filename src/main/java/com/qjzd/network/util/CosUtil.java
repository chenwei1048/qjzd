package com.qjzd.network.util;

import cn.hutool.core.util.StrUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.utils.IOUtils;
import com.qjzd.network.config.ParamConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * @Author:
 * @Description:
 * @Date Create on 14:46 2018/12/13
 * @MOdifyBy:
 * @parameter
 */
public class CosUtil {

    public static String SECRETID="AKID9hh1aAkhFhXusPxGXvnsz3xhKnY2rpKe";
    public static String SECRETKEY="HhP6EE4vNgVB7y2RL9kU2BhDvONoUk2b";
    public static String BUCKETNAME="qjcd-1255952474";
    public static String REGIONID="ap-shanghai";

    /**
     * * 初始化CosClient相关配置， appid、accessKey、secretKey、region * @return
     */
    public static COSClient getCosClient() {
        // 1 init userInfo (secretId, secretKey)
        // COSCredentials cred = new BasicCOSCredentials(APPID,ACCESSKEY,
        // SECRETKEY);
        COSCredentials cred = new BasicCOSCredentials(SECRETID, SECRETKEY); // 不传APPID也可以，APPID和ACCESSKE已经关联过
        // 2 set bucket region
        ClientConfig clientConfig = new ClientConfig(new Region(REGIONID));
        // 3 init cosclient
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket name protocol must be {name}-{appid}
        return cosclient;
    }

    /**
     * 上传文件
     *
     * @return
     */
    public static String uploadFile(String fileURL, String fileName) {
        InputStream inputStream = null;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        if (fileURL.startsWith("http") || fileURL.startsWith("https")) {
            try {
                URL url = new URL(fileURL);
                inputStream = url.openStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                inputStream = new FileInputStream(fileURL);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } // 绝对路径和相对路径都OK
        }
        try {
            IOUtils.copy(inputStream, output);
        } catch (IOException e2) {
            e2.printStackTrace();
        }

        return uploadFile(output.toByteArray(), fileName);

    }

    public static String uploadFile(byte[] bytes, String fileName) {
        String backUrl = "";
        String key = fileName;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(bytes.length);
        PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKETNAME, key, new ByteArrayInputStream(bytes),
                metadata);
        // 设置存储类型, 默认是标准(Standard), 低频(standard_ia)
        putObjectRequest.setStorageClass(StorageClass.Standard);
        COSClient cc = getCosClient();
        try {
            PutObjectResult putObjectResult = cc.putObject(putObjectRequest);
            String res = putObjectResult.getETag();
            // putobjectResult会返回文件的etag
            Date expiration = new Date(System.currentTimeMillis() + 5 * 60 * 10000);
            URL url = cc.generatePresignedUrl(BUCKETNAME, key, expiration);
            backUrl = url.toString();
            backUrl = StrUtil.sub(backUrl,0,backUrl.indexOf("?"));
            return backUrl;
        } catch (CosServiceException e) {
            e.printStackTrace();
        } catch (CosClientException e) {
            e.printStackTrace();
        }
        // 关闭客户端
        cc.shutdown();

        return backUrl;
    }

    public static String uploadFile(ByteArrayInputStream input, String fileName) {
        byte[] bytes = null;
        try {
            bytes = IOUtils.toByteArray(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadFile(bytes, fileName);
    }

    /**
     * * 下载文件 * @param bucketName * @param key * @return
     */

    public static String downLoadFile(String bucketName, String key) {
        File downFile = new File("D:/downloadTest/czy_test.png");

        COSClient cc = getCosClient();
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
        ObjectMetadata downObjectMeta = cc.getObject(getObjectRequest, downFile);
        cc.shutdown();
        String etag = downObjectMeta.getETag();

        return etag;
    }

    /**
     * * 删除文件 * @param bucketName * @param key
     */
    public static void deleteFile(String bucketName, String key) {
        COSClient cc = getCosClient();
        try {
            cc.deleteObject(bucketName, key);
        } catch (CosClientException e) {
            e.printStackTrace();
        } finally {
            cc.shutdown();
        }
    }

    /**
     * * 判断桶是否存在 * @param bucketName * @return * @throws CosClientException
     * * @throws CosServiceException
     */
    public static boolean doesBucketExist(String bucketName) throws CosClientException {
        COSClient cc = getCosClient();
        boolean bucketExistFlag = cc.doesBucketExist(bucketName);
        return bucketExistFlag;
    }

    /**
     * * 查看桶文件 * @param bucketName * @return * @throws CosClientException
     * * @throws CosServiceException
     */
    public ObjectListing listObjects(String bucketName) throws CosClientException {
        COSClient cc = getCosClient();
        // 获取 bucket 下成员（设置 delimiter）
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        listObjectsRequest.setBucketName(bucketName);
        // 设置 list 的 prefix, 表示 list 出来的文件 key 都是以这个 prefix 开始
        listObjectsRequest.setPrefix("");
        // 设置 delimiter 为/, 即获取的是直接成员，不包含目录下的递归子成员
        listObjectsRequest.setDelimiter("/");
        // 设置 marker, (marker 由上一次 list 获取到, 或者第一次 list marker 为空)
        listObjectsRequest.setMarker("");
        // 设置最多 list 100 个成员,（如果不设置, 默认为 1000 个，最大允许一次 list 1000 个 key）
        listObjectsRequest.setMaxKeys(100);
        ObjectListing objectListing = cc.listObjects(listObjectsRequest);
        // 获取下次 list 的 marker
        String nextMarker = objectListing.getNextMarker();
        // 判断是否已经 list 完, 如果 list 结束, 则 isTruncated 为 false, 否则为 true
        boolean isTruncated = objectListing.isTruncated();
        List<COSObjectSummary> objectSummaries = objectListing.getObjectSummaries();
        for (COSObjectSummary cosObjectSummary : objectSummaries) {
            // get file path
            String key = cosObjectSummary.getKey();
            // get file length
            long fileSize = cosObjectSummary.getSize();
            // get file etag
            String eTag = cosObjectSummary.getETag();
            // get last modify time
            Date lastModified = cosObjectSummary.getLastModified();
            // get file save type
            String StorageClassStr = cosObjectSummary.getStorageClass();
        }
        return objectListing;
    }

    public static InputStream getImageStream(String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestMethod("GET");
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                System.out.println("进入图片获取***" + inputStream);
                return inputStream;
            }
        } catch (IOException e) {
            System.out.println("获取网络图片出现异常，图片路径为：" + url);
            e.printStackTrace();
        }
        return null;
    }

    private static byte[] readBytes(InputStream is) throws Exception {

        if ((is == null) || (is.available() < 1)) {
            return new byte[0];
        }
        byte[] buff = new byte[8192];
        byte[] result = new byte[is.available()];

        BufferedInputStream in = new BufferedInputStream(is);
        int pos = 0;
        int nch;
        while ((nch = in.read(buff, 0, buff.length)) != -1) {
            // int nch;
            pos += nch;
        }
        System.out.println("byte长度为：" + result.length);
        in.close();
        return result;
    }

    /**
     * 获取网络文件的输入流
     *
     * @param urlStr
     * @return
     */
    public static InputStream getInputStreamByUrl(String urlStr) {
        DataInputStream in = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            in = new DataInputStream(conn.getInputStream());
        } catch (IOException e) {

        }
        System.out.println("转换完成" + in);
        return in;
    }

    public static byte[] toByteArray(InputStream in) throws IOException {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024 * 4];
        int n = 0;
        while ((n = in.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    public static void main(String[] arg){
        System.out.println(uploadFile("D:/zbfuben.jpg","zb2as?？sgasg.jpg"));
    }
}
