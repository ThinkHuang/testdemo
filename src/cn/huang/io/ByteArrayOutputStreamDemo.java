package io;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class ByteArrayOutputStreamDemo
{
    public static void main(String[] args)
    {
        //test1();
        //test2();
        test3();
    }
    
    private static void test1()
    {
        /**
         * 主要是为了测试ByteArrayOutputStream对中文的write是否会乱码
         */
        OutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = {123, 34, 99, 111, 110, 116, 101, 110, 116, 34, 58, 34, 63, 63, 63, 63, 63, 34, 44, 34, 99, 111, 109, 109, 101, 110, 116, 73, 100, 34, 58, 50, 56, 44, 34, 100, 121, 110, 97,
                109, 105, 99, 73, 100, 34, 58, 50, 53, 125};
        try
        {
            baos.write(bytes);
            System.out.println(baos.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                baos.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private static void test2()
    {
        /**
         * 下面开始测试OutputStreamWriter对中文write是否会乱码
         */
        String content = "{\"content\":\"测试一二三\",\"commentId\":28,\"dynamicId\":25}";
        OutputStream fops = null;
        Writer osw = null;
        try
        {
            fops = new FileOutputStream(new File("d://test.txt"));
            osw = new OutputStreamWriter(fops, "UTF-8");
            osw.write(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                osw.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 测试乱码菱形出现的方式
     * @throws Exception 
     */
    private static void test3()
    {
        String target = "中国人";
        byte[] bytes;
        try
        {
            bytes = target.getBytes("GBK");
            String dest = new String(bytes, "UTF-8");
            System.out.println(dest);
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }
}
