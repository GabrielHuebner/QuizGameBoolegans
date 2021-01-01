package at.ac.fhcampuswien.boolegans;

public class Questions {
    private String question, a, b, c, d, answer;

    @Override
    public String toString() {
        return "Questions{" +
                "question='" + question + '\'' +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    public String getQuestion() {
        return question;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getAnswer() {
        return answer;
    }
}
