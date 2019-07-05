# 写在前面：

这里使用的是耗费理解力最少的写法，不代表最优解或最常用解。



# XML转JSON：

这个功能比JSON转XML常用。



## book.xml

准备好，放于src根目录下：

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
      <title>哈利波特</title> 
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



## jar包：

commons-io-2.6.jar；

org.json-2.0.jar；



## Java代码：

```java
package books;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

public class XmlToJson {
	
	public static String xmlToJson() {
		try {
			FileInputStream inputStream = new FileInputStream(new File("src/books.xml"));
			String xmlStr = IOUtils.toString(inputStream,"UTF-8");
		    JSONObject object = XML.toJSONObject(xmlStr);
		    return object.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    return null;
	}
	
	public static void main(String[] args) {
		System.out.println(xmlToJson());
	}
}

```



### 输出结果：

```json
{"bookstore":{"book":[{"year":2005,"author":"Giada De Laurentiis","price":30,"category":"COOKING","title":{"lang":"en","content":"Everyday Italian"}},{"year":2005,"author":"J·K·罗琳","price":29.99,"category":"CHILDREN","title":{"lang":"en","content":"哈利波特"}},{"year":2003,"author":"Erik T. Ray","price":39.95,"category":"WEB","title":{"lang":"en","content":"Learning XML"}}]}}
```



美美哒格式化之后：

```json
{
	"bookstore": {
		"book": [{
			"year": 2005,
			"author": "Giada De Laurentiis",
			"price": 30,
			"category": "COOKING",
			"title": {
				"lang": "en",
				"content": "Everyday Italian"
			}
		}, {
			"year": 2005,
			"author": "J·K·罗琳",
			"price": 29.99,
			"category": "CHILDREN",
			"title": {
				"lang": "en",
				"content": "哈利波特"
			}
		}, {
			"year": 2003,
			"author": "Erik T. Ray",
			"price": 39.95,
			"category": "WEB",
			"title": {
				"lang": "en",
				"content": "Learning XML"
			}
		}]
	}
}
```

嗯，基本上没什么问题，可以直接用了。



# JSON转XML：

## books.json

准备好，放于src根目录下：

```json
{
  "message": "这是一个中文测试",
  "info": {
    "fileType": "json",
    "fileUsage": "我在哪里？我要做什么？"
  },
  "books": [{
    "title": "Everyday Italian",
    "author": "Giada De Laurentiis",
    "year": "2005",
    "price": "30.00"
    },
    {
      "title": "Harry Potter",
      "author": "J K. Rowling",
      "year": "2005",
      "price": "29.99"
    },
    {
      "title": "Learning JSON",
      "author": "Erik T. Ray",
      "year": "2005",
      "price": "39.95"
    }
  ]
}
```



## jar包：

commons-io-2.6.jar；

org.json-2.0.jar；



## Java代码：

注意这里的jsonToXmlstr(JSONObject jsonObject, StringBuffer buffer )...

```java
package books;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class JsonToXml {

	public static String jsonToXmlstr(JSONObject jsonObject, StringBuffer buffer ){
		Set<Map.Entry<String, Object>> set = jsonObject.entrySet();
		Iterator<Map.Entry<String, Object>> iterator = set.iterator();
		while (iterator.hasNext()){
			Map.Entry<String, Object> entry = iterator.next();
			if (entry.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONObject")){
				buffer.append("<" + entry.getKey() + ">");
				JSONObject jo = jsonObject.getJSONObject(entry.getKey());
				jsonToXmlstr(jo, buffer);
				buffer.append("</" + entry.getKey() + ">");
			} else if(entry.getValue().getClass().getName().equals("com.alibaba.fastjson.JSONArray")){
				JSONArray ja = jsonObject.getJSONArray(entry.getKey());
				for (int i = 0; i < ja.size(); i++) {
					buffer.append("<" + entry.getKey() + ">");
					JSONObject joChild = ja.getJSONObject(i);
					jsonToXmlstr(joChild, buffer);
					buffer.append("</" + entry.getKey() + ">");
				}
			} else if(entry.getValue().getClass().getName().equals("java.lang.String")){
				buffer.append("<" + entry.getKey() + ">" + entry.getValue());
				buffer.append("</" + entry.getKey() + ">");
			}
		}
		return buffer.toString();
	}

	public static String jsonToXml() {
		try {
			String jsonStr = FileUtils.readFileToString(new File("src/books.json"), "UTF-8");
			JSONObject jsonObject = JSON.parseObject(jsonStr);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");

			return jsonToXmlstr(jsonObject, buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(jsonToXml());
	}

}

```



**注意：**

​	这里jsonToXmlstr方法遍历非字符串数据类型时，可能造成数据丢失，因为叶子结点数据类型的判断条件是equals("java.lang.String")嘛 U know~



### 输出结果：

```xml
<?xml version="1.0" encoding="utf-8"?><books><year>2005</year><author>Giada De Laurentiis</author><price>30.00</price><title>Everyday Italian</title></books><books><year>2005</year><author>J K. Rowling</author><price>29.99</price><title>Harry Potter</title></books><books><year>2005</year><author>Erik T. Ray</author><price>39.95</price><title>Learning JSON</title></books><message>这是一个中文测试</message><info><fileUsage>我在哪里？我要做什么？</fileUsage><fileType>json</fileType></info>
```



开森哒格式化之后：

```xml
<?xml version="1.0" encoding="utf-8"?>
<books>
	<year>2005</year>
	<author>Giada De Laurentiis</author>
	<price>30.00</price>
	<title>Everyday Italian</title>
	</books>
<books>
	<year>2005</year>
	<author>J K. Rowling</author>
	<price>29.99</price>
	<title>Harry Potter</title>
</books>
<books>
	<year>2005</year>
	<author>Erik T. Ray</author>
	<price>39.95</price>
	<title>Learning JSON</title>
</books>

<message>这是一个中文测试</message>

<info>
	<fileUsage>我在哪里？我要做什么？</fileUsage>
	<fileType>json</fileType>
</info>

```



——发现一个开森不起来的问题：

​	代码完美地将json数据转化为了xml格式，但是这并不是一个符合xml文档结构的xml。

​	原因：xml是树形结构，是有根元素的。如果原生的json数据不遵循这个原则，那么生成的xml文档就是不规范的，需要开发者根据具体情况改进上述java代码，或生成结果后再二次处理。