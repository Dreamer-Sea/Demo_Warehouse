# 项目介绍
[toc]
## 总述
‘点点’是一个运行在Android系统上的新闻资讯类应用程序。目标SDK为25，最小SDK为18，
它的主要功能有：
- 新闻的获取与浏览；
- Bing图片的浏览与下载；
- 用户的登录与注册。

## 各个功能的介绍(包括具体实现和遇到的问题)
### 新闻的获取与浏览
这一部分的功能的实现主要分为以下几个点：
- JSON的获取与解析；
- 用来显示新闻的RecyclerView的设计与实现；
- 加载并显示新闻。

#### JSON的获取与解析
1. 这部分使用了**万维易源**的[新闻API接口](https://www.showapi.com/apiGateway/view?apiCode=109)来获取新闻的JSON。
2. JSON的解析使用了Google官方提供的Gson库，这个库能够将JSON转为Java对象，同样能够将Java对象转为JSON。
从JSON结构来看，想要获得有用的返回内容需要先获得‘showapi_res_body’，再获得‘contentlist’，才能够获得能够后续放在RecyclerView子项中的内容。所以构建‘DailyNews’，‘RBody’和‘ResBody’这三个类，来提取JSON中的内容。
3. 经过以上步骤就能够获得一个存放有多个新闻对象的List。

存在的问题
1. 未对新闻JSON获取失败或网络异常进行处理。如果在JSON转化为Java对象的过程中有问题出现，该App就会直接崩溃。

解决方法：
1. 加入异常处理。当获取新闻对象失败时，负责显示新闻的fragment就不加载RecyclerView，转而显示异常提示，如“获取新闻失败，请检查网络连接”等。

#### 用来显示新闻的RecyclerView的设计与实现
这部分需要先设计RecyclerView中子项的布局，这里的子项只显示新闻标题和日。RecyclerView还能设置显示布局，这里使用最简单的线性布局(LinearLayoutManager)。同时给每个子项设置了点击事件，点击子项就能够用WebView加载相应的新闻链接。
这里还加入了‘下拉刷新’的功能，每次从顶部下拉就触发‘获取新闻JSON’的请求，并将新的新闻加入RecyclerView中。

存在的问题：
1. 未对新闻内容进行去重。如果用户频繁刷新而服务器端并未返回最新的新闻，那么RecyclerView中就会显示重复的新闻内容。

解决方法：
1. 重写新闻对象的euqals和hashcode方法，用Set来存放新闻的Java对象，这样就能够避免新闻的重复。

#### 加载并显示新闻
在之前的一个步骤中给RecyclerView设置了点击事件，用户在点击子项之后就会调用腾讯的TBS浏览服务SDK来加载新闻的链接。使用了Intent来传递要加载的新闻的URL。

### Bing图片的浏览与下载
这一部分的功能的实现主要分为以下几个点：
- JSON的获取与解析；
- 图片显示；
- 图片下载。

#### JSON的获取与解析
1. 从**万维易源**的[必应每日壁纸](https://www.showapi.com/apiGateway/view?apiCode=1287)接口获取JSON。
2. 使用Google的Gson库解析JSON并转为相应的Java对象。
3. 实现了下拉刷新的功能。

#### 图片显示
1. 使用了Glide库通过读取URL的方式获取图片。
2. 将加载到的图片放入ViewHolder中。

#### 图片下载
1. 构造DownloadUtils类。实现了图片下载时，通知栏同时显示下载进度。
2. 给RecyclerView中的每个子项设置了点击事件。用户在点击图片的时候，会向DownloadUtils类传入图片的URL，DownloadUtils就能根据这个URL来下载图片，并将图片存放到根目录的‘BingPicture’目录下。

存在的问题：
1. 在SDK 25下，App能够获得‘android.permission.MOUNT_UNMOUNT_FILESYSTEMS’，但是在SDK 26下，不再允许App获得该权限了。

