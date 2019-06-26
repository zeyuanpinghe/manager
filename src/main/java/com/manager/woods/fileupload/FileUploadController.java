package com.apricot.woods.fileupload;

import com.alibaba.druid.util.StringUtils;
import com.apricot.woods.framework.handler.MyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RequestMapping("/file")
@RestController
public class FileUploadController {

    private final static String filePath = "/opt/apk_package/";
    private  static  String apk_name = "apricot_woods.apk";
    /**
     * 实现文件上传
     * */
//    @RequestMapping("fileUpload")
//    @ResponseBody
    public String fileUpload(@RequestParam("fileName") MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        File dest = new File(filePath + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
        }
    }


    /**
     * 实现多文件上传
     * */
//    @RequestMapping(value="multifileUpload",method= RequestMethod.POST)
    public @ResponseBody String multifileUpload(HttpServletRequest request){

        List<MultipartFile> files = ((MultipartHttpServletRequest)request).getFiles("fileName");

        if(files.isEmpty()){
            return "false";
        }

        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                return "false";
            }else{
                File dest = new File(filePath + "/" + fileName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }


//    @RequestMapping("download")
    public String downLoad(HttpServletRequest request,HttpServletResponse response){

        String fileName = request.getParameter("fileName");
        if(!StringUtils.isEmpty(fileName)){
            apk_name = fileName;
        }
        File file = new File(filePath + "/" + apk_name);
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment;fileName=" + apk_name);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;

            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                int i = bis.read(buffer);
                while(i != -1)
                {
                    os.write(buffer);
                    i = bis.read(buffer);
                }
                os.close();
                bis.close();
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(os != null)
                    os = null;
                if(bis != null)
                    bis = null;
                if(fis != null)
                    fis = null;
            }

        }else{
            throw new MyException("107","resource is not found!");
        }
        return null;
    }

    @RequestMapping("download")
    public void downloadFile(HttpServletRequest req, HttpServletResponse resp) {
//        进行转码后的文件名，用来下载之后的文件名
        download(resp,apk_name);
    }

    /**
     * @param resp
     * @param name         文件真实名字
     */
    public static void download(HttpServletResponse resp, String name) {
        File file = new File(filePath+name);
        resp.reset();
        resp.setContentType("application/octet-stream");
        resp.setCharacterEncoding("utf-8");
        resp.setContentLength((int) file.length());
        resp.setHeader("Content-Disposition", "attachment;filename=" + name);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = resp.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(file));
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @RequestMapping({"/",""})
    public String fileFilter(){
        throw new MyException("107","resource is not found!");
    }

}
