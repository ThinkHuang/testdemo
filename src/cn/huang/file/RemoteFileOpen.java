package file;

import java.io.File;

/**
 * 获取不到远端文件
 * @author Administrator
 *
 */
public class RemoteFileOpen {
    
    public static void main(String[] args) {
        String url = "https://jijifile.jijitec.com/20190826.png";
        File file = new File(url);
        System.out.println();
    }
}
