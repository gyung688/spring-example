package tacos;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@RedisHash("logoutAccessToken")
@AllArgsConstructor
@Builder
public class LogoutAccessToken {

	@Id
    private String id; 

    @Indexed // �ʵ� ������ ������ ã�� �� �ְ� �ϴ� ������̼�(findByAccessToken)
    private String username;

    @TimeToLive
    private Long expiration; // seconds

    public static LogoutAccessToken createLogoutAccessToken(String accessToken, String username,Long remainingMilliSeconds){
        return LogoutAccessToken.builder()
                .id(accessToken)
                .username(username)
                .expiration(remainingMilliSeconds/1000)
                .build();
    }
}
