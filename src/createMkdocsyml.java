import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * 根据某个文件夹生成其mkdocs.yml文件
 */

public class createMkdocsyml {

    public static void main(String[] args) {

        traverseFolder("/Users/lwk/oceanbase/docs/docs/docs-cn",0);

    }

    public static void traverseFolder(String path,int count) {

        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            count++;
            bubbleSort(files);
            if (null == files || files.length == 0) {
                System.out.println("文件夹是空的!");
                return;
            } else {
                for (File file2 : files) {
                    if (file2.isDirectory()) {
                        System.out.println(getSpace(count)+"- : " + file2.getAbsolutePath());
                        traverseFolder(file2.getAbsolutePath(),count);
                    } else {
                        String title = getFileFirstLine(file2.getAbsolutePath());
                        System.out.println(getSpace(count)+"- "+title+": " + file2.getAbsolutePath());
                    }
                }
            }
        } else {
            System.out.println("文件不存在!");
        }

    }

    public static int getNumberForString(String s){

        String res = "";
        char [] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i]))
            {
                res+=chars[i];
            }else {
                break;
            }
        }
        if (res.equals(""))
        {
            return -1;
        }else {
            return Integer.valueOf(res);
        }
    }

    public static void bubbleSort(File arr[]) {

        for(int i =0 ; i<arr.length-1 ; i++) {

            for(int j=0 ; j<arr.length-1-i ; j++) {

                if(getNumberForString(arr[j].getName())>getNumberForString(arr[j+1].getName())) {
                    File temp = arr[j];

                    arr[j]=arr[j+1];

                    arr[j+1]=temp;
                }
            }
        }
    }

    public static String getSpace(int count){
        String res = "";
        for (int i = 0; i < count; i++) {
            res+="  ";
        }
        return res;
    }

    public static String getFileFirstLine(String path){

        File file = new File(path);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String res = reader.readLine();
            reader.close();
            return res;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return "";

    }

}
