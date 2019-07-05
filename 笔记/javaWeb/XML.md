# XML简介

全称：Extensible Markup Language，可拓展标记语言。

用途：传输和存储数据。（附：HTML是用来显示数据）





# XML文档结构

XML 文档遵循树结构。即：必须包含根元素，该元素是所有其他元素的父元素。

XML 文档中的元素形成了一棵文档树。这棵树从根部开始，并扩展到树的最底端。



举个栗子：

```xml
<bookstore>
    <book category="COOKING">
      <title lang="en">Everyday Italian</title> 
      <author>Giada De Laurentiis</author> 
      <year>2005</year> 
      <price>30.00</price> 
    </book>
    <book category="CHILDREN">
      <title lang="en">Harry Potter</title> 
      <author>J K. Rowling</author> 
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



# XML语法规则

- 所有 XML 元素都须有关闭标签
- XML 标签对大小写敏感
- XML 必须正确地嵌套（父子关系明确）
- XML 文档必须有根元素
- XML 的属性值须加引号
- 在 XML 中，空格会被保留



