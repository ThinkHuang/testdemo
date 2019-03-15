package nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest
{
    public static void main(String[] args)
    {
        Path path = Paths.get("nio.text");
        System.out.println(path.getFileName());
        //使用normalize()方法可以将路径规范化。也就是将.和..去除
    }
}
