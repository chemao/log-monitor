## 结构图
![image](https://cloud.githubusercontent.com/assets/1988293/9754510/70ecbfb2-56fc-11e5-9bbd-cef1f0739e15.png)

## 关键字监控接入
### 1. 记录日志
* log4j日志格式前缀
[%-5p] %d{yyyy-MM-dd HH:mm:ss,SSS} 
* logback日志格式前缀
%d{yyyy-MM-dd HH:mm:ss.SSS} %-5level 
[地址](https://sls.console.aliyun.com)

### 2. 添加sls
![image](https://cloud.githubusercontent.com/assets/1988293/9733802/d75be2ec-5660-11e5-968a-088dccc5e455.png)
![image](https://cloud.githubusercontent.com/assets/1988293/9733818/ec7d18b2-5660-11e5-8a11-12cdf4680dde.png)
![image](https://cloud.githubusercontent.com/assets/1988293/9733846/15e697fa-5661-11e5-9cc6-036fae80fbde.png)
> ![image](https://cloud.githubusercontent.com/assets/1988293/9755151/37b0708a-5702-11e5-8bd0-f7da72e676fd.png)
> 日志字段捕获表达式
> * log4j.xml
``` \[(\w+)]\s([^,]+).*(,)(.*) ```
> * logback.xml
``` ([^.]+).*\s(\w+)(\s|)(.*) ```

![image](https://cloud.githubusercontent.com/assets/1988293/9760617/2db3a92c-5729-11e5-9c9f-6541559e93a4.png)


### 3. 监控配置[地址](http://cms.console.aliyun.com/index#/custom)
![image](https://cloud.githubusercontent.com/assets/1988293/9601389/bbb15eec-50d3-11e5-89f4-4a0280d2c767.png)
![image](https://cloud.githubusercontent.com/assets/1988293/9753980/ab7ecc7a-56f6-11e5-98e2-143deaa08683.png)


![image](https://cloud.githubusercontent.com/assets/1988293/9601899/30f575a0-50d7-11e5-9090-fef39029589a.png)

### 4. 配置监控服务
添加日志监控信息，https://github.com/chemao/log-monitor/blob/master/keyword-monitor-config
```
  {
        "userId": 固定值，与现有的配置一致,
        "namespace": 固定值，与现有的配置一致,
        "logName": 日志名称，与sls和云监控中配置的名称一致，
        "keyword": 关键字名称，比如：ERROR,
        "run": 是否开启，填写true
    }
```

### 打包
1. 从github的配置中，把相应环境的配置文件内容拷贝到 filter.properties中。
2. mvn package -Dmaven.test.skip=true

### 注意事项
1. filter.properties的文件内容不要提交到github, log-monitor工程时对外公开的

### 状态监测
* logmonitor自检地址：/log-monitor/preload
* logmonitor配置更新：/log-monitor/configModifyNotify

