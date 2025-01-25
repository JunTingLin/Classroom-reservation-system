# Classroom-reservation-system

這個專案是為中央大學應用地質研究所開發的教室預約系統。考慮到以前使用 Google 表單和日曆進行預約的方式有點混亂難以管理，且缺乏身份驗證，任何人都可以進行預約。因此，本次專案使用 Spring Boot 和 Nas 上的 MariaDB 資料庫開發了一個簡單的預約系統。系統採用學校計中帳號的 Portal 單一登入認證機制，僅允許在學學生進行單一預約，而批量預約（例如課表）僅限指定的管理員帳號使用。

## The website now is runing on Nas server
http://140.115.123.11:8080/

# 部署小筆記
~~maven打包fat jar指令，且跳過test~~

`mvn package -Dmaven.test.skip`

idea GUI

![image](https://user-images.githubusercontent.com/92431095/224988836-4f3fda5f-a8ee-4fbb-87e9-42c945606828.png)

配置文件已分開，現已經不需要，直接按下打包(package)即可

運行jar檔指令，並且指定配置文件

`java -jar <jar-file-name>.jar --spring.profiles.active=prod`

背景運行，使用nohup(no hang up不挂起)

`nohup java -jar /var/services/homes/geo251/oauth-client-1.0.0.jar --spring.profiles.active=prod &`

`nohup java -jar /var/services/homes/geoad/oauth-client-1.0.0.jar --spring.profiles.active=prod &`

參考資料:[Linux nohup 命令](https://www.runoob.com/linux/linux-comm-nohup.html)
![image](https://user-images.githubusercontent.com/92431095/224990307-129c1842-b89d-46b5-b21f-4d0bbf2fb6c1.png)

查找process id

`ps aux | grep oauth-client-1.0.0.jar`

`kill <PID>`



# 渲染圖
1. HOME
<img alt="image" src="https://github.com/JunTingLin/Classroom-reservation-system/assets/92431095/af467098-f0e9-48e8-9d50-c730b2b3e197">


2. 日曆
![image](https://user-images.githubusercontent.com/92431095/224990696-4aa07e63-964e-4af9-9df7-5b582a41218e.png)


3. 新增、刪除個人預約、表單提交(需透過中大單一登入)
![image](https://user-images.githubusercontent.com/92431095/215586461-940f0f92-212c-4a04-a438-e3113284ffbb.png)

4. 新增、刪除批量預約(需透過中大單一登入)
<img width="" alt="image" src="https://github.com/JunTingLin/Classroom-reservation-system/assets/92431095/6f5d800a-6b5f-4f33-806e-63ffe5a0be40">

5. 中大protal單一登入
![image](https://github.com/JunTingLin/Classroom-reservation-system/assets/92431095/935834eb-fe48-482f-ae45-d01d8c5ce92b)





