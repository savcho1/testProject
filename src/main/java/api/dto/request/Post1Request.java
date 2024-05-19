package api.dto.request;

import api.dto.response.Post1Response;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Post1Request {

    int userId;
    int id;
    String title;
    String body;


    private Post1Request(Post1Request.Builder builder) {
        this.userId = builder.userId;
        this.body = builder.body;
        this.title = builder.title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static class Builder {
        private int userId;
        private String body;
        private String title;

        public Builder() {
            // Initialize default values if needmv
        }

        public Post1Request.Builder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public Post1Request.Builder body(String body) {
            this.body = body;
            return this;
        }

        public Post1Request.Builder title(String title) {
            this.title = title;
            return this;
        }

        public Post1Request build() {
            return new Post1Request(this);
        }
    }
}
