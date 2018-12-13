import java.text.DecimalFormat;

/**
 *
 * @author fm
 */
public class IREvaluation {

    private static DecimalFormat formatter = new DecimalFormat("#0.00");

    public static void main(String[] args) {
   
        // String[] query = {"N", "U", "N", "R", "R", "N", "U", "R", "U", "U", "R", "R", "N", "N", "N", "R", "U", "U", "N", "R"};
        //int r = 10;
        //  String[] query = {"U", "R", "R", "N", "N", "N", "N", "N", "R", "R", "R", "U", "U", "N", "N", "R", "U", "N", "R", "U"};
        // int r = 15;
        String[] query = {"R", "U", "N", "N", "N", "N", "R", "U", "U", "U", "N", "N", "R", "R", "U", "R", "N", "N", "R", "R"};
        int r = 12;

        System.out.println("R:  " + r);

        precision(query);

        recall(query, r);

        precisionAt(query, 5);

        precisionAt(query, 10);

        avaragePrecision(query, r);

        reciprocalRank(query);

        bPref(query, r, 10);

        interpolatedGraph(query, r, 10);
    }

    private static void precision(String[] query) {
        double count = 0;
        double p = 0.000;
        for (int i = 0; i < query.length; i++) {
            if (query[i].equalsIgnoreCase("R")) {
                count++;
            }
        }
        p = count / query.length;
        System.out.println("Precision: " + count + "/" + query.length + " = " + formatter.format(p));
    }

    private static void recall(String[] query, int r) {
        double count = 0;
        double p = 0.000;
        for (int i = 0; i < query.length; i++) {
            if (query[i].equalsIgnoreCase("R")) {
                count++;
            }
        }
        p = count / r;
        System.out.println("Recall: " + count + "/" + r + " = " + formatter.format(p));
    }

    private static void precisionAt(String[] query, int position) {
        double count = 0;
        double p = 0.000;
        for (int i = 0; i < position; i++) {
            if (query[i].equalsIgnoreCase("R")) {
                count++;
            }
        }
        p = count / position;
        System.out.println("Precision@" + position + ": " + count + "/" + position + " = " + formatter.format(p));
    }

    private static void avaragePrecision(String[] query, int r) {
        double count = 0;
        double p = 0.000;
        double sum = 0.0;
        System.out.println("----------Average Precision----------");
        for (int i = 0; i < query.length; i++) {
            if (query[i].equalsIgnoreCase("R")) {
                count++;
                sum = sum + (count / (i + 1));

                System.out.println("val: " + count + "/" + (i + 1) + " = " + (count / (i + 1)));
            }
        }
        p = sum / r;
        System.out.println("Average Precision: " + formatter.format(sum) + "/" + r + " = " + formatter.format(p));
    }

    private static void reciprocalRank(String[] query) {
        double count = 0;
        double p = 0;
        for (int i = 0; i < query.length; i++) {
            if (query[i].equalsIgnoreCase("R")) {
                count++;
                p = count / (i + 1);
                System.out.println("Reciprocal Rank: " + count + "/" + (i + 1) + " = " + formatter.format(p));
                break;
            }
        }
    }

    private static void bPref(String[] query, int r, int position) {
        double Rcount = 0;
        double p = 1 / (double) r;
        double sum = 0;
        double bprefv = 0;
        System.out.println("----------Bpref----------");

        for (int i = 0; i < query.length; i++) {
            double Ncount = 0;
            if (query[i].equalsIgnoreCase("R")) {
                Rcount++;
                for (int j = 0; j < i; j++) {
                    if (query[j].equalsIgnoreCase("N")) {
                        Ncount++;
                    }
                }
                System.out.println("R position:" + (i + 1) + " NCount: " + (Ncount));
                sum = sum + (1 - ((Ncount) / (r + position)));
                System.out.println("sum: 1-(" + (Ncount + "/" + (r + position)) + ") = " + (1 - ((Ncount) / (r + position))));
            }
        }
        bprefv = p * sum;
        System.out.println("Bpref= 1/" + r + "(" + sum + ")");
        System.out.println("BPref: " + formatter.format(bprefv));
    }

    private static void interpolatedGraph(String[] query, int r, int position) {
        double count = 0;
        double sum = 0.0;
        System.out.println("----------Interpolated Graph----------");
        for (int i = 0; i < query.length; i++) {
            if (query[i].equalsIgnoreCase("R")) {
                count++;
                sum = (count / (i + 1));
                System.out.println("Precision @ " + (i + 1) + " = " + count + "/" + (i + 1) + " = " + sum);
            }
        }
    }
}
