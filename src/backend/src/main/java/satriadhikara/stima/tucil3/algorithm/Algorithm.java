package satriadhikara.stima.tucil3.algorithm;

import satriadhikara.stima.tucil3.helper.Node;
import satriadhikara.stima.tucil3.model.InputWord;
import satriadhikara.stima.tucil3.model.Response;

import java.util.LinkedList;
import java.util.List;

public abstract class Algorithm {
    public abstract Response search(InputWord input);

    protected int getHeuristic(String current, String target) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != target.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    protected List<String> constructPath(Node node) {
        LinkedList<String> path = new LinkedList<>();
        Node current = node;
        while (current != null) {
            path.addFirst(current.word());
            current = current.parent();
        }
        return path;
    }
}
