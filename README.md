StorageManager
A java library, to save and manage files in local storage, it can be used on all sort of java applications, since it was built based on Java.io package. 

Contact: abdelouadoudrasmi@gmail.com  
Linkedin: https://www.linkedin.com/in/abdel-wadoud/

The documentation can be found here:  
https://github.com/rasmi-aw/StorageManager/wiki/StorageManager




# StorageManager

A java library, to save and manage files in local storage, it can be used on all sort of java applications, since it was built based on Java.io package. 
This library is composed of several Java files.

## 1- Integration   
This library can be used in many different ways, all versions are published on Maven Central, as all other libraries to use it you need to include its dependency in your code, the next steps give you a hint about that.

### 1.1 Maven   

```xml
 <dependency>
   <groupId>com.beastwall</groupId>
   <artifactId>storage-manager</artifactId>
   <version>1.0.2</version>
 </dependency> 
```

### 1.2 Gradle   
```gradle 
implementation 'com.beastwall:storage-manager:1.0.2'
```

1.3 Other methods   
For other usages (gradle kotlin, scala etc...), see the link below  
https://search.maven.org/artifact/com.beastwall/storage-manager/1.0.2/jar

## 2- How to use it ?   
The main useful way this library can be used in, is to save files with other utils support, this will come as a question and response.

### 2.1 Save file   
```java 
FileSaver class is used to save, inputstream or files, in a Synchronous or Asynchronous way, generally an object is instantiated using get() method, meanwhile the file saving operation is done using, save(...) or saveAsync(...) methods.
``` 

```java 
//Construct a saver instance
FileSaver saver = FileSaver.get();
```

Save a file
```java
 File pdfFile = new File(pdfUrl);
 String pdf = saver.save(pdfFile, "pdffile.pdf");
```

Save an inputStream
```java
 InputStream inputStream = new FileInputStream(imgUrl);
 String png = saver.save(inputStream, "image.png");
```

Save a file using its url
```java
String zip = saver.save(zipUrl, "file.zip");
```

Copy a file into a specefic directory
```java
String text = saver.save(new File(txtUrl), "StorageManager/demo/Demo/newdir", "file.txt");
```

Other saving methods can be found in FileSaver class

### 2.2 ProgressCallback
Some file saving tasks can take too much time, thus for the user needs some update on the current state of the operation, for example after performing an http call and get a file as a response, we need to show download progress to the user, and this can be done using ProgressCallback interface

```java
 String path = saver.setProgressCallBack(new ProgressCallback() {
            @Override
            public void progress(long totalBytes, long numberOfReadBytes, int percentage) {
                System.out.println(percentage);
            }
        }).save(new File(txtUrl), "StorageManager/demo/Demo/newdir", "file1.txt");
```
   

or in lambda expression
```java 
saver
      .setProgressCallBack((totalBytes, numberOfReadBytes, percentage) -> {})
      .save(new File(txtUrl), "StorageManager/demo/Demo/newdir", "file.txt");
```

The total number of bytes (File size) is extracted from "inputsteam.available()" method, then to change te expected number of bytes to be read, to get a more accurate progress calculation you can use setInputLength() method to specify the totalBytes number
```java
saver.setInputLength(textFile.length())
                .save(textFile, "StorageManager/demo/Demo/newdir", "file2.txt");  
```
### 2.3 Asynchronous save
Save a file asynchronously in a separate thread (meant for performance)
```java 
saver.saveAsync(zipUrl, "fileAsync.zip", new FileSavedCall() {
            @Override
            public void result(boolean success, String file) {
                String zip = file;
            }
        });
```
### Random file name
Sometimes a file needs a random name with extension to be saved, StorageUtils class provides some needed utils that could help you to manage your files 
```java
String fileName = StorageUtils.randomNameWithExtension("any-prefix", MimeType.APPLICATION_JSON);
```
A lot of other features an be found in StorageUtils.

### Short expression
All the set methods return the FileSaver instance, so for a better code appearance you might want to use this short expression.
 ```java
 FileSaver
           .get()
           .setProgressCallBack(...)
           .setInputLength(...)
           .save(...);
```
## Demo
To see real examples you can download demo project from the link below  
https://github.com/rasmi-aw/StorageManager/tree/master/demo/Demo
 

