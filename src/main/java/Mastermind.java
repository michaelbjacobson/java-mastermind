public class Mastermind {

    private String code;
    private CodeCreator codeCreator;

    Mastermind(CodeCreator codeCreator) {
        this.codeCreator = codeCreator;
        this.code = codeCreator.newCode();
    }

    String guess(String guess) {
        if ( guess.matches("[012345]{4}") ) {
            return feedbackForGuess(guess);
        }
        return null;
    }

    void reset() {
        this.code = codeCreator.newCode();
    }

    String getCode() {
        return this.code;
    }


    private String feedbackForGuess(String guess) {

        boolean[] codeUsed = new boolean[this.code.length()];
        boolean[] guessUsed = new boolean[guess.length()];
        StringBuilder feedback = new StringBuilder();
        
        for (int i = 0; i < this.code.length(); i++) {
            if (this.code.charAt(i) == guess.charAt(i)) {
                feedback.append("correct ");
                codeUsed[i] = guessUsed[i] = true;
            }
        }
        
        for (int i = 0; i < this.code.length(); i++) {
            for (int j = 0; j < guess.length(); j++) {
                if (!codeUsed[i] && !guessUsed[j] && this.code.charAt(i) == guess.charAt(j)) {
                    feedback.append("colorMatch ");
                    codeUsed[i] = guessUsed[j] = true;
                    break;
                }
            }
        }

        return feedback
                .toString()
                .trim()
                .replace(" ", ",");
    }

}
