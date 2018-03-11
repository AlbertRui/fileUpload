# Javaweb文件的上传和下载
## 最终效果展示
![image](/images/001.PNG)
* 其中：点击新增按钮可新增一个上传框
## 基于表单的文件上传
* 如果在表单中使用表单元素 <input type=“file” />，浏览器在解析表单时，会自动生成一个输入框和一个按钮，输入框可供用户填写本地文件的文件名和路径名，按钮可以让浏览器打开一个文件选择框供用户选择文件：

![image](/images/002.PNG)
### enctype属性
* 当表单需要上传文件时，需指定表单 enctype 的值为 multipart/form-data
* 在 form 元素的语法中，enctype 属性指定将数据发送到服务器时浏览器使用的编码类型。  
* enctype 属性取值: 
    * application/x-www-form-urlencoded：表单 enctype 属性的默认值。这种编码方案使用有限的字符集，当使用了非字母和数字时，必须用”%HH”代替(H 代表十六进制数字)。对于大容量的二进制数据或包含非 ASCII 字符的文本来说，这种编码不能满足要求。
    * multipart/form-data：form 设定了enctype=“multipart/form-data”属性后，表示表单以二进制传输数据 
### Commons-fileupload 组件
#### 组件简介
* Commons-fileupload 组件是 Apache 开源代码组织用来处理表单文件上传的一个子项目，该组件性能优异，可以支持任意大小的文件的上传
* Commons-fileupload 组件从 1.1 版本开始依赖 Apache 的另一个项目：commons-io
#### 组件原理
* FileUpload组件将页面提交的所有元素(普通form表单域，如text和文件域file)都看作一样的FileItem，这样上传页面提交的 request请求也就是一个FileItem的有序组合，FileUpload组件可以解析该request，并返回一个一个的FileItem。而对每一个FileItem，FileUpload组件可以判断出它是普通form表单域还是文件file域，从而根据不同的类型，采取不同的操作－－如果是表单域，就读出其值，如果是文件域，就保存文件到服务器硬盘上或者内存中。 
#### 组件API
* 在 Commons-fileupload 组件中，主要用到以下三个接口和类：
```java
org.apache.commons.fileupload.FileItem;
org.apache.commons.fileupload.disk.DiskFileItemFactory;
org.apache.commons.fileupload.servlet.ServletFileUpload;
```
* ServletFileUpload 负责处理上传的文件数据，并将每部分的数据封装成一到 FileItem 对象中。
* DiskFileItemFactory 是创建 FileItem 对象的工厂，在这个工厂类中可以配置内存缓冲区大小和存放临时文件的目录。
* ServletFileUpload 在接收上传文件数据时，会将内容保存到内存缓存区中，如果文件内容超过了 DiskFileItemFactory 指定的缓冲区的大小，那么文件将被保存到磁盘上，存储为 DiskFileItemFactory 指定目录中的临时文件。等文件数据都接收完毕后，ServletUpload 在从文件中将数据写入到上传文件目录下的文件中
## 文件的下载
* 情景：在一些网络系统中，需要隐藏下载文件的真实地址，或者下载的文件需要一个程序来动态的确定后在传送给客户端
* 方案：利用程序编码实现下载
* 可以增加安全访问控制，只对经过授权认证的用户提供下载
* 可以从任意位置提供下载的数据

>利用程序实现下载需要设置 2 个报头：

* Web 服务器需要告诉浏览器其所输出的内容的类型不是普通的文本文件或 HTML 文件，而是一个要保存到本地的下载文件。设置Content-Type 的值为：application/x-msdownload
* Web 服务器希望浏览器不直接处理相应的实体内容，而是由用户选择将相应的实体内容保存到一个文件中，这需要设置 Content-Disposition 报头。该报头指定了接收程序处理数据内容的方式，在 HTTP 应用中只有 attachment 是标准方式，attachment 表示要求用户干预。在 attachment 后面还可以指定 filename 参数，该参数是服务器建议浏览器将实体内容保存到文件中的文件名称。在设置 Content-Dispostion 之前一定要指定 Content-Type.	
```java
response.setContentType("application/x-msdownload");
String fileName = "文件下载.pptx";
response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
```
* 因为要下载的文件可以是各种类型的文件，所以要将文件传送给客户端，其相应内容应该被当做二进制来处理，所以应该调用`response.getOutPutStream();`方法返回 ServeltOutputStream 对象来向客户端写入文件内容。                        
```java
OutputStream out = response.getOutputStream();
String pptFileName = "E:\\ITstudy\\atguigu\\Javaweb\\javaweb 课件\\12. 尚硅谷_JavaWEB_文件的上传和下载.pptx";

InputStream in = new FileInputStream(pptFileName);

byte[] buffer = new byte[1024];
int len;

while ((len = in.read(buffer)) != -1) {
    out.write(buffer, 0, len);
}
```








