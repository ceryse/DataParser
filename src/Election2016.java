public class Election2016 {
    private double demVotes;
    private double gopVotes;
    private double totalVotes;
    private County county;

    public Election2016(double demVotes, double gopVotes, double totalVotes, County county) {
        this.demVotes = demVotes;
        this.gopVotes = gopVotes;
        this.totalVotes = totalVotes;
        this.county = county;
    }

    public double getDemVotes() {
        return demVotes;
    }

    public void setDemVotes(double demVotes) {
        this.demVotes = demVotes;
    }

    public double getGopVotes() {
        return gopVotes;
    }

    public void setGopVotes(double gopVotes) {
        this.gopVotes = gopVotes;
    }

    public double getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(double totalVotes) {
        this.totalVotes = totalVotes;
    }
}
