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
    private int nodeVisited;
    @Setter
    private long memoryUsed;

    public Response(String message, List<String> path, int executionTime, int nodeVisited, long memoryUsed) {
        this.message = message;
        this.path = path;
        this.executionTime = executionTime;
        this.nodeVisited = nodeVisited;
        this.memoryUsed = memoryUsed;
    }

    public ResponseEntity<String> json() {
        StringBuilder json = new StringBuilder("{");
        json.append("\"message\":\"").append(message).append("\"");
        if (path != null) {
            json.append(",\"path\":[");
            for (int i = 0; i < path.size(); i++) {
                json.append("\"").append(path.get(i).toUpperCase()).append("\"");
                if (i < path.size() - 1) {
                    json.append(",");
                }
            }
            json.append("],");
            json.append(",\"memoryUsed (KB)\":").append(memoryUsed);
        }
        json.append(",\"executionTime\":").append(executionTime);
        json.append(",\"nodeVisited\":").append(nodeVisited);
        json.append("}");

        if (path == null) {
            return new ResponseEntity<>(json.toString(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(json.toString(), HttpStatus.OK);
        }
    }
}

