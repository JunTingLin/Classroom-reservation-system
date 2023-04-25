# Classroom-reservation-system

## The website now is runing on Nas server
http://140.115.123.11:8080/

# 部屬小筆記
maven打包fat jar指令，且跳過test

`mvn package -Dmaven.test.skip`

idea GUI

![image](https://user-images.githubusercontent.com/92431095/224988836-4f3fda5f-a8ee-4fbb-87e9-42c945606828.png)

運行jar檔指令

`java -jar <jar-file-name>.jar`

背景運行，使用nohup(no hang up不挂起)

`nohup java -jar /var/services/homes/geo251/oauth-client-1.0.0.jar &`

參考資料:[Linux nohup 命令](https://www.runoob.com/linux/linux-comm-nohup.html)
![image](https://user-images.githubusercontent.com/92431095/224990307-129c1842-b89d-46b5-b21f-4d0bbf2fb6c1.png)

查找process id

`ps aux | grep oauth-client-1.0.0.jar`

`kill <PID>`



# 渲染圖
1. HOME
![image](https://user-images.githubusercontent.com/92431095/215586277-a9093474-3a9e-484b-9c93-d16ab90b5ae6.png)


2. 日曆
![image](https://user-images.githubusercontent.com/92431095/224990696-4aa07e63-964e-4af9-9df7-5b582a41218e.png)


3. 查看刪除個人預約、表單提交(需透過中大單一登入)
![image](https://user-images.githubusercontent.com/92431095/215586461-940f0f92-212c-4a04-a438-e3113284ffbb.png)


