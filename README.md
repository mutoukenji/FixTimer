# FixTimer

Android用程序定时器

## 使用方法
### 创建并启动
```
FixTimer timer = FixTimer.newInstance()
      .setDelay(5000)
      .setRunnable(new Runnable() {
          @Override
          public void run() {
              // Do something here
          }
      })
      .start();
```
### 取消
```
timer.cancel();
```

取消后Runnable内容不会再被调用
