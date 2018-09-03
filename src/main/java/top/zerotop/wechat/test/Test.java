package top.zerotop.wechat.test;

import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

public class Test {
    private String name;
 
    @XStreamOmitField
    private String hidden;
    
    

	public Test(String name, String hidden) {
        this.name = name;
        this.hidden = hidden;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public String getHidden() {
        return hidden;
    }
 
    public void setHidden(String hidden) {
        this.hidden = hidden;
    }
    
    public static void main(String[] args) throws IOException {
        XStream xs = new XStream();
        // xs.processAnnotations(Test.class);
        xs.autodetectAnnotations(true);
        String str = xs.toXML(new Test("dddd", "dd"));
        System.out.println(str);
 
    }
 
}