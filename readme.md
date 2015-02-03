## 版本信息 ##

V1.0.2

1．【BUG】修复 WritableExcel 写出 EXCEL 文档对象属性为null时抛出的异常

V1.0.1

1．校验接口添加获得校验失败原因方法，修复 ReadableExcel 校验文档内容不通过时抛出的用户自定义的异常信息。

## 依赖配置 ##

```xml
<repositories>
  <repository>
    <id>lychie-maven-repo</id>
    <url>https://raw.github.com/lychie/maven-repo/master/releases</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>org.lychie</groupId>
    <artifactId>jexcel</artifactId>
    <version>1.0.2</version>
  </dependency>
</dependencies>
```

## 读写EXCEL文档 ##

博客文章：[http://www.blogjava.net/fancydeepin/archive/2015/01/21/jexcel.html](http://www.blogjava.net/fancydeepin/archive/2015/01/21/jexcel.html "点击前往")