import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private List<State> states;

    public DataManager() {
        this.states = new ArrayList<>();
    }

    public int getMedianIncome() {
        int[] incomes = getOrderedIncomeList();
        return incomes[(incomes.length + 1) / 2];
    }

    private int[] getOrderedIncomeList() {
        int[] list = new int[states.size()];
        for (int i = 0; i < list.length; i++) {
            list[i] = states.get(i).getData().getMedianIncome();
        }
        return insertionSort(list);
    }

    private static int[] insertionSort(int[] arr) {
        for (int start = 1; start < arr.length; start++) {
            int locToInsert = start;

            while (locToInsert != 0 && arr[locToInsert] < arr[locToInsert - 1]) {
                swap(arr, locToInsert, locToInsert - 1);
                locToInsert--;
            }
        }
        return arr;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public void loadAllData(String incomeFile, String votersFile) {
        String[] incomeRawLines = Utils.getCleanLines(incomeFile, 2);
        String[] voterRawLines = Utils.getCleanLines(votersFile, 11);

        addStateObjs(incomeRawLines, voterRawLines);

        setIsHighIncome();
    }

    private void setIsHighIncome() {
        double medianIncome = getMedianIncome();
        for (State state : states) {
            if (state.getData().getMedianIncome() > medianIncome) {
                state.getData().setHighIncome(true);
            } else {
                state.getData().setHighIncome(false);
            }
        }
    }

    private void addStateObjs(String[] incomeRawLines, String[] voterRawLines) {
        for (int i = 0; i < incomeRawLines.length; i++) {
            String voterLine = voterRawLines[i];
            String incomeLine = incomeRawLines[i];
            if (voterLine == null) System.out.println("voter line null");
            if (incomeLine == null) System.out.println("income line null");
            String[] incomeVals = incomeLine.split(",");
            String[] voterVals = voterLine.split(",");
            State state = new State(incomeVals[1], new Data(incomeVals[1], voterVals[9], incomeVals[11]));
            states.add(state);
        }
    }

    public List<State> getStates() {
        return states;
    }

    public void add(State state) {
        states.add(state);
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}