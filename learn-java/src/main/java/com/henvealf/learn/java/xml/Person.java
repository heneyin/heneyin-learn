package com.henvealf.learn.java.xml;

import nu.xom.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 使用开源类库XOM将数据转换为Xml格式
 * Created by Henvealf on 2016/9/6.
 */
public class Person extends ArrayList<Person>{
    private String first, last;
    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }

    // 使用一个Person对象生成一个XML
    public Element getXml() {
        // Element 构造方法，参数为字符串，会生成一个元素，
        // 也就是XML文件中的标签。标签的名字就是你输入的那个字符串。
        Element person =  new Element("person");
        Element firstName = new Element("first");
        // Element对象的appendChild()方法，
        // 参数为字符串时，是设置此元素中的值，也就是标签里的值
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        // 参数为Element对象时，就是在此标签下添加子标签。
        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }

    public Person(Element person) {
        first = person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
    }

    /**
     * 反序列化一个XML文件
     * @param fileName
     */
    public Person(String fileName) throws ParsingException, IOException {
        //使用下面的方法从将XML文件解析成Document对象
        Document doc = new Builder().build(fileName);
        //得到根标签，得到子标签
        Elements elements = doc.getRootElement().getChildElements();
        for(int i = 0; i < elements.size(); i++) {
            add(new Person(elements.get(i)));
        }
    }

    @Override
    public String toString() {
        return first + " " + last;
    }

    public static void format(OutputStream os, Document doc) throws IOException {
        Serializer serializer = new Serializer(os,"ISO-8859-11");
        //首行缩进字符数
        serializer.setIndent(4);
        //文件建议的行数，如果太小，各个标签会挤到一块
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }

    public static void main(String[] args) throws IOException, ParsingException {
        List<Person> people = Arrays.asList(
                new Person("ni hao","nihao"),
                new Person("bu hao","ni22o"),
                new Person("hahah","ndasdao")
        );
        //System.out.println(people);
        Element root = new Element("people");
        for(Person p : people)
            root.appendChild(p.getXml());
        //Document 一整个XML文档。
        Document doc =  new Document(root);
        format(System.out,doc);
        format(new BufferedOutputStream(new FileOutputStream("people.xml")),doc);

        //反序列化XML文件
        Person p = new Person("file:./people.xml");
        System.out.println("反序列化后的Person对象：\n" + p.size());
        for (int i = 0; i < p.size(); i++) {
            Person p1 = p.get(i);
            System.out.println(p1.first + " " + p1.last  );
        }
    }

}
