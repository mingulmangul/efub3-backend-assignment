package efub.session.community.post.dto;

import lombok.Getter;

@Getter


public class PostRequestDto {
    private Long accountID;
    private String title;
    private String content;
}
