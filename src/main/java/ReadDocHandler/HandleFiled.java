package ReadDocHandler;

import java.io.File;
import java.util.List;

/**
 * Created by zhuhaoju on 2018/1/30.
 */
public class  HandleFiled{

    public static void main(String[] args) {

        String path = HandleFiled.class.getResource("").getPath();
        path = path.replaceAll("/calculate.jar!/temp/","").replaceAll("file:/","");
        File parentFile = new File(path).getParentFile();
        handlerAllFile(new File(parentFile.getPath() +"/before"),parentFile.getPath());
    }

    public static void handlerAllFile(File file,String parentFilePath){

        if (file.isDirectory()){
            File[] files = file.listFiles();
            for (File childFile : files) {
                handlerAllFile(childFile,parentFilePath);
            }
        }else {
            if(file.getName().endsWith("doc") || file.getName().endsWith("docx")){
                try{
                    List<String> list = ReadDocUtil.readDoc(file.getAbsolutePath());
                    List<Data> dataList = TextHandleHelper.handler(list);
                    WirteDocUtil.writeDoc(newFilePath(parentFilePath,file.getName()),dataList);
                }catch (Exception e){
                    e.printStackTrace();
                    System.out.println("文件解析异常" + file.getName());
                }
            }
        }
    }

    public static String newFilePath(String parentFilePath,String fileName){
        File file = new File(parentFilePath +"/after");
        if(file.isAbsolute()){
            file.mkdirs();
        }
        return file.getAbsolutePath() +"/"+ fileName.split("\\.")[0]+ ".docx";
    }


}
