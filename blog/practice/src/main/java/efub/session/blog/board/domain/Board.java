package efub.session.blog.board.domain;

import efub.session.blog.board.dto.BoardModifyRequestDto;
import efub.session.blog.global.BaseTimeEntity;
import efub.session.blog.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter

public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String explain;

    @Column(columnDefinition = "TEXT")
    private String notice;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member owner;

    @Builder
    public Board(Long boardId, String name, String explain, String notice, Member owner){
        this.boardId=boardId;
        this.name=name;
        this.explain=explain;
        this.notice=notice;
        this.owner=owner;
    }

    public void updateBoard(BoardModifyRequestDto requestDto){
        this.name=requestDto.getName();
        this.explain=requestDto.getExplain();
        this.notice=requestDto.getNotice();
    }
}
