package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileLineDiff {
 
    public static void main(String[] args) {
        String path1 = "C:\\Users\\Administrator\\Desktop\\new 1.txt";
        String path2 = "C:\\Users\\Administrator\\Desktop\\new 3.txt";
        
        // 文件1的内容列表
        List<String> list1 = readListFromFile(path1);
        // 文件2的内容列表
        List<String> list2 = readListFromFile(path2);
        if (null != list1 && null != list2) {
            List<String> file1Diff = new ArrayList<>();
            for (String s : list1) {
                if (!list2.contains(s)) file1Diff.add(s);
            }
            System.out.println("文件1中的不同行为：" + file1Diff);
            // 标记文件2中的不同点
            List<String> file2Diff = new ArrayList<>();
            for (String s : list2) {
                if (!list1.contains(s)) file2Diff.add(s);
            }
            System.out.println("文件2中的不同行为：" + file2Diff);
        } else if(null != list1 || null != list2){
            System.out.println("文件" + ((null == list1) ? "1" : "2") + "为空，请先检查文件内容"); 
        } else {
            System.out.println("源文件和目标文件均为空，请先检查文件内容"); 
        }
        
    }
    
    private static List<String> readListFromFile(String path) {
        if (null == path || "".equals(path)) {
            return null;
        }
        List<String> result = new ArrayList<>();
        // 读取文件内容
        String tmp = null;
        try (BufferedReader buffer1 = new BufferedReader(new FileReader(new File(path)))){
            while (null != (tmp = buffer1.readLine())) {
                result.add(tmp);
            }
        } catch (IOException e) {
            System.err.printf("读写文件异常 " + e.getMessage());
        }
        return result;
    }
}
