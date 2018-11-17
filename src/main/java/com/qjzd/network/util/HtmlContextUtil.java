package com.qjzd.network.util;

import org.htmlparser.Parser;
import org.htmlparser.visitors.TextExtractingVisitor;

public class HtmlContextUtil {


    public static String getContext(String html){
        if(CommonUtils.isNull(html)){
            return null;
        }
        String text = null;
        try {
            Parser  parser = new Parser(html);
            TextExtractingVisitor visitor = new TextExtractingVisitor();
            parser.visitAllNodesWith(visitor);
            text = visitor.getExtractedText();
        }catch (Exception e){
            text=html;
        }
        return text;


    }


    public static String delHtmlTag(String str){
        String newstr = "";
        newstr = str.replaceAll("<[.[^>]]*>","");
        newstr = newstr.replaceAll(" ", "");
        return newstr;
    }
}
