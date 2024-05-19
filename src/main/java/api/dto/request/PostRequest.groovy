package api.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.builder.Builder
import groovy.transform.builder.ExternalStrategy

@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRequest {
        int userId
        String body, title
}

@Builder(builderStrategy = ExternalStrategy, forClass = PostRequest)
class PostRequestDtoBuilder {}

