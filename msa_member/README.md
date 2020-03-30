# Member

### 공통 Header
| Header | value |
| -----| ----- |
| Authorization | Bearer {access-token} |

### Member 조회
##### /member/{userId}
| Parameter | Description |
| ----- | ----- |
| userId | 조회하려는 User Id |

```
method : GET

{
    "status": 200,
    "data": {
        "id": 44,
        "userId": "test",
        "username": "이종민",
        "socialId": "null",
        "profileHref": "/images/201604141550096308_d.jpg",
        "userRole": "ROLE_USER",
        "email": "jongminlee0218@gmail.com",
        "phoneNumber": "010-1234-2212",
        "statusMessage": "기분 좋은 하루입니다~",
        "introduceMessage": "안녕하세요 반갑습니다!!",
        "updateTime": "2020-03-27 17:11:35"
    },
    "message": "회원정보를 가져오는데 성공했습니다.",
    "time": "2020-03-30T19:53:30.612"
}
```

### 사용자 조회
##### /member/search/{username}
| Parameter | Description |
| ----- | ----- |
| username | 사용자이름 |


```
method : GET

{
	"status":200,
	"data":[{"id":44,
				"userId":"test",
				"username":"이종민",
				"socialId":"null",
				"profileHref":"/images/201604141550096308_d.jpg",
				"userRole":"ROLE_USER",
				"email":"jongminlee0218@gmail.com",
				"phoneNumber":"010-1234-2212",
				"statusMessage":"기분 좋은 하루입니다~",
				"introduceMessage":"안녕하세요 반갑습니다!!",
				"updateTime":"2020-03-27 17:11:35"}
	],
	"message":"회원정보를 가져오는데 성공했습니다.",
	"time":"2020-03-30T19:59:47.431"
}
```
### 사용자 정보 수정
##### /member/update
| Parameter | Description |
| ----- | ----- |
| members | 아래의 Parameter를 String으로 직렬화 |
| email | 사용자 email |
| phoneNumber | 사용자 핸드폰번호 |
| statusMessage | 상태메시지 |
| introduceMessage | 사용자 소개말 |

| Parameter | Description |
| ----- | ----- |
| file | 프로필 사진 |
```
method : PUT
Content-Type : multipart/form-data

{
	"status":200,
	"data": "",
	"message":"회원정보 수정에 성공했습니다.",
	"time":"2020-03-30T20:20:43.321"
}
```

# Friend
### 친구 목록 조회
##### /friend/allList/{userId}
| Parameter | Description |
| ----- | ----- |
| userId | 사용자 Id |

```
method : GET

{
    "status": 200,
    "data": {
        "friend": [
            {
                "id": 45,
                "userId": "test2",
                "username": "김정민",
                "socialId": "null",
                "profileHref": "/images/unnamed.png",
                "userRole": "ROLE_USER",
                "email": "jm0520@hanmail.net",
                "phoneNumber": "010-1111-2222",
                "statusMessage": "안녕하세요!!",
                "introduceMessage": " 안녕하세요!! 반갑습니다!!",
                "updateTime": "2020-03-27 17:44:56"
            }
        ],
        "requestFriend": []
    },
    "message": "친구목록을 가져왔습니다.",
    "time": "2020-03-30T21:10:03.109"
}
```
| Parameter | Description |
| ----- | ----- |
| friend | 친구 리스트 |
| requestFriend | 친구 요청 목록 |


### 친구 요청
##### /friend/request
| Parameter | Description |
| ----- | ----- |
| userId1 | 사용자 Id |
| userId2 | 요청 받을 친구 |

```
method : POST
Content-Type : application/json

{
	"status":200,
	"data": "",
	"message":"친구 요청을 보냈습니다.",
	"time":"2020-03-30T20:23:50.378"
}
```

### 친구 수락
##### /friend/accept
| Parameter | Description |
| ----- | ----- |
| userId1 | 사용자 Id |
| userId2 | 요청 받을 친구 |
```
method : POST
Content-Type : application/json

{
	"status":200,
	"data": "",
	"message":"친구 요청을 수락했습니다.",
	"time":"2020-03-30T20:23:52.623"
}
```
