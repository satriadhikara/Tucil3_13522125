package satriadhikara.stima.tucil3.algorithm;

import satriadhikara.stima.tucil3.helper.Node;
import satriadhikara.stima.tucil3.model.InputWord;
import satriadhikara.stima.tucil3.model.Response;
import satriadhikara.stima.tucil3.helper.Dictionary;

import java.util.*;

public class UCS extends Algorithm {
    @Override
    public Response search(InputWord input) {
        String startWord = input.startWord();
        String targetWord = input.targetWord();

        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(Node::cost));
        Map<String, Integer> costSoFar = new HashMap<>();

        frontier.add(new Node(startWord, null, 0, 0));
        costSoFar.put(startWord, 0);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.word().equals(targetWord)) {
                return new Response("Path found", constructPath(current), 0);
            }

            List<String> neighbors = Dictionary.getWordNeighbors(current.word());
            for (String neighbor : neighbors) {
                int newCost = current.cost() + 1;
                if (!costSoFar.containsKey(neighbor) || newCost < costSoFar.get(neighbor)) {
                    costSoFar.put(neighbor, newCost);
                    frontier.add(new Node(neighbor, current, newCost, 0));
                }
            }
        }

        return new Response("No path found", null, -1);
    }
}
