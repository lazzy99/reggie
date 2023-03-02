package com.lzz.reggie.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传与下载
 */

@RestController
@RequestMapping("/common")
public class CommonFile {

    @Value("${reggie.path}")
    private String path;


    @PostMapping("/upload")
    public R<String> upload(MultipartFile file){

        //原始文件名
        String originalFilename = file.getOriginalFilename();
        //截取.jpg的信息 --->abc.jpg
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));
        //使用uuid生成文件名 uuid + .jpg
        String uuidName = UUID.randomUUID().toString()+substring;

        File dir = new File(path);

        if (!dir.exists()){
            dir.mkdirs();
        }
        try {
            file.transferTo(new File(path+uuidName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return R.success(uuidName);
    }

    /**
     * 文件下载
     */

    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
        try {
            //输入流，通过输入流读取文件内容
            FileInputStream fileInputStream = new FileInputStream(new File(this.path+name));
            //输出流，通过输出流将文件写回浏览器，在浏览器显示图片
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("image/jpeg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while((len = fileInputStream.read(bytes)) != -1){
                outputStream.write(bytes,0,len);
                outputStream.flush();
            }
            outputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
