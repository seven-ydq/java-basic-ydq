# inline元素

inline元素又被称为：内联元素或行内元素。

## 特点：

1、不独占一行显示，相邻的内联元素在同一行，**宽度随内容的变化而变化**。

2、没有宽高属性，不可以设置width、height；没有上下属性，不可以设置margin、padding的top和bottom值。

3、有左右属性，可设置margin、padding的左右属性值。

## 举例：

HTML元素默认：`<a>、<span>、<label>、<textarea>`

css可通过设置元素样式为display: inline;修改元素为块级元素；

# block元素

block元素又被称为：块级元素。

## 特点：

1、独占一行，后面不管是啥元素，都会另起一行显示，宽度默认为父级元素的100%填充。

2、有宽高、上下、左右属性，随便设置。

## 举例：

HTML元素默认：`<div>、<p>、<pre>、<hr>、<h1>...<h6>、<ol>、<ul>、<li>、<dl>、<table>、<form>`

css可通过设置元素样式为display: block;修改元素为块级元素；

# inline-block元素

inline-block元素又被称为：内联-块级元素。

## 特点

集合了inline和block元素的一部分特点。

1、声明为inline-block的元素，会被当做inline元素去显示，也就是它整体不必独占一行了，但是它的内容遵循block元素的特征，宽高、上下、左右属性都可设置。

## 举例：

HTML元素默认：<img>、<input>

css可通过设置元素样式为display: inline-block;修改元素为内联-块级元素；