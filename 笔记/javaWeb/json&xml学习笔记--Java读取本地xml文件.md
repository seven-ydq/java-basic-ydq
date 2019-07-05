# 写在前面

嗯，之前没具体学习过XML相关知识，最近整理文档，顺便做个总结。

本篇文章实现了Java读取本地xml文件功能，使用dom4j实现。



# 所需jar包：

dom4j-1.6.1.jar



# xml文件：

位于src根目录下：

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<bookstore>
    <book category="COOKING">
      <title lang="en">Everyday Italian</title> 
      <author>Giada De Laurentiis</author> 
      <year>2005</year> 
      <price>30.00</price> 
    </book>
    <book category="CHILDREN">
      <title lang="en">哈利波特</title> 
      <author>J·K·罗琳</author> 
      <year>2005</year> 
      <price>29.99</price> 
    </book>
    <book category="WEB">
      <title lang="en">Learning XML</title> 
      <author>Erik T. Ray</author> 
      <year>2003</year> 
      <price>39.95</price> 
    </book>
</bookstore>
```



# Java代码：

```java
package books;

import java.io.File;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class GetXML {

	public static void main(String[] args) {
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(new File("src/books.xml"));
			Element element = document.getRootElement();
            //只获取book元素下的内容
			Iterator<Element> iterator = element.elementIterator("book");
			while (iterator.hasNext()) {
				Element ele = iterator.next();
				System.out.println("title: " + ele.elementText("title"));
				System.out.println("author: " + ele.elementText("author"));
				System.out.println("year: " + ele.elementText("year"));
				System.out.println("price: " + ele.elementText("price"));
				System.out.println("***************************");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}

```



## 输出结果：

```
title: Everyday Italian
author: Giada De Laurentiis
year: 2005
price: 30.00
***************************
title: 哈利波特
author: J·K·罗琳
year: 2005
price: 29.99
***************************
title: Learning XML
author: Erik T. Ray
year: 2003
price: 39.95
***************************
```

