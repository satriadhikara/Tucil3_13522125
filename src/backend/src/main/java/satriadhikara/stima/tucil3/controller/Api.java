package satriadhikara.stima.tucil3.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import satriadhikara.stima.tucil3.algorithm.AStar;
import satriadhikara.stima.tucil3.algorithm.GreedyBestFirstSearch;
import satriadhikara.stima.tucil3.algorithm.UCS;
import satriadhikara.stima.tucil3.helper.Dictionary;
import satriadhikara.stima.tucil3.model.InputWord;
import satriadhikara.stima.tucil3.model.Response;
import org.springframework.http.ResponseEntity;

@CrossOrigin
@RestController
public class Api {

    @GetMapping(value = "/api", produces = "application/json")
    public ResponseEntity<String> api(
            @RequestParam(value = "StartWord") String startWord,
            @RequestParam(value = "EndWord") String endWord,
            @RequestParam(value = "Method") String method) {
        startWord = startWord.toLowerCase();
        endWord = endWord.toLowerCase();
        if (!Dictionary.isWordExist(startWord)) {
            return new Response("StartWord is not a valid word", null, -1, -1, -1).json();
        }
        if (!Dictionary.isWordExist(endWord)) {
            return new Response("EndWord is not a valid word", null, -1, -1, -1).json();
        }
        if (!method.equals("AStar") && !method.equals("UCS") && !method.equals("GBFS")) {
            return new Response("Method is not valid", null, -1, -1, -1).json();
        }
        if (startWord.length() != endWord.length()) {
            return new Response("Words length is not the same", null, -1, -1, -1).json();
        }

        InputWord input = new InputWord(startWord, endWord);
        Response response;
        long startTime = System.currentTimeMillis();
        Runtime runtime = Runtime.getRuntime();
        long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
        if (method.equals("AStar")) {
            AStar aStar = new AStar();
            response = aStar.search(input);
        } else if (method.equals("UCS")) {
            UCS ucs = new UCS();
            response = ucs.search(input);
        } else {
            GreedyBestFirstSearch gbfs = new GreedyBestFirstSearch();
            response = gbfs.search(input);
        }
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
        response.setExecutionTime(executionTime);
        long memoryUsed = Math.abs(memoryAfter - memoryBefore) / 1024;
        response.setMemoryUsed(memoryUsed);
        return response.json();
    }
}