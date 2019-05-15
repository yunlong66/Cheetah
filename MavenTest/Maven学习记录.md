学习了Maven相关知识后，我在本地环境中安装了Maven，并在IDEA中进行了配置，创建了MavenTest项目和它的子项目childMavenTest，在命令行下测试了Maven的常用命令

以下是整理的知识点：

####Maven的优点：统一维护jar包

正常情况下如果有3个Java项目，就会各自维护一套jar包，但是其中有些jar包是相同的。
而maven风格的项目，首先把所有的jar包都放在"仓库“ 里，然后哪个项目需要用到这个jar包，只需要给出jar包的名称和版本号就行了，这样jar包就实现了共享。

####Maven的项目结构：

1. 有一个pom.xml 用于维护当前项目都用了哪些jar包
2. 所有的java代码都放在 src/main/java 下面
3. 所有的测试代码都放在src/test/java 下面

####Maven常用命令：

1. 创建Maven的普通java项目：
   mvn archetype:generate -DgroupId=com.testmaven -DartifactId=Cheetah -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
   archetype:generate 表示创建项目
   -DgroupId 项目包名: com.testmaven
   -DartifactId 项目名称: Cheetah
   -DarchetypeArtifactId 项目类型: maven-archetype-quickstart
   -DinteractiveMode:false 是否使用交互模式。表示前面参数都给了，就不用一个一个地输入了

2. 创建Maven的Web项目：
   mvn archetype:create
   -DgroupId=packageName
   -DartifactId=webappName
   -DarchetypeArtifactId=maven-archetype-webapp
3. 编译源代码： mvn compile
4. 编译测试代码：mvn test-compile
5. 运行测试：mvn test
6. 创建项目文档site：mvn site
7. 打包(不)：mvn package
8. 在本地Repository中安装jar：mvn install
9. 清除产生的项目：mvn clean牋

####pom.xml文件组成及用途：
POM是 Maven 工程的基本工作单元，是一个XML文件，包含了项目的基本信息，用于描述项目如何构建，声明项目依赖等。
POM 中可以指定以下配置：

- 项目依赖
- 插件
- 执行目标
- 项目构建 profile
- 项目版本
- 项目开发者列表
- 相关邮件列表信息

所有 POM 文件都需要 project 元素和三个必需字段：groupId，artifactId，version。

```xml
<project>	工程的根标签。
<modelVersion>	模型版本
<groupId>	这是工程组的标识。它在一个组织或者项目中通常是唯一的。
<artifactId>	这是工程的标识。它通常是工程的名称。
<version>	这是工程的版本号。在artifact的仓库中，它用来区分不同的版本
```


其他部分标签的解释：

```xml
<parent> --被继承的父项目的构件标识符 
<dependencies> --该元素描述了项目相关的所有依赖。 这些依赖组成了项目构建过程中的一个个环节。它们自动从项目定义的仓库中下载。 
<developers> --某个项目开发者的信息
<build> --该元素设置了项目源码目录，当构建项目的时候，构建系统会编译目录里的源码。该路径是相对于pom.xml的相对路径。
<sourceDirectory /> --该元素设置了项目脚本源码目录，该目录和源码目录不同：绝大多数情况下，该目录下的内容 会被拷贝到输出目录(因为脚本是被解释的，而不是被编译的)。
<resources> --这个元素描述了项目相关或测试相关的所有资源路径
<pluginManagement> --使用的插件列表。
<modules> --模块（有时称作子项目）被构建成项目的一部分。列出的每个模块元素是指向该模块的目录的相对路径
<repositories> --发现依赖和扩展的远程仓库列表。
<distributionManagement> --部署项目产生的构件到远程仓库需要的信息
```



####仓库概念：

仓库就是用于存放项目需要的jar包的。
maven采用一个仓库，多个项目的方式，让多个项目共享一个仓库里的相同jar包。
Maven 仓库有三种类型：

- 本地（local）Maven 所需要的任何构件都是直接从本地仓库获取的。如果本地仓库没有，它会首先尝试从远程仓库下载构件至本地仓库，然后再使用本地仓库的构件。
- 中央（central）Maven 中央仓库是由 Maven 社区提供的仓库，其中包含了大量常用的库。
- 远程（remote）开发人员自己定制的仓库，包含了所需要的代码库或者其他工程中用到的 jar 文件。

在MAC环境下，通过**/usr/local/maven/conf/settings.xml**文件可以看到仓库的默认位置是
**Default: ${user.home}/.m2/repository** 
我把修改仓库地址修改成了**/Users/yanyunlong/maven/repository**
maven会默认从maven官方提供的服务器下载jar包,可以在上面说的settings.xml文件里修改下载路径，换成国内的下载速度会更快。

####父子聚合项目:
通过 maven 可以创建父子-聚合项目。 所谓的父子项目，即有一个父项目，有多个子项目。
这些子项目，在业务逻辑上，都归纳在这个父项目下，并且一般来说，都会有重复的jar包共享。
所以常用的做法会把重复的 jar 包都放在父项目下进行依赖，那么子项目就无需再去依赖这些重复的 jar 包了。
子项目的 pom.xml ，多了一个parent, 这个就是对父项目的依赖。

```xml
<parent>
         <artifactId>cheetah</artifactId>
        <groupId>com.maventest</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
```

父项目的 pom.xml ,多了这么一个模块, 就表示对子项目的关联。

   ```xml
<modules>
    <module>childMavenTest</module>
  </modules>
   ```



####Maven 依赖搜索顺序:

当我们执行 Maven 构建命令时，Maven 开始按照以下顺序查找依赖的库：

- 步骤 1 － 在本地仓库中搜索，如果找不到，执行步骤 2，如果找到了则执行其他操作。
- 步骤 2 － 在中央仓库中搜索，如果找不到，并且有一个或多个远程仓库已经设置，则执行步骤 4，如果找到了则下载到本地仓库中以备将来引用。
- 步骤 3 － 如果远程仓库没有被设置，Maven 将简单的停滞处理并抛出错误（无法找到依赖的文件）。
- 步骤 4 － 在一个或多个远程仓库中搜索依赖的文件，如果找到则下载到本地仓库以备将来引用，否则 Maven 将停止处理并抛出错误（无法找到依赖的文件）。