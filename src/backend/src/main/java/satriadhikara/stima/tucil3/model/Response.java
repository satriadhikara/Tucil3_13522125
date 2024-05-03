package satriadhikara.stima.tucil3.model;

import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;

public class Response {
    private final String message;
    private final List<String> path;
    @Setter
    private long executionTime;

    public Response(String message, List<String> path, int executionTime) {
        this.message = message;
        this.path = path;
        this.executionTime = executionTime;
    }

    public ResponseEntity<String> json() {
        StringBuilder json = new StringBuilder("{");
        json.append("\"message\":\"").append(message).append("\"");
        if (path != null) {
            json.append(",\"path\":[");
            for (int i = 0; i < path.size(); i++) {
                json.append("\"").append(path.get(i)).append("\"");
                if (i < path.size() - 1) {
                    json.append(",");
                }
            }
            json.append("],");
            json.append("\"executionTime\":").append(executionTime);
        }
        json.append("}");

        if (path == null) {
            return new ResponseEntity<>(json.toString(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(json.toString(), HttpStatus.OK);
        }
    }
}

