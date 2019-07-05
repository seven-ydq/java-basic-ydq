## 基本数据类型和引用数据类型（String）的相互转换：

1、每个基本数据类型都有一个相对应的引用数据类型；

eg:           int number = 10;
		String age = "10";
		System.out.println("****string转int****");
		int age_int = Integer.parseInt(age);
		System.out.println(age_int);
		System.out.println("****int转string****");
		Integer integer = new Integer(number);
		System.out.println(integer.toString());



