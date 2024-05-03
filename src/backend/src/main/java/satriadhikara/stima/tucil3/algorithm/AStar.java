package satriadhikara.stima.tucil3.algorithm;

import satriadhikara.stima.tucil3.helper.Node;
import satriadhikara.stima.tucil3.model.InputWord;
import satriadhikara.stima.tucil3.model.Response;
import satriadhikara.stima.tucil3.helper.Dictionary;

import java.util.*;

public class AStar extends Algorithm {
    @Override
    public Response search(InputWord input) {
        String startWord = input.startWord();
        String targetWord = input.targetWord();

        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator.comparingInt(Node::getTotalCost));
        Set<String> visited = new HashSet<>();

        frontier.add(new Node(startWord, null, 0, getHeuristic(startWord, targetWord)));
        visited.add(startWord);

        while (!frontier.isEmpty()) {
            Node current = frontier.poll();

            if (current.word().equals(targetWord)) {
                return new Response("Path found", constructPath(current), 0);
            }

            List<String> neighbors = Dictionary.getWordNeighbors(current.word());
            for (String neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    int cost = current.cost() + 1;
                    int heuristic = getHeuristic(neighbor, targetWord);
                    frontier.add(new Node(neighbor, current, cost, heuristic));
                }
            }

        }

        return new Response("No path found", null, -1);
    }
}