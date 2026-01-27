#### 类图：



#### 时序图：



#### 状态图：



#### 数据库表结构（字段、索引、关系）：



#### 接口定义（API 参数、返回值）：

##### 公共层：

StaffController类：

post:
 /staff                    # 根路径
    /registBySelf              # 医生、化验员注册自己已有账号的密码，管理员账号不能注册，只能由工程师提前插入
                   #
            #


##### 医生层：

##### 管理员层：

AdminController类：

post:   
 /admin                # 根路径
     /registerByAdmin/doctor               #管理员注册医生账号
     /registerByAdmin/labTech             #管理员注册化验员账号
          #

##### 化验员层：

##### 用户层：