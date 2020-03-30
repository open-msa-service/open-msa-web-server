# TimeLine

### 공통 Header
| Header | value |
| -----| ----- |
| Authorization | Bearer {access-token} |

### TimeLine main 조회
##### /time/main/{userId}
사용자와 친구의 타임라인과 작성한 댓글이 포함된 글을 조회

| Parameter | Description |
| ----- | ----- |
| userId | 조회하려는 User Id |

```
method : GET

{
    "status": 200,
    "data": {
        "member": {
            "id": null,
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
        "timeline": [
            {
                "timeId": 52,
                "content": "안녕하세요!! 반갑습니다!!",
                "scope": "ALL",
                "fileLocation": "unnamed.jpg,",
                "userId": "test2",
                "username": "김정민",
                "profileHref": "/images/unnamed.png",
                "updateTime": "2020-03-27",
                "comments": [
                    {
                        "commentId": 14,
                        "content": "안녕하세요!!!",
                        "userId": "test",
                        "profileHref": "/images/201604141550096308_d.jpg",
                        "username": "이종민",
                        "updateTime": "2020-03-27",
                        "tempTimeId": null
                    },
                    {
                        "commentId": 13,
                        "content": "안녕하세요!",
                        "userId": "test",
                        "profileHref": "/images/unnamed.png",
                        "username": "김정민",
                        "updateTime": "2020-03-27",
                        "tempTimeId": null
                    }
                ],
                "likes": [],
                "fileNameList": null
            },
            // 생략.....
        ]
    },
    "message": "타임라인을 조회했습니다.",
    "time": "2020-03-30T21:30:43.851"
}
```

### TimeLine 조회
##### /time/{userId}
사용자의 게시글만을 조회

| Parameter | Description |
| ----- | ----- |
| userId | 조회하려는 User Id |
```
method: GET

{
    "status": 200,
    "data": {
        "member": {
            "id": null,
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
        "timeLine": [
            {
                "timeId": 51,
                "content": "오늘 날씨가 춥습니다!\n다들 옷 따뜻하게 입으세요!!",
                "scope": "ALL",
                "fileLocation": "unnamed.jpg,",
                "userId": "test",
                "username": "이종민",
                "profileHref": "/images/201604141550096308_d.jpg",
                "updateTime": "2020-03-27",
                "comments": [
                    {
                        "commentId": 15,
                        "content": "안녕하세요~",
                        "userId": "test2",
                        "profileHref": "/images/unnamed.png",
                        "username": "김정민",
                        "updateTime": "2020-03-29",
                        "tempTimeId": null
                    }
                ],
                "likes": [],
                "fileNameList": null
            },
            // 생략...
        ]
    },
    "message": "test의 타임라인을 조회했습니다.",
    "time": "2020-03-30T21:32:11.749"
}
```

### 다른 사용자 방문
##### /time/visit/{userId}/{visitName}
친구일 경우와 아닐 경우의 범위를 다르게 해서 조회

| Parameter | Description |
| ----- | ----- |
| userId | 사용자 Id |
| visitName | 방문한 페이지 Id |
```
method : GET

{
    "status": 200,
    "data": {
        "member": {
            "id": null,
            "userId": "test2",
            "username": "김정민",
            "password": "",
            "socialId": "null",
            "profileHref": "/images/unnamed.png",
            "userRole": "ROLE_USER",
            "email": "jm0520@hanmail.net",
            "phoneNumber": "010-1111-2222",
            "statusMessage": "안녕하세요!!",
            "introduceMessage": " 안녕하세요!! 반갑습니다!!",
            "updateTime": "2020-03-27 17:44:56"
        },
        "timeline": [
            {
                "timeId": 52,
                "content": "안녕하세요!! 반갑습니다!!",
                "scope": "ALL",
                "fileLocation": "unnamed.jpg,",
                "userId": "test2",
                "username": "김정민",
                "profileHref": "/images/unnamed.png",
                "updateTime": "2020-03-27",
                "comments": [
                    {
                        "commentId": 14,
                        "content": "안녕하세요!!!",
                        "userId": "test",
                        "profileHref": "/images/201604141550096308_d.jpg",
                        "username": "이종민",
                        "updateTime": "2020-03-27",
                        "tempTimeId": null
                    },
                    {
                        "commentId": 13,
                        "content": "안녕하세요!",
                        "userId": "test",
                        "profileHref": "/images/unnamed.png",
                        "username": "김정민",
                        "updateTime": "2020-03-27",
                        "tempTimeId": null
                    }
                ],
                "likes": [],
                "fileNameList": null
            }
        ],
        "isFriend": true
    },
    "message": "test2의 타임라인을 조회했습니다.",
    "time": "2020-03-30T21:34:13.241"
}
```

### 게시글 작성
##### /time/write
| Parameter | Description |
| ----- | ----- |
| timeline | 아래의 Parameter들을 String으로 직렬화 |
| content | 내용 |
| scope | 범위(ALL, Friend) |
| userId | 작성자 id |
| username | 작성자 이름 |
| profileHref | 작성자 프로필 사진 |

| Parameter | Description |
| ----- | ----- |
| file | 게시글 사진(Multipart[]) |

```
method : POST
Content-Type : multipart/form-data

{
	"status":200,
	"data": "",
	"message":"타임라인을 작성했습니다.",
	"time":"2020-03-30T21:46:43.321"
}
```


### 게시글 수정
##### /time/update
| Parameter | Description |
| ----- | ----- |
| timeline | 아래의 Parameter들을 String으로 직렬화 |
| content | 내용 |
| scope | 범위(ALL, Friend) |
| userId | 작성자 id |
| username | 작성자 이름 |
| profileHref | 작성자 프로필 사진 |

| Parameter | Description |
| ----- | ----- |
| file | 게시글 사진(Multipart[]) |

```
method : PUT
Content-Type : multipart/form-data

{
	"status":200,
	"data": "",
	"message":"타임라인을 수정했습니다.",
	"time":"2020-03-30T21:47:23.241"
}
```

### 게시글 삭제
##### /time/delete/{timeId}
| Parameter | Description |
| ----- | ----- |
| timeId | 게시글 번호 |

```
method : GET

{
	"status":200,
	"data": "",
	"message":"타임라인을 삭제했습니다.",
	"time":"2020-03-30T21:47:53.259"
}
```

# Comment

### 댓글 작성
##### /comment/write
| Parameter | Description |
| ----- | ----- |
| content | 댓글 내용 |
| userId | 댓글 작성자 id |
| username | 댓글 작성자 이름 |
| profileHref | 댓글 작성자 프로필 사진 |
| tempTimeId | 게시글 번호 |

```
method : POST
Content-Type: application/json

{
	"status":200,
	"data": "",
	"message":"댓글을 작성 했습니다.",
	"time":"2020-03-30T21:56:12.206"
}
```


### 댓글 수정
##### /comment/update
| Parameter | Description |
| ----- | ----- |
| content | 댓글 내용 |
| userId | 댓글 작성자 id |
| username | 댓글 작성자 이름 |
| profileHref | 댓글 작성자 프로필 사진 |
| tempTimeId | 게시글 번호 |

```
method : PUT
Content-Type: application/json

{
	"status":200,
	"data": "",
	"message":"댓글을 수정 했습니다.",
	"time":"2020-03-30T21:56:12.206"
}
```

### 댓글 삭제
##### /comment/delete/{commentId}
| Parameter | Description |
| commentId | 댓글 번호 |

```
method : GET

{
	"status":200,
	"data": "",
	"message":"댓글을 삭제 했습니다.",
	"time":"2020-03-30T21:58:41.928"
}
```
