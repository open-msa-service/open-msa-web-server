package msa.member.msamember.controller;


import msa.member.msamember.domain.Friend;
import msa.member.msamember.domain.ResponseMessage;
import msa.member.msamember.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;
    private ResponseMessage responseMessage;

    public FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @GetMapping("/allList/{userId}")
    ResponseEntity<ResponseMessage> getFriendListByUserId(@PathVariable String userId){
        responseMessage = new ResponseMessage(friendService.getMyFriendListByUserId(userId), "친구목록을 가져왔습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/request")
    ResponseEntity<ResponseMessage> requestFriend(@RequestBody Friend friend){
        friendService.requestFriendByUserId(friend.getUserId1(), friend.getUserId2());
        responseMessage = new ResponseMessage("", "친구 요청을 보냈습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }


    @PutMapping("/accept")
    ResponseEntity<ResponseMessage> acceptFriendShip(@RequestBody Friend friend){
        friendService.accpetFriendByUserID(friend.getUserId1(), friend.getUserId2());
        responseMessage = new ResponseMessage("", "친구 요청을 수락했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/list/{userId}")
    String[] restClientGetFriendList(@PathVariable String userId){
        return friendService.restClientFriendList(userId);
    }

    @GetMapping("/isFriend/{userId}/{visitId}")
    boolean restClientIsFriend(@PathVariable String userId, @PathVariable String visitId){
        return friendService.restClientIsFriend(userId, visitId);
    }



}
