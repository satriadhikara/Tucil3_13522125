package satriadhikara.stima.tucil3.helper;

import lombok.Getter;

public record Node(String word, @Getter satriadhikara.stima.tucil3.helper.Node parent, int cost,
                   int heuristic) {
    public int getTotalCost() {
        return cost + heuristic;
    }
}
