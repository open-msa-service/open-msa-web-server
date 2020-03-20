package msa.demo.timeline.service;

import msa.demo.timeline.domain.Comment;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CommentServiceImplTest {

    @Autowired
    private CommentService commentService;


    @Test
    void updateComment(){
        Comment comment = new Comment();
        comment.setUsername("jongmin");
        comment.setContent("dd");
        commentService.updateComment(comment);
    }


    @Test
    @Disabled
    void deleteCommentById(){
        Long commentId = 2L;
        commentService.deleteCommentByCommentId(null);
    }


    @Test
    @Disabled
    void writeComment() {
        Comment comment = new Comment();
        comment.setContent("ㅋㅋ");
        comment.setProfileHref("");
        comment.setUserId("test");
        comment.setUsername("dd");
        commentService.writeComment(comment);
    }
}