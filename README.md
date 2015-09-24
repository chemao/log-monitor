### 系统结构
![image](https://cloud.githubusercontent.com/assets/1988293/9754510/70ecbfb2-56fc-11e5-9bbd-cef1f0739e15.png)

### 打包
1. 从github的配置中，把相应环境的配置文件内容拷贝到 filter.properties中。
2. mvn package -Dmaven.test.skip=true

### 注意事项
1. filter.properties的文件内容不要提交到github, log-monitor工程时对外公开的

### 状态监测
* logmonitor自检地址：/log-monitor/preload
* logmonitor配置更新：/log-monitor/configModifyNotify

