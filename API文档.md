# 医院管理系统 API 接口文档

## 通用说明

### 基础路径
```
http://localhost:8080
```

### 响应格式
所有接口统一返回 JSON 格式：
```json
{
  "code": 0,        // 0-成功, 1-失败
  "message": "提示信息",
  "data": { }       // 响应数据
}
```

### 认证方式
- **用户端**: 通过 `/user/login` 获取 Token，放入请求头 `Token`
- **员工端**: 通过 `/staff/staffLogin` 获取 Token，放入请求头 `Token`
- **免登录接口**: `/user/register`, `/user/login`, `/staff/registerBySelf`, `/staff/staffLogin`

---

## 一、用户模块 (User)

### 1.1 用户注册
**POST** `/user/register`

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| phone | string | 是 | 手机号，11位数字 |
| psw | string | 是 | 密码，5-16位字母或数字 |
| userName | string | 是 | 用户姓名 |

**响应示例**
```json
{
  "code": 0,
  "message": "注册成功",
  "data": null
}
```

---

### 1.2 用户登录
**POST** `/user/login`

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| phone | string | 是 | 手机号，11位数字 |
| psw | string | 是 | 密码，5-16位字母或数字 |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": "eyJhbGciOiJIUzI1NiJ9..."
}
```

---

### 1.3 获取用户信息
**GET** `/user/Info`

- 需要请求头: `Token`

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": {
    "userId": 1,
    "userName": "张三",
    "phone": "13800138000"
  }
}
```

---

### 1.4 添加就诊卡
**POST** `/user/addMedicalCard`

- 需要请求头: `Token`

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| cardName | string | 是 | 就诊卡姓名 |
| gender | string | 是 | 性别 |
| idCard | string | 是 | 身份证号 |
| birthday | date | 是 | 出生日期 |
| address | string | 否 | 地址 |

**响应示例**
```json
{
  "code": 0,
  "message": "添加成功",
  "data": null
}
```

---

### 1.5 查看就诊卡
**GET** `/user/showMedicalCard`

- 需要请求头: `Token`

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": {
    "cardId": 1,
    "cardName": "张三",
    "gender": "男",
    "idCard": "110101199001011234",
    "birthday": "1990-01-01",
    "address": "北京市朝阳区"
  }
}
```

---

### 1.6 预约医生
**POST** `/user/appointment`

- 需要请求头: `Token`

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| doctorNo | string | 是 | 医生工号 |
| reserveDate | date | 是 | 预约日期，格式 yyyy-MM-dd |
| reserveTime | string | 是 | 预约时间段 (如: 上午/下午) |

**响应示例**
```json
{
  "code": 0,
  "message": "预约成功",
  "data": null
}
```

---

### 1.7 预约体检化验项目
**PATCH** `/user/checkItem`

- 需要请求头: `Token`

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| itemId | int | 是 | 体检项目ID |

**响应示例**
```json
{
  "code": 0,
  "message": "用户预约检查化验项目成功",
  "data": null
}
```

---

## 二、员工模块 (Staff)

### 2.1 员工注册
**POST** `/staff/registerBySelf`

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| staffId | string | 是 | 工号 |
| name | string | 是 | 姓名 |
| password | string | 是 | 密码 |
| phone | string | 是 | 手机号 |
| role | string | 是 | 角色 (医生/化验员/管理员) |

**响应示例**
```json
{
  "code": 0,
  "message": "注册成功",
  "data": null
}
```

---

### 2.2 员工登录
**POST** `/staff/staffLogin`

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| staffId | string | 是 | 工号 |
| password | string | 是 | 密码 |
| role | string | 是 | 角色 (医生/化验员/管理员) |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "name": "张三"
  }
}
```

---

### 2.3 员工查看个人信息
**GET** `/staff/staffInfo`

- 需要请求头: `Token`

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": {
    "staffId": "D2022001",
    "name": "李医生",
    "role": "医生",
    "title": "主任医师",
    "deptId": 1,
    "phone": "13800138000",
    "email": "doctor@hospital.com"
  }
}
```

---

### 2.4 员工修改密码
**POST** `/staff/updatePsw`

- 需要请求头: `Token`

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| oldPsw | string | 是 | 旧密码 |
| newPsw | string | 是 | 新密码 |

**响应示例**
```json
{
  "code": 0,
  "message": "修改成功",
  "data": null
}
```

---

## 三、管理员模块 (Admin)

### 3.1 管理员注册医生账号
**POST** `/admin/registerByAdmin/doctor`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| doctorNo | string | 是 | 医生工号 |
| doctorName | string | 是 | 医生姓名 |
| title | string | 是 | 职称 |
| phone | string | 否 | 手机号 |
| email | string | 否 | 邮箱 |
| deptId | int | 是 | 科室ID |
| specialty | string | 否 | 擅长领域 |

**响应示例**
```json
{
  "code": 0,
  "message": "注册成功",
  "data": null
}
```

---

### 3.2 管理员注册化验员账号
**POST** `/admin/registerByAdmin/labTech`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| labNo | string | 是 | 化验员工号 |
| labName | string | 是 | 化验员姓名 |
| phone | string | 否 | 手机号 |
| email | string | 否 | 邮箱 |

**响应示例**
```json
{
  "code": 0,
  "message": "注册成功",
  "data": null
}
```

---

### 3.3 查看所有医生列表
**GET** `/admin/selectList/doctor`

- 需要请求头: `Token` (管理员)

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    {
      "doctorNo": "D2022001",
      "doctorName": "李医生",
      "title": "主任医师",
      "deptName": "内科",
      "specialty": "心血管",
      "phone": "13800138000",
      "email": "doctor@hospital.com"
    }
  ]
}
```

---

### 3.4 查看所有化验员列表
**GET** `/admin/selectList/labTech`

- 需要请求头: `Token` (管理员)

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    {
      "labNo": "L2022001",
      "labName": "王化验员",
      "phone": "13800138001",
      "email": "lab@hospital.com"
    }
  ]
}
```

---

### 3.5 删除医生
**DELETE** `/admin/delete/doctor/{doctorNo}`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| doctorNo | path | 是 | 医生工号 |

**响应示例**
```json
{
  "code": 0,
  "message": "删除医生成功",
  "data": null
}
```

---

### 3.6 删除化验员
**DELETE** `/admin/delete/labTech/{labNo}`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| labNo | path | 是 | 化验员工号 |

**响应示例**
```json
{
  "code": 0,
  "message": "删除化验员成功",
  "data": null
}
```

---

### 3.7 修改医生职称
**PUT** `/admin/update/doctorTitle`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| doctorNo | string | 是 | 医生工号 |
| title | string | 是 | 新职称 |

**响应示例**
```json
{
  "code": 0,
  "message": "修改医生职称成功",
  "data": null
}
```

---

### 3.8 查看所有科室分类
**GET** `/admin/findAllDeptSort`

- 需要请求头: `Token` (管理员或用户)

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    { "deptSortId": 1, "deptSortName": "内科" },
    { "deptSortId": 2, "deptSortName": "外科" }
  ]
}
```

---

### 3.9 查看某分类下的所有科室
**GET** `/admin/findAllDept`

- 需要请求头: `Token` (管理员或用户)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| deptSortName | string | 是 | 科室分类名 |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    { "deptId": 1, "deptName": "心内科", "deptSortId": 1 },
    { "deptId": 2, "deptName": "消化内科", "deptSortId": 1 }
  ]
}
```

---

### 3.10 查看所有科室（不分分类）
**GET** `/admin/findAllDeptNoSort`

- 需要请求头: `Token` (管理员或用户)

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    { "deptId": 1, "deptName": "心内科", "deptSortId": 1 },
    { "deptId": 2, "deptName": "骨科", "deptSortId": 2 }
  ]
}
```

---

### 3.11 新增科室
**POST** `/admin/addDept`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| deptName | string | 是 | 科室名称 |
| deptSortId | int | 是 | 科室分类ID |

**响应示例**
```json
{
  "code": 0,
  "message": "返回该分类下全部科室成功",
  "data": null
}
```

---

### 3.12 删除科室
**DELETE** `/admin/deleteDept`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| deptId | int | 是 | 科室ID |

**响应示例**
```json
{
  "code": 0,
  "message": "删除成功",
  "data": null
}
```

---

### 3.13 查询值班列表（分页）
**GET** `/admin/workList/search`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| page | int | 是 | 页码 |
| pageSize | int | 是 | 每页条数 |
| deptName | string | 否 | 科室名称 |
| doctorName | string | 否 | 医生姓名 |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": {
    "total": 100,
    "rows": [
      {
        "id": 1,
        "doctorNo": "D2022001",
        "doctorName": "李医生",
        "deptName": "内科",
        "reserveDate": "2026-03-15",
        "reserveTime": "上午",
        "reserveMax": 20,
        "reserveEmpty": 15
      }
    ]
  }
}
```

---

### 3.14 新增值班信息
**POST** `/admin/workList/insert`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| doctorNo | string | 是 | 医生工号 |
| reserveDate | date | 是 | 值班日期 |
| reserveTime | string | 是 | 时间段 |
| reserveMax | int | 是 | 最大预约人数 |

**响应示例**
```json
{
  "code": 0,
  "message": "插入值班信息成功",
  "data": null
}
```

---

### 3.15 修改值班信息
**PATCH** `/admin/workList/update`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | int | 是 | 值班记录ID |
| doctorNo | string | 是 | 医生工号 |
| reserveDate | date | 是 | 值班日期 |
| reserveTime | string | 是 | 时间段 |
| reserveMax | int | 是 | 最大预约人数 |

**响应示例**
```json
{
  "code": 0,
  "message": "修改值班信息成功",
  "data": null
}
```

---

### 3.16 删除值班信息
**DELETE** `/admin/workList/delete`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| id | int | 是 | 值班记录ID |

**响应示例**
```json
{
  "code": 0,
  "message": "删除值班信息成功",
  "data": null
}
```

---

### 3.17 获取某分类下的医生列表
**GET** `/admin/docList/deptSort`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| deptSortName | string | 是 | 科室分类名 |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": ["李医生", "王医生", "赵医生"]
}
```

---

### 3.18 获取某科室下的医生列表
**GET** `/admin/docList/dept`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| deptName | string | 是 | 科室名称 |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": ["李医生", "王医生"]
}
```

---

### 3.19 根据医生姓名查询工号和科室
**GET** `/admin/findNoAndDept`

- 需要请求头: `Token` (管理员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| docName | string | 是 | 医生姓名 |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": {
    "doctorNo": "D2022001",
    "deptName": "内科"
  }
}
```

---

## 四、医生模块 (Doctor)

### 4.1 查看个人值班表
**GET** `/doctor/workTable`

- 需要请求头: `Token` (医生)

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    {
      "id": 1,
      "doctorNo": "D2022001",
      "doctorName": "李医生",
      "deptName": "内科",
      "reserveDate": "2026-03-15",
      "reserveTime": "上午",
      "reserveMax": 20
    }
  ]
}
```

---

### 4.2 查看预约患者列表
**GET** `/doctor/patientReserve`

- 需要请求头: `Token` (医生)

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    {
      "reservationId": 1,
      "cardId": 1,
      "cardName": "张三",
      "reserveDate": "2026-03-15",
      "reserveTime": "上午",
      "reserveState": "已就诊"
    }
  ]
}
```

---

### 4.3 修改个人信息
**PATCH** `/doctor/updateInfo`

- 需要请求头: `Token` (医生)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| specialty | string | 是 | 擅长领域 |
| email | string | 是 | 邮箱 |
| phone | string | 是 | 手机号 |

**响应示例**
```json
{
  "code": 0,
  "message": "修改成功",
  "data": null
}
```

---

### 4.4 上传头像
**POST** `/doctor/upload`

- 需要请求头: `Token` (医生)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| file | file | 是 | 图片文件 |

**响应示例**
```json
{
  "code": 0,
  "message": "上传头像成功",
  "data": "http://localhost:8080/images/xxx.jpg"
}
```

---

## 五、处方模块 (Doctor Prescription)

### 5.1 新增处方
**POST** `/doctor/prescription/add`

- 需要请求头: `Token` (医生)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| cardId | int | 是 | 就诊卡ID |
| reservationId | int | 是 | 预约ID |
| diagnosis | string | 是 | 诊断 |
| prescriptionDetails | array | 是 | 处方详情列表 |

**prescriptionDetails 数组项结构：**
```json
[
  {
    "medicineName": "阿莫西林",
    "quantity": 1,
    "usage": "口服"
  }
]
```

**响应示例**
```json
{
  "code": 0,
  "message": "新增处方成功",
  "data": null
}
```

---

### 5.2 修改处方
**PATCH** `/doctor/prescription/update`

- 需要请求头: `Token` (医生)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| prescriptionId | int | 是 | 处方ID |
| diagnosis | string | 是 | 诊断 |
| prescriptionDetails | array | 是 | 处方详情列表 |

**响应示例**
```json
{
  "code": 0,
  "message": "修改处方成功",
  "data": null
}
```

---

### 5.3 删除处方
**DELETE** `/doctor/prescription/delete/{prescriptionId}`

- 需要请求头: `Token` (医生)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| prescriptionId | path | 是 | 处方ID |

**响应示例**
```json
{
  "code": 0,
  "message": "删除处方成功",
  "data": null
}
```

---

### 5.4 查询所有处方
**GET** `/doctor/prescription/query/all`

- 需要请求头: `Token` (医生)

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    {
      "prescriptionId": 1,
      "cardName": "张三",
      "diagnosis": "感冒",
      "createTime": "2026-03-11 10:00:00"
    }
  ]
}
```

---

### 5.5 查询处方详情
**GET** `/doctor/prescription/query/detail`

- 需要请求头: `Token` (医生)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| prescriptionId | int | 是 | 处方ID |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": {
    "prescriptionId": 1,
    "cardName": "张三",
    "doctorName": "李医生",
    "diagnosis": "感冒",
    "createTime": "2026-03-11 10:00:00",
    "details": [
      {
        "medicineName": "阿莫西林",
        "quantity": 1,
        "usage": "口服"
      }
    ]
  }
}
```

---

## 六、化验员模块 (LabTech)

### 6.1 新增体检项目
**POST** `/labTech/add`

- 需要请求头: `Token` (化验员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| itemName | string | 是 | 项目名称 |
| itemDesc | string | 是 | 项目描述 |
| itemPlace | string | 否 | 检查地点 |
| startTime | datetime | 是 | 开始时间 |
| endTime | datetime | 是 | 结束时间 |
| reserveMax | int | 是 | 最大预约数 |

**响应示例**
```json
{
  "code": 0,
  "message": "添加检查化验项目成功",
  "data": null
}
```

---

### 6.2 删除体检项目
**DELETE** `/labTech/delete`

- 需要请求头: `Token` (化验员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| itemId | int | 是 | 项目ID |

**响应示例**
```json
{
  "code": 0,
  "message": "删除项目成功",
  "data": null
}
```

---

### 6.3 修改体检项目
**POST** `/labTech/update`

- 需要请求头: `Token` (化验员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| itemId | int | 是 | 项目ID |
| itemName | string | 是 | 项目名称 |
| itemDesc | string | 是 | 项目描述 |
| itemPlace | string | 否 | 检查地点 |
| startTime | datetime | 是 | 开始时间 |
| endTime | datetime | 是 | 结束时间 |
| reserveMax | int | 是 | 最大预约数 |

**响应示例**
```json
{
  "code": 0,
  "message": "修改项目成功",
  "data": null
}
```

---

### 6.4 查询所有体检项目
**GET** `/labTech/queryAll`

- 需要请求头: `Token` (化验员)

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": [
    {
      "itemId": 1,
      "itemName": "血常规",
      "itemDesc": "常规血液检查",
      "itemPlace": "二楼检验科",
      "startTime": "2026-03-01 08:00:00",
      "endTime": "2026-03-31 17:00:00",
      "isActive": 1,
      "reserveMax": 30,
      "reserved": 10,
      "reserveEmpty": 20
    }
  ]
}
```

---

### 6.5 查询单个体检项目
**GET** `/labTech/queryOne`

- 需要请求头: `Token` (化验员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| itemId | int | 是 | 项目ID |

**响应示例**
```json
{
  "code": 0,
  "message": null,
  "data": {
    "itemId": 1,
    "itemName": "血常规",
    "itemDesc": "常规血液检查",
    "itemPlace": "二楼检验科",
    "startTime": "2026-03-01 08:00:00",
    "endTime": "2026-03-31 17:00:00",
    "isActive": 1,
    "reserveMax": 30,
    "reserved": 10
  }
}
```

---

### 6.6 发布体检项目
**PATCH** `/labTech/publish`

- 需要请求头: `Token` (化验员)

| 参数名 | 类型 | 必填 | 说明 |
|--------|------|------|------|
| itemId | int | 是 | 项目ID |

**响应示例**
```json
{
  "code": 0,
  "message": "发布项目成功",
  "data": null
}
```

---

## 附录：角色说明

| 角色 | 说明 |
|------|------|
| 管理员 | 可管理员工、科室、值班表 |
| 医生 | 可查看患者、填写处方 |
| 化验员 | 可管理体检项目 |
| 用户 | 可预约挂号、体检 |

---

## 更新日志

| 日期 | 更新内容 |
|------|----------|
| 2026-03-18 | 初始版本，更新所有API接口文档 |
