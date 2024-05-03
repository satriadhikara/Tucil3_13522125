package satriadhikara.stima.tucil3.helper;

public record Node(String word, satriadhikara.stima.tucil3.helper.Node parent, int cost, int heuristic) {
    public int getTotalCost() {
        return cost + heuristic;
    }
}
