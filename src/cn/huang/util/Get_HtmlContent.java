package com.huang.util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 
 * @author HUNING
 * 2016-10-14
 * 获取HTML文件 对应的文件内容(HTML和TXT内容)
 *
 */
public class Get_HtmlContent {
	
	/**
	 * 获取HTML里面的纯文本
	 */
	public static String ReplaceHtmlToTxt(String str){
		String txtcontent = str.replaceAll("</?[^>]+>", ""); //剔出<html>的标签  
        txtcontent = txtcontent.replaceAll("<a>\\s*|\t|\r|\n</a>", "");//去除字符串中的空格,回车,换行符,制表符
        
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;
        
        try{
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(txtcontent);
            txtcontent = m_script.replaceAll(""); //过滤script标签

            p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(txtcontent);
            txtcontent = m_style.replaceAll(""); //过滤style标签

            p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(txtcontent);
            txtcontent = m_html.replaceAll(""); //过滤html标签
           
            txtcontent=StringUtil.Html2Text(txtcontent);// 过滤标签
            txtcontent=StringUtil.filterHtml(txtcontent);
       }catch(Exception e){
    	   e.printStackTrace();
       }
       return txtcontent;
	}

    
    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }
    
	public static void main(String[] args) throws Exception    {
		String fileUrl="D:/scxx/workspace/sc-kbs/src/main/webapp/upload/article/1476427932063.html";
		//获取HTML内容
		String content= readFileByLines(fileUrl);
		System.out.println("---HTML---"+content);
		//解析HTML内容信息获取纯文本信息
		String result= ReplaceHtmlToTxt(content);
		System.out.println("---TEXT---"+result);
    } 
    
}
