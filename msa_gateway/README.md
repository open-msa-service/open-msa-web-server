# Gateway

### Token 발급
##### /oauth/token

| Header | auth | Description |
| ----- | ----- | ----- |
| username | mas-id | client-id |
| password | mas-password | client-secret |

| Parameter | Description |
| ----- | ----- |
| username | 사용자 id |
| password | 사용자 password |
| grant_type | 인증방식("password") 지정 |

```
method : POST
Content-Type : application/x-www-form-urlencoded charset=utf-8

{
    "access_token": "07b837b1-0e85-4466-9d26-d61d3798c106",
    "token_type": "bearer",
    "refresh_token": "8669e9ef-1407-4974-834c-54143acdaa30",
    "expires_in": 3599,
    "scope": "read write trust"
}
```

### 회원가입
##### /register
| Parameter | Description |
| ----- | ----- |
| Entity | Member |
| userId | 사용자 id |
| password | 사용자 password |
| username | 사용자 이름 |
| email | 사용자 이메일 |
| phoneNumber | 핸드폰 번호 |

```
method : POST
Content-Type : applicaion/json

{
    "status": 200,
    "data": "",
    "message": "회원가입을 완료했습니다.",
    "time": "2020-03-30T19:48:56.258"
}
```
