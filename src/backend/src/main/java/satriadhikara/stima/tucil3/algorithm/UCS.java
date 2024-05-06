package satriadhikara.stima.tucil3.algorithm;

import satriadhikara.stima.tucil3.helper.Node;
import satriadhikara.stima.tucil3.model.InputWord;
import satriadhikara.stima.tucil3.model.Response;
import satriadhikara.stima.tucil3.helper.Dictionary;

import java.util.*;

public class UCS extends Algorithm {

    @Override
    public Response search(InputWord input) {
        String start = input.startWord();
        String end = input.targetWord();

        Queue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(Node::cost));
        Set<String> visited = new HashSet<>();
        queue.add(new Node(start, null, 0, 0));
        visited.add(start);

        int totalNodesVisited = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            String currentWord = current.word();

            totalNodesVisited++;

            if (currentWord.equals(end)) {
                List<String> path = new ArrayList<>();
                int cost = -1;
                while (current != null) {
                    path.add(current.word());
                    cost = current.cost();
                    current = current.parent();
                }
                Collections.reverse(path);
                return new Response("Path found", path, cost, totalNodesVisited, 0);
            }

            Set<String> neighbors = Dictionary.getWordNeighbors(currentWord, visited);
            for (String neighbor : neighbors) {
                int newCost = current.cost() + 1;
                if (!visited.contains(neighbor)) {
                    queue.add(new Node(neighbor, current, newCost, 0));
                    visited.add(neighbor);
                }
            }
        }

        return new Response("No path found", null, 0, totalNodesVisited, -1);
    }
}