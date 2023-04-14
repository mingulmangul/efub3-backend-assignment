package efub.session.community.post.domain;

import efub.session.community.account.domain.Member;
import efub.session.community.account.dto.PostModifyRequestDto;
import efub.session.community.global.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter

public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 중요한 pk 값이다.
    private Long postId;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT") // 어떤 컬럼인지 마이에스큐엘에서 텍스트다 ~
    private String content;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Member writer; // 난 멤버로 함.. // 이 post를 글쓴이

    @Builder
    public Post(Long postId, String title, String content, Member writer) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public void updatePost(PostModifyRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
