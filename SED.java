import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

public class SED {
static String list;
	public interface CostFunction {
		public int cost(int[][] distanceMatrix, CharSequence x, CharSequence y, int i, int j);
	}

	public static final CostFunction ONE = new CostFunction() {
		public int cost(int[][] distanceMatrix, CharSequence x, CharSequence y, int i, int j) {
			return 1;
		}
	};

	public static final CostFunction TWO = new CostFunction() {
		public int cost(int[][] distanceMatrix, CharSequence x, CharSequence y, int i, int j) {
			return 2;
		}
	};

	public static int distance(CharSequence x, CharSequence y) {
		return distance(x, y, false);
	}

	public static int distance(CharSequence x, CharSequence y, boolean jurafskyLevenshtein) {
		int result = 0;
		int[][] d = null;
		if (jurafskyLevenshtein) {
			d = distanceMatrix(x, y, ONE, ONE, TWO);
		} else {
			d = distanceMatrix(x, y, ONE, ONE, ONE);
		}
		result = d[x.length()][y.length()];
		return result;
	}

	public static int distance(CharSequence x, CharSequence y, CostFunction deletion, CostFunction insertion,
			CostFunction substitution) {
		int result = 0;
		int[][] d = distanceMatrix(x, y, deletion, insertion, substitution);
		result = d[x.length()][y.length()];
		return result;
	}

	public static int[][] distanceMatrix(CharSequence x, CharSequence y, CostFunction deletion, CostFunction insertion,
			CostFunction substitution) {
		int[][] d = new int[x.length() + 1][y.length() + 1];
		// initialize
		for (int i = 1; i < d.length; i++)
			d[i][0] = d[i - 1][0] + deletion.cost(d, x, y, i, 0);
		for (int j = 1; j < d[0].length; j++)
			d[0][j] = d[0][j - 1] + insertion.cost(d, x, y, 0, j);
		for (int i = 1; i < d.length; i++) {
			for (int j = 1; j < d[i].length; j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					d[i][j] = d[i - 1][j - 1];
				} else {
					d[i][j] = min(d[i - 1][j] + deletion.cost(d, x, y, i, j),
							d[i][j - 1] + insertion.cost(d, x, y, i, j),
							d[i - 1][j - 1] + substitution.cost(d, x, y, i, j));
				}
			}
		}
		return d;
	}

	private static int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	public static int getMinEditDistance(int[][] distanceMatrix) {
		return distanceMatrix[distanceMatrix.length - 1][distanceMatrix[0].length - 1];
	}

	public static List<int[]> computeBacktrace(int[][] d) {
		List<int[]> backtrace = new ArrayList<int[]>();
		int i = d.length - 1;
		int j = d[0].length - 1;

		while (true) {
			backtrace.add(new int[] { i, j });
			if (i == 1 && j == 1)
				break;
			else if (i == 1 && j > 1)
				j--;
			else if (j == 1 && i > 1)
				i--;
			else {
				if (d[i - 1][j] < d[i - 1][j - 1]) {
					i--;
				} else if (d[i][j - 1] < d[i - 1][j - 1]) {
					j--;
				} else {
					i--;
					j--;
				}
			}
		}
		Collections.reverse(backtrace);

		return backtrace;
	}

	public static void printDistanceMatrix(int[][] d, CharSequence x, CharSequence y) {
		StringBuilder builder = new StringBuilder();
		builder.append("\t\t");
		for (int i = 0; i < y.length(); i++) {
			builder.append(y.charAt(i)).append('\t');
		}
		builder.append('\n');
		for (int i = 0; i < d.length; i++) {
			if (i == 0)
				builder.append("\t");
			else
				builder.append(x.charAt(i - 1)).append("\t");
			for (int j = 0; j < d[i].length; j++) {
				builder.append(d[i][j]).append('\t');
			}
			builder.append('\n');
		}
		System.out.println(builder);
	}

	public static void printAlignment(List<int[]> backtrace, CharSequence x, CharSequence y) {
		StringBuilder builder = new StringBuilder();
		for (int[] pair : backtrace) {
			builder.append(x.charAt(pair[0] - 1));
		}
		System.out.println("x:" + builder.toString());

		builder = new StringBuilder();
		for (int[] pair : backtrace) {
			if (x.charAt(pair[0] - 1) == y.charAt(pair[1] - 1))
				builder.append('|');
			else
				builder.append(' ');
		}
		System.out.println("  " + builder.toString());

		builder = new StringBuilder();
		for (int[] pair : backtrace) {
			builder.append(y.charAt(pair[1] - 1));
		}
		System.out.println("y:" + builder.toString());
	}

	public static void test(CharSequence x, CharSequence y) {
		test(x, y, ONE, ONE, TWO);
	}

	public static void test(CharSequence x, 
                            CharSequence y, 
                            CostFunction deletion, 
                            CostFunction insertion, 
                            CostFunction substitution) {
        System.out.println("*********** " + x + " vs. " + y + " ************\n");
        int[][] distanceMatrix = distanceMatrix(x, y, deletion, insertion, substitution);
        int minEditDistance = getMinEditDistance(distanceMatrix);
        System.out.println("min edit distance: " + minEditDistance + "\n");
        printDistanceMatrix(distanceMatrix, x, y);
        List<int[]> backtrace = computeBacktrace(distanceMatrix);
        printAlignment(backtrace, x, y);
        System.out.println('\n');
        {
    
    try
    {
        BufferedReader br = new BufferedReader(new FileReader("chzone.txt"));
        String username = "postgres";
        String pwd = "algorithm";
        String connurl = "jdbc:postgresql://localhost:32768/BDS_CSF_AuqliteEau";

        Connection con = DriverManager.getConnection(connurl, username, pwd);
        Class.forName("org.postgresql.Driver");

        String line = null;
        PreparedStatement ps;
		while ((line = br.readLine()) != null)
        {
            String tmp[] = line.split(",");
            String date = tmp[0];
            String heure = tmp[1];
            String parametre = tmp[2];
            String valeur = tmp[3];

            System.out.println(date + "\t" + heure + "\t" + parametre + "\t" + valeur);
            String sql =
                    "INSERT INTO resultat (date_resultat,valeur,code_pc,code_parametre,heure_resultat) values ('"
                            + date + "','" + valeur + "','1','" + parametre + "','" + heure +
                            "')";

            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }

        br.close();
        con.close();
        ps.close();

    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
finally {
	System.out.println("File not found");
}
    Scanner sc = new Scanner(System.in);
    String gn=sc.next();
    SED.setGN(gn);
    String s1, value;
    if(gn.equals(SED.getGN()))
    		{
    		System.out. println("Already set that");
    		}
    else {
    	System.out.println("String not matched");
    }

       
        // lexicographical comparison of String in Java with ComapreTo
        if (s1.compareTo(list) > 0) {
            System.out.println("Matched in lexicographical order" +  value);
        } else if (s1.compareTo(list) < 0) {
            System.out.println(" Not matched in lexicographical order" + value);
        }
    }
	}
    public static void main(String[] args) {
        test("hello", "yellow");
        test("sitting", "kitten");
        test("Sunday", "Saturday");
        test("comb", "love");
        test("intention", "execution");
    }
}

	private static void setGN(String gn) {
		// TODO Auto-generated method stub

	}

	private static Object getGN() {
		// TODO Auto-generated method stub
		return null;
	}
}