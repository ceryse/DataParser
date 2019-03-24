public class Data {
    private String state;
    private double percentVoted; //percent voted out of total population
    private int medianIncome;

    public Data(String state, String percentVoted, String medianIncome) {
        this.state = state;
        this.percentVoted = Double.parseDouble(percentVoted);
        this.medianIncome = Integer.parseInt(medianIncome);
    }

    public int getMedianIncome() {
        return medianIncome;
    }

    public void setMedianIncome(int medianIncome) {
        this.medianIncome = medianIncome;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getPercentVoted() {
        return percentVoted;
    }

    public void setPercentVoted(double percentVoted) {
        this.percentVoted = percentVoted;
    }
}
