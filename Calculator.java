import java.util.Scanner;

public class Calculator {

    static Scanner scanner = new Scanner(System.in);

    // ── Factorial ──────────────────────────────────────────────────────────────
    static long factorial(int n) {
        if (n < 0) return -1;
        if (n == 0 || n == 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    // ── Permutation P(n,r) ─────────────────────────────────────────────────────
    static long permutation(int n, int r) {
        if (r > n) return 0;
        return factorial(n) / factorial(n - r);
    }

    // ── Combination C(n,r) ─────────────────────────────────────────────────────
    static long combination(int n, int r) {
        if (r > n) return 0;
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    // ── Statistics ─────────────────────────────────────────────────────────────
    static void statistics(double[] nums) {
        int n = nums.length;
        double sum = 0, min = nums[0], max = nums[0];

        for (double v : nums) {
            sum += v;
            if (v < min) min = v;
            if (v > max) max = v;
        }

        double mean = sum / n;

        double variance = 0;
        for (double v : nums) variance += Math.pow(v - mean, 2);
        variance /= n;

        double stddev = Math.sqrt(variance);

        // Median
        double[] sorted = nums.clone();
        java.util.Arrays.sort(sorted);
        double median = (n % 2 == 0)
            ? (sorted[n/2 - 1] + sorted[n/2]) / 2.0
            : sorted[n/2];

        System.out.println("\n─── Statistics Results ───");
        System.out.println("Count    : " + n);
        System.out.println("Sum      : " + sum);
        System.out.println("Mean     : " + mean);
        System.out.println("Median   : " + median);
        System.out.println("Std Dev  : " + stddev);
        System.out.println("Variance : " + variance);
        System.out.println("Min      : " + min);
        System.out.println("Max      : " + max);
    }

    // ── Matrix multiply (2x2) ──────────────────────────────────────────────────
    static double[][] matMul(double[][] A, double[][] B) {
        int n = A.length;
        double[][] C = new double[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    C[i][j] += A[i][k] * B[k][j];
        return C;
    }

    static double matDet2(double[][] m) {
        return m[0][0]*m[1][1] - m[0][1]*m[1][0];
    }

    static void printMatrix(double[][] m) {
        for (double[] row : m) {
            System.out.print("[ ");
            for (double v : row) System.out.printf("%-10.4f", v);
            System.out.println("]");
        }
    }

    // ── Read a 2x2 matrix from input ───────────────────────────────────────────
    static double[][] readMatrix(String name) {
        double[][] m = new double[2][2];
        System.out.println("Enter Matrix " + name + " (2x2):");
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++) {
                System.out.print("  [" + i + "][" + j + "]: ");
                m[i][j] = scanner.nextDouble();
            }
        return m;
    }

    // ── Main Menu ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║   SCIENTIFIC CALCULATOR v1.0     ║");
        System.out.println("╚══════════════════════════════════╝");

        boolean running = true;
        while (running) {
            System.out.println("\n════ MENU ════");
            System.out.println("1. Basic Arithmetic");
            System.out.println("2. Trigonometric Functions");
            System.out.println("3. Hyperbolic Functions");
            System.out.println("4. Log / Power / Root");
            System.out.println("5. Permutation & Combination");
            System.out.println("6. Statistics");
            System.out.println("7. Matrix Operations (2x2)");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int choice = scanner.nextInt();

            switch (choice) {

                // ── 1. Basic Arithmetic ────────────────────────────────────────
                case 1: {
                    System.out.print("Enter first number: ");
                    double a = scanner.nextDouble();
                    System.out.print("Enter operator (+, -, *, /): ");
                    String op = scanner.next();
                    System.out.print("Enter second number: ");
                    double b = scanner.nextDouble();
                    double result;
                    switch (op) {
                        case "+": result = a + b; break;
                        case "-": result = a - b; break;
                        case "*": result = a * b; break;
                        case "/":
                            if (b == 0) { System.out.println("Error: Division by zero"); break; }
                            result = a / b; break;
                        default: System.out.println("Unknown operator"); break;
                    }
                    if (op.equals("+") || op.equals("-") || op.equals("*")) {
                        System.out.println("Result: " + (op.equals("+") ? a+b : op.equals("-") ? a-b : a*b));
                    } else if (op.equals("/") && b != 0) {
                        System.out.println("Result: " + (a / b));
                    }
                    break;
                }

                // ── 2. Trig Functions ──────────────────────────────────────────
                case 2: {
                    System.out.println("1-sin  2-cos  3-tan  4-asin  5-acos  6-atan");
                    System.out.print("Choose function: ");
                    int fn = scanner.nextInt();
                    System.out.print("Enter value (degrees): ");
                    double val = scanner.nextDouble();
                    double rad = Math.toRadians(val);
                    double res = 0;
                    String fname = "";
                    switch (fn) {
                        case 1: res = Math.sin(rad);  fname = "sin";  break;
                        case 2: res = Math.cos(rad);  fname = "cos";  break;
                        case 3: res = Math.tan(rad);  fname = "tan";  break;
                        case 4: res = Math.toDegrees(Math.asin(val)); fname = "asin"; break;
                        case 5: res = Math.toDegrees(Math.acos(val)); fname = "acos"; break;
                        case 6: res = Math.toDegrees(Math.atan(val)); fname = "atan"; break;
                        default: System.out.println("Invalid"); continue;
                    }
                    System.out.println(fname + "(" + val + ") = " + res);
                    break;
                }

                // ── 3. Hyperbolic Functions ────────────────────────────────────
                case 3: {
                    System.out.println("1-sinh  2-cosh  3-tanh  4-asinh  5-acosh  6-atanh");
                    System.out.print("Choose function: ");
                    int fn = scanner.nextInt();
                    System.out.print("Enter value: ");
                    double val = scanner.nextDouble();
                    double res = 0;
                    String fname = "";
                    switch (fn) {
                        case 1: res = Math.sinh(val);  fname = "sinh";  break;
                        case 2: res = Math.cosh(val);  fname = "cosh";  break;
                        case 3: res = Math.tanh(val);  fname = "tanh";  break;
                        case 4: res = Math.log(val + Math.sqrt(val*val + 1)); fname = "asinh"; break;
                        case 5: res = Math.log(val + Math.sqrt(val*val - 1)); fname = "acosh"; break;
                        case 6: res = 0.5 * Math.log((1+val)/(1-val));       fname = "atanh"; break;
                        default: System.out.println("Invalid"); continue;
                    }
                    System.out.println(fname + "(" + val + ") = " + res);
                    break;
                }

                // ── 4. Log / Power / Root ──────────────────────────────────────
                case 4: {
                    System.out.println("1-sqrt  2-cbrt  3-ln  4-log10  5-x^y  6-e^x  7-10^x  8-n!");
                    System.out.print("Choose: ");
                    int fn = scanner.nextInt();
                    System.out.print("Enter value: ");
                    double val = scanner.nextDouble();
                    switch (fn) {
                        case 1: System.out.println("sqrt(" + val + ") = " + Math.sqrt(val)); break;
                        case 2: System.out.println("cbrt(" + val + ") = " + Math.cbrt(val)); break;
                        case 3: System.out.println("ln("   + val + ") = " + Math.log(val));  break;
                        case 4: System.out.println("log10("+ val + ") = " + Math.log10(val));break;
                        case 5:
                            System.out.print("Enter exponent y: ");
                            double y = scanner.nextDouble();
                            System.out.println(val + "^" + y + " = " + Math.pow(val, y));
                            break;
                        case 6: System.out.println("e^" + val + " = " + Math.exp(val));      break;
                        case 7: System.out.println("10^" + val + " = " + Math.pow(10, val)); break;
                        case 8:
                            System.out.println((int)val + "! = " + factorial((int)val));
                            break;
                        default: System.out.println("Invalid");
                    }
                    break;
                }

                // ── 5. Permutation & Combination ───────────────────────────────
                case 5: {
                    System.out.print("Enter n: ");
                    int n = scanner.nextInt();
                    System.out.print("Enter r: ");
                    int r = scanner.nextInt();
                    System.out.println("P(" + n + "," + r + ") = " + permutation(n, r));
                    System.out.println("C(" + n + "," + r + ") = " + combination(n, r));
                    break;
                }

                // ── 6. Statistics ──────────────────────────────────────────────
                case 6: {
                    System.out.print("How many numbers? ");
                    int count = scanner.nextInt();
                    double[] nums = new double[count];
                    for (int i = 0; i < count; i++) {
                        System.out.print("Number " + (i+1) + ": ");
                        nums[i] = scanner.nextDouble();
                    }
                    statistics(nums);
                    break;
                }

                // ── 7. Matrix Operations ───────────────────────────────────────
                case 7: {
                    System.out.println("1-Add  2-Subtract  3-Multiply  4-Determinant of A");
                    System.out.print("Choose: ");
                    int op = scanner.nextInt();
                    double[][] A = readMatrix("A");
                    double[][] result = new double[2][2];

                    if (op == 4) {
                        System.out.println("det(A) = " + matDet2(A));
                        break;
                    }

                    double[][] B = readMatrix("B");

                    switch (op) {
                        case 1:
                            for (int i=0;i<2;i++) for(int j=0;j<2;j++) result[i][j]=A[i][j]+B[i][j];
                            System.out.println("A + B ="); printMatrix(result); break;
                        case 2:
                            for (int i=0;i<2;i++) for(int j=0;j<2;j++) result[i][j]=A[i][j]-B[i][j];
                            System.out.println("A - B ="); printMatrix(result); break;
                        case 3:
                            result = matMul(A, B);
                            System.out.println("A × B ="); printMatrix(result); break;
                        default: System.out.println("Invalid");
                    }
                    break;
                }

                case 0:
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
          }
          
