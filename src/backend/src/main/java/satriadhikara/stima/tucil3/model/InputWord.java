package satriadhikara.stima.tucil3.model;

import jakarta.validation.constraints.NotBlank;

public record InputWord(@NotBlank String startWord, @NotBlank String targetWord) {
    public InputWord(String startWord, String targetWord) {
        this.startWord = startWord;
        this.targetWord = targetWord;
    }
}
