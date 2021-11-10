package dunice.news.common;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseErrorDTO {
    private List<Integer> codes;
    @Builder.Default
    private int statusCode = 1;
    @Builder.Default
    private boolean success = true;
    private String timeStamp;
}
