/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Deneir Uy
 */
public class DFA {

    static PrintWriter writer;
    static ArrayList<String> invalids;
    static ArrayList<String> validEnds;

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        DFA dfa = new DFA();
        invalids = new ArrayList<>();
        validEnds = new ArrayList<>();
        Scanner scan = new Scanner(new File("mp2.in"));
        writer = new PrintWriter("mp2.out", "UTF-8");

        dfa.setInvalids();
        dfa.setValidEnds();

        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            int status = 0;
            String wBank = "LRCM";
            String eBank = "";
            for (int i = 0; i < currentLine.length(); i++) {
                char currentChar = currentLine.charAt(i);

                switch (currentChar) {
                    case 'L':
                        if (wBank.contains("L") && wBank.contains("M")) {
                            wBank = wBank.replace("L", "");
                            wBank = wBank.replace("M", "");
                            eBank += "LM";
                            if (dfa.checkIfInvalid(eBank)) {
                                status = 1;
                            }
                            if (dfa.checkIfInvalid(wBank)) {
                                status = 1;
                            }
                        } else if (eBank.contains("L") && eBank.contains("M")) {
                            eBank = eBank.replace("L", "");
                            eBank = eBank.replace("M", "");
                            wBank += "LM";
                            if (dfa.checkIfInvalid(eBank)) {
                                status = 1;
                            }
                            if (dfa.checkIfInvalid(wBank)) {
                                status = 1;
                            }
                        } else {
                            status = 1;
                        }
                        break;
                    case 'R':
                        if (wBank.contains("R") && wBank.contains("M")) {
                            wBank = wBank.replace("R", "");
                            wBank = wBank.replace("M", "");
                            eBank += "RM";
                            if (dfa.checkIfInvalid(eBank)) {
                                status = 1;
                            }
                            if (dfa.checkIfInvalid(wBank)) {
                                status = 1;
                            }
                        } else if (eBank.contains("R") && eBank.contains("M")) {
                            eBank = eBank.replace("R", "");
                            eBank = eBank.replace("M", "");
                            wBank += "RM";
                            if (dfa.checkIfInvalid(eBank)) {
                                status = 1;
                            }
                            if (dfa.checkIfInvalid(wBank)) {
                                status = 1;
                            }
                        } else {
                            status = 1;
                        }
                        break;
                    case 'C':
                        if (wBank.contains("C") && wBank.contains("M")) {
                            wBank = wBank.replace("C", "");
                            wBank = wBank.replace("M", "");
                            eBank += "CM";
                            if (dfa.checkIfInvalid(eBank)) {
                                status = 1;
                            }
                            if (dfa.checkIfInvalid(wBank)) {
                                status = 1;
                            }
                        } else if (eBank.contains("C") && eBank.contains("M")) {
                            eBank = eBank.replace("C", "");
                            eBank = eBank.replace("M", "");
                            wBank += "CM";
                            if (dfa.checkIfInvalid(eBank)) {
                                status = 1;
                            }
                            if (dfa.checkIfInvalid(wBank)) {
                                status = 1;
                            }
                        } else {
                            status = 1;
                        }
                        break;
                    case 'N':
                        if (wBank.contains("M")) {
                            wBank = wBank.replace("M", "");
                            eBank += "M";
                            if (dfa.checkIfInvalid(eBank)) {
                                status = 1;
                            }
                            if (dfa.checkIfInvalid(wBank)) {
                                status = 1;
                            }
                        } else {
                            eBank = eBank.replace("M", "");
                            wBank += "M";
                            if (dfa.checkIfInvalid(eBank)) {
                                status = 1;
                            }
                            if (dfa.checkIfInvalid(wBank)) {
                                status = 1;
                            }
                        }
                    default:
                        break;
                }

                if (status == 1) {
                    writer.println("NG");
                    break;
                }

            }
            if (status == 0) {
                if (dfa.checkIfValidEnd(eBank)) {
                    writer.println("OK");
                }
                else{
                    writer.println("NG");
                }
            }
        }
        writer.close();
    }

    public void setInvalids() {
        invalids.add("LR");
        invalids.add("RL");
        invalids.add("RC");
        invalids.add("CR");
        invalids.add("LRC");
        invalids.add("LCR");
        invalids.add("RCL");
        invalids.add("RLC");
        invalids.add("CLR");
        invalids.add("CRL");
    }

    public void setValidEnds() {
        validEnds.add("CLMR");
        validEnds.add("CLRM");
        validEnds.add("CMLR");
        validEnds.add("CMRL");
        validEnds.add("CRLM");
        validEnds.add("CRML");
        validEnds.add("LCMR");
        validEnds.add("LCRM");
        validEnds.add("LMCR");
        validEnds.add("LMRC");
        validEnds.add("LRCM");
        validEnds.add("LRMC");
        validEnds.add("MCLR");
        validEnds.add("MCRL");
        validEnds.add("MLCR");
        validEnds.add("MLRC");
        validEnds.add("MRCL");
        validEnds.add("MRLC");
        validEnds.add("RCLM");
        validEnds.add("RCML");
        validEnds.add("RLCM");
        validEnds.add("RLMC");
        validEnds.add("RMCL");
        validEnds.add("RMLC");

    }

    public boolean checkIfInvalid(String state) {
        for (int i = 0; i < invalids.size(); i++) {
            if (state.equals(invalids.get(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfValidEnd(String state) {
        for (int i = 0; i < validEnds.size(); i++) {
            if (state.equals(validEnds.get(i))) {
                return true;
            }
        }
        return false;
    }
}
