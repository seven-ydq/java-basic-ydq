# 写在前面

嗯，之前没具体学习过JSON相关知识，最近整理文档，顺便做个总结。

本篇文章实现了Java读取本地json文件功能——使用commons.io和fastjson实现。



# 所需jar包：

commons-io-2.6.jar；

fastjson-1.2.28.jar；



# json文件：

books.xml，位于src根目录下：

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
    "price": 30.00
  	},
    {
      "title": "Harry Potter",
      "author": "J K. Rowling",
      "year": "2005",
      "price": 29.99
    },
    {
      "title": "Learning JSON",
      "author": "Erik T. Ray",
      "year": "2005",
      "price": 39.95
    }
  ]
}
```





# Java代码：

```java
package books;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class GetJson {

	public static void main(String[] args) {
		try {
//			获取JSON文件内容，转化为字符串类型
			String jsonStr = FileUtils.readFileToString(new File("src/books.json"), "UTF-8");
//			将字符串转化为json
			JSONObject jsonObject = JSON.parseObject(jsonStr);

//			获取单个属性值
			String message = (String) jsonObject.get("message");
			System.out.println("***message***");
			System.out.println("message= " + message);
			
//			获取对象值
			JSONObject info = jsonObject.getJSONObject("info");
			String fileType = info.getString("fileType");
			String fileUsage = info.getString("fileUsage");
			System.out.println("***info***");
			System.out.println("    fileType=" + fileType);
			System.out.println("    fileUsage=" + fileUsage);
			
//			获取数组值
			JSONArray jsonArray = jsonObject.getJSONArray("books");
			System.out.println("***books***");

			for(int i = 0; i < jsonArray.size(); i++) {
				System.out.println("***book***");
				JSONObject jObject = (JSONObject) jsonArray.get(i);
				String title = jObject.getString("title");
				String author = jObject.getString("author");
				String year = jObject.getString("year");
				double price = jObject.getDoubleValue("price");
				
				System.out.println("    title=" + title);
				System.out.println("    author=" + author);
				System.out.println("    year=" + year);
				System.out.println("    price=" + price);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

```



## 输出结果：

```
***message***
message= 这是一个中文测试
***info***
    fileType=json
    fileUsage=我在哪里？我要做什么？
***books***
***book***
    title=Everyday Italian
    author=Giada De Laurentiis
    year=2005
    price=30.00
***book***
    title=Harry Potter
    author=J K. Rowling
    year=2005
    price=29.99
***book***
    title=Learning JSON
    author=Erik T. Ray
    year=2005
    price=39.95
```

