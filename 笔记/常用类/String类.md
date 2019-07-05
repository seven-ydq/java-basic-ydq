# String类

java.lang.string 

string是被final修饰的，replace（）方法返回的是一个新的字符串。

## length()方法

## equals()方法   

### equals()与== 的区别：

**equals()：检查组成字符串内容的字符是否完全一致；**

**==：判断两字符串在内存中的地址，即判断是否是同一个字符串对象**



**总结: String 常用于基本字符串的处理，如果一个字符串需要经常增删一部分内容，则优先使用StrinBuffer、 StringBuilder, 多线程环境使用StringBuffer, 单线程环境使用StringBuilder .**

### equals与hashcode：

**当类不为散列结构时：**

equals为true， hashcode不一定相同；

hashcode相同，equals不一定为true;



**当类遵循散列结构时：**

equals为true，hashcode一定相同;

hashcode相同，认为是同一个对象，equals最好也返回true;