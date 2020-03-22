package msa.timeline.msatimeline.controller;


import msa.timeline.msatimeline.domain.Comment;
import msa.timeline.msatimeline.domain.ResponseMessage;
import msa.timeline.msatimeline.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comm")
public class CommentController {

    private final CommentService commentService;

    private ResponseMessage responseMessage;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @DeleteMapping("/delete/{commentId}")
    ResponseEntity<ResponseMessage> deleteCommentById(@PathVariable Long commentId) {
        commentService.deleteCommentByCommentId(commentId);
        responseMessage = new ResponseMessage("", "댓글을 삭제 했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PostMapping("/write")
    ResponseEntity<ResponseMessage> writeComment(@RequestBody Comment comment) {
        commentService.writeComment(comment);
        responseMessage = new ResponseMessage("", "댓글을 작성 했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<ResponseMessage> updateComment(@RequestBody Comment comment) {
        commentService.updateComment(comment);
        responseMessage = new ResponseMessage("", "댓글을 수정 했습니다.");
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }

}
