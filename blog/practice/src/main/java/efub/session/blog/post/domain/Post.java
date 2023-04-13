package efub.session.blog.post.domain;

import efub.session.blog.global.BaseTimeEntity;
import efub.session.blog.member.domain.Member;
import efub.session.blog.post.dto.PostModifyRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column
    private Boolean anonymous;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member owner;

    @Builder
    public Post(Long postId, Boolean anonymous, String content, Member owner){
        this.postId=postId;
        this.anonymous=anonymous;
        this.content=content;
        this.owner=owner;
    }

    public void updatePost(PostModifyRequestDto requestDto){
        this.anonymous=requestDto.getAnonymous();
        this.content=requestDto.getContent();
    }

}
