package com.baidu.util;



import java.util.ArrayList;  
import java.util.List;  
  
public class JXPageUtil {  
      
    private String url;  
    private int total;  
    private int currpage;  
    private int pagesize ;  
    private int pagecount;  
    private int pagesplit=7;  
    private int bothnum = pagesplit/2;  
    private StringBuffer buff = new StringBuffer();  
      
    public String getUrl() {  
        return url;  
    }  
    public void setUrl(String url) {  
        this.url = url;  
    }  
    public int getTotal() {  
        return total;  
    }  
    public void setTotal(int total) {  
        this.total = total;  
    }  
    public int getCurrpage() {  
        return currpage;  
    }  
    public void setCurrpage(int currpage) {  
        this.currpage = currpage;  
    }  
    public int getPagesize() {  
        return pagesize;  
    }  
    public void setPagesize(int pagesize) {  
        this.pagesize = pagesize;  
    }  
    public int getPagecount() {  
        return pagecount;  
    }  
    public void setPagecount(int pagecount) {  
        this.pagecount = pagecount;  
    }  
    public int getPagesplit() {  
        return pagesplit;  
    }  
    public void setPagesplit(int pagesplit) {  
        this.pagesplit = pagesplit;  
    }  
    public int getBothnum() {  
        return bothnum;  
    }  
    public void setBothnum(int bothnum) {  
        this.bothnum = bothnum;  
    }  
      
      
      
      
    public JXPageUtil() {  
        // TODO Auto-generated constructor stub  
    }  
      
      
    public JXPageUtil(String url,int total,int currpage) {  
        this.url = url;  
        this.total = total;  
        this.currpage = currpage;  
        this.pagecount=total%pagesize==0 ? total/pagesize : total/pagesize+1;  
        this.checkCurrpage();  
    }  
      
    public JXPageUtil(String url,int total,int currpage,int pagesize) {  
        this.url = url;  
        this.total = total;  
        this.currpage = currpage;  
        this.pagesize=pagesize;  
        if(total!=0){  
            this.pagecount=total%pagesize==0 ? total/pagesize : total/pagesize+1;  
        }else{  
            this.pagecount=1;  
        }  
          
        this.checkCurrpage();  
    }  
      
      
    /** 
     * 验证当前页的合法性 
     */  
    private void checkCurrpage(){  
        if(currpage<=1) currpage =1;  
        if(currpage>=pagecount) currpage = pagecount;  
    }  
      
      
      
      
    /** 
     * 显示单独的某一个文本 
     *  
     */  
    private String showOneTextPage(int page,String str){  
  
        return "<li class='text'><a href='"+url+"?currpage="+page+"' class='text'>"+str+"</a></li>";  
          
    }  
      
    /** 
     * 不能在按的按钮 
     * @param str 
     * @return 
     */  
    private String showOneDisabledPage(String str){  
        return "<li class='disabled'>"+str+"</li>";  
    }  
      
      
    /** 
     * 显示首页 
     */  
      
    private String showIndex(){   
        return this.showOneTextPage(1, "首页");  
    }  
      
    /** 
     * 显示尾页 
     */  
    private String showLast(){  
        return this.showOneTextPage(pagecount, "尾页");  
  
    }  
      
    /** 
     * 显示文本的上一页 
     */  
    private String showTextPrev(){  
        if(currpage==1){  
            return this.showOneDisabledPage("<<上一页");  
        }  
        return this.showOneTextPage(currpage-1, "<<上一页");  
    }  
      
      
    /** 
     * 显示数字的上一页 
     */  
    private String showNumPrev(){  
          
        if(currpage==1){  
            return this.showOneDisabledPage("PREV<<");  
        }  
        return this.showOneTextPage(currpage-1, "PREV<<");  
          
    }  
      
      
    /** 
     * 显示文本的下一页 
     */  
      
    private String showTextNext(){  
        if(currpage==pagecount){  
            return this.showOneDisabledPage("下一页>>");  
        }  
          
        return this.showOneTextPage(currpage+1, "下一页>>");  
          
    }  
      
    /** 
     * 显示数字的下一页 
     */  
      
    private String showNumNext(){  
        if(currpage==pagecount){  
            return this.showOneDisabledPage(">>NEXT");  
        }  
          
        return this.showOneTextPage(currpage+1, ">>NEXT");  
    }  
      
  
    /** 
     * 循环出所有的数字 
     * @param start 
     * @param end 
     * @return 
     */  
    private List<Integer> getNumList(int start,int end){  
        List<Integer> numList = new ArrayList<Integer>();  
          
        for(int i=start;i<=end;i++){  
            numList.add(i);  
        }  
          
        return numList;  
    }  
  
    /**验证判断 
     * 获取一个数字的集合 
     * @return 
     */  
    private List<Integer> showNumList(){  
        List<Integer> numList = new ArrayList<Integer>();  
        if(pagecount>pagesplit){  
            if(currpage-bothnum>=1 && currpage+bothnum<=pagecount){  
                numList=this.getNumList(currpage-bothnum, currpage+bothnum);  
                  
            }  
              
            if(currpage+bothnum>pagecount){  
                numList=this.getNumList(pagecount-pagesplit+1, pagecount);  
                  
            }  
            if(currpage-bothnum<1){  
                numList=this.getNumList(1, pagesplit);  
              
            }  
  
              
        }else if(pagecount<=pagesplit){  
            numList=this.getNumList(1, pagecount);  
              
        }  
          
        return numList;  
      
    }  
      
    /** 
     * 下拉列表框  需用到js配合 
     * @return 
     */  
    public String select(){  
        String str ="<li><select  id='curr'>";  
        for(int i=1;i<=pagecount;i++){  
            if(currpage==i){  
                str+="<option value='"+i+"' selected='selected'>"+i+"</option>";  
            }else{  
                str+="<option value='"+i+"'>"+i+"</option>";  
  
            }  
        }  
        return str+"</select></li>";  
    }  
      
      
      
      
      
      
    /** 
     * 获取一个数字分页 
     * @param page 
     * @return 
     */  
    private String showOneNumPage(int page){  
        if(currpage==page){  
            return "<li class='number'><a class='select' href='"+url+"?currpage="+page+"'>"+page+"</a></li>";  
        }  
          
        return "<li class='number'><a class='number' href='"+url+"?currpage="+page+"'>"+page+"</a></li>";  
          
    }  
      
    /**    
     * 获取数字分页组合 
     * @param numList 
     * @return 
     */  
    private String getNumString(List<Integer> numList){  
        StringBuffer sb = new StringBuffer();  
        for(int i=0;i<numList.size();i++){  
            sb.append(this.showOneNumPage(numList.get(i)));  
        }  
          
        return sb.toString();  
    }  
      
      
      
      
      
    /** 
     * 显示文本分页 
     */  
  
    public String showTextPage(){  
        buff.append("<ul><br/>");  
        buff.append(this.showIndex());  
        buff.append(this.showTextPrev());  
        buff.append(this.showTextNext());  
        buff.append(this.showLast());  
        buff.append("</ul><br/>");  
          
        return buff.toString();  
      
    }  
      
      
    /** 
     * 显示数字分页 
     * @return 
     */  
    public String showNumPage(){  
        buff.append("<ul>");  
        buff.append(this.showIndex());  
        buff.append(this.showNumPrev());  
        buff.append(this.getNumString(this.showNumList()));  
        buff.append(this.showNumNext());  
        buff.append(this.showLast());  
        buff.append(this.textInfo());  
        buff.append(this.textSkip());  
        buff.append(this.select());  
        buff.append("</ul>");  
        return buff.toString();   
    }  
      
      
    public String showpages(){  
          
        buff.append("<ul>");  
        buff.append(this.showIndex());  
        buff.append(this.showNumPrev());  
        buff.append(this.getNumString(this.showNumList()));  
        buff.append(this.showNumNext());  
        buff.append(this.showLast());  
        buff.append(this.textInfo());  
        buff.append("</ul>");  
        return buff.toString();   
    }  
      
      
    public String textInfo(){  
          
        return "<li class='textjs'>" +  
        "<span class='currpage'>"+currpage +"/"+ pagecount+"</span>" +  
        "<span class='pagecount'> 共"+pagecount+"页</span>";  
          
    }  
      
  
  
    /** 
     *跳转 
     * @return 
     */  
    public String textSkip(){  
          
        return  
                "<span class='skip'>跳到第 <input type='text' id='textpage' value='"+currpage+"' /> 页</span>" +  
                "<span class='go' id='textjs'>GO</span>"+         
                "</li>";  
          
    }  
  
      
      
      
      
      
    public static void main(String[] args) {  
        JXPageUtil jx  = new JXPageUtil("fdsfdsfds.action",115,3,10);  
      
        jx.getNumString(jx.showNumList());  
          
    }  
      
}  