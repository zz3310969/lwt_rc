package com.bsc.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 汉字转化为拼音
 * @author 韩进城
 *
 */
public class Ch2PinYin 
{
	
	/**
	 * 获得汉字的首字母的缩写
	 * @param chText 汉字
	 * @return 
	 */
	public static String getFirstCh(String chText){
		String convert = ""; 
		for (int j = 0; j < chText.length(); j++) { 
		      char word = chText.charAt(j); 
		      String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word); 
		      if (pinyinArray != null) { 
		    	  convert += pinyinArray[0].charAt(0); 
		      }else { 
		    	  convert += word; 
		      } 
		} 
		return convert; 

	}
	
	/**
	 * 获得汉字的完整拼音
	 * @param chText 汉字
	 * @return   
	 */
	public static String getWholeCh(String chText){
		char[] t1 = null; 
	    t1=chText.toCharArray(); 
	    String[] t2 = new String[t1.length]; 
	    HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat(); 
	    t3.setCaseType(HanyuPinyinCaseType.LOWERCASE); 
	    t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE); 
	    t3.setVCharType(HanyuPinyinVCharType.WITH_V); 
	    String t4=""; 
	    int t0=t1.length; 
	    try { 
	      for(int i=0;i<t0;i++){ 
	    	  //判断是否为汉字字�?
	         if(java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) { 
	              t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);               
	              t4+=t2[0]; 
	         }else{
	        	  t4+=java.lang.Character.toString(t1[i]); 
	         }
	      } 
	      return t4; 
	    }catch (BadHanyuPinyinOutputFormatCombination e1) { 
	    		e1.printStackTrace(); 
	    } 
	    return t4; 
	}
}
