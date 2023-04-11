package efub.session.community.post.repository;

import efub.session.community.account.dto.PostModifyRequestDto;
import efub.session.community.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostIdAndWriter_AccountId(Long postId, Long accountId); // POST로 접근하는 매개체, pk 타입이 long

}
