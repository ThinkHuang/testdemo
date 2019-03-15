package cn.huang.nio;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class FilesTest
{
    public static void main(String[] args)
    {
        /**
         * 检查文件是否存在
         */
//        Path path = Paths.get("data/logging.properties");
//        //检查path路径是否存在，并且不检查链接符号文件
//        boolean pathExists = Files.exists(path, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
        
        /**
         * 创建目录
         */
//        Path path = Paths.get("data/subdir");
//        try
//        {
//            Path newDir = Files.createDirectory(path);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        
        /**
         * 文件复制
         */
//        Path sourcePath = Paths.get("data/logging.properties");
//        Path destinationPath = Paths.get("data/logging-copy.properties");
//        try
//        {
//            Files.copy(sourcePath, destinationPath);
//            //强制覆盖掉已经存在的文件
//            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        
        /**
         * 移动文件位置
         */
//        Path sourcePath      = Paths.get("data/logging-copy.properties");
//        Path destinationPath = Paths.get("data/subdir/logging-moved.properties");
//        try
//        {
//            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        
        /**
         * 文件删除
         */
//        Path path = Paths.get("data/subdir/logging-moved.properties");
//        try {
//            Files.delete(path);
//        } catch (IOException e) {
//            //deleting file failed
//            e.printStackTrace();
//        }
        
        /**
         * 目录过滤
         */
//        Path root = Paths.get("data");
//        Files.walkFileTree(root, new FileVisitor() {
//
//            访问目录前
//            @Override
//            public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException
//            {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            访问文件时
//            @Override
//            public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException
//            {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            访问文件失败时
//            @Override
//            public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException
//            {
//                // TODO Auto-generated method stub
//                return null;
//            }
//
//            访问目录成功后
//            @Override
//            public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException
//            {
//                // TODO Auto-generated method stub
//                return null;
//            }
//            
//        });
        
        /**
         * 使用walkFileTree完成文件查找
         */
//        Path root = Paths.get("data");
//        String target = File.separator + "README.txt";
//        try
//        {
//            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
//                
//                @Override
//                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    String fileString = file.toAbsolutePath().toString();
//                    if(fileString.endsWith(target)) {
//                        System.out.println("file find at path:" + file.toAbsolutePath());
//                        return FileVisitResult.TERMINATE;
//                    }
//                    return FileVisitResult.CONTINUE;
//                }
//            });
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
        
        /**
         * 使用walkFileTree完成文件夹的删除
         */
        Path path = Paths.get("data/to-delete");
        try
        {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(path);
                    return FileVisitResult.CONTINUE;
                }
                
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException e) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
