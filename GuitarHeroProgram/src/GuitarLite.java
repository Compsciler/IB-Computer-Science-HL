// This is a sample class that implements the Guitar interface.  It is not well
// documented.

public class GuitarLite implements Guitar {  /// Updated to include all notes
    private int totalNotes = 37;
    private GuitarString[] strings = new GuitarString[totalNotes];
    // private GuitarString stringA;
    // private GuitarString stringB;
    // private GuitarString stringC;

    // create two guitar strings, for concert A and C
    public GuitarLite() {
        // double[] frequencies = new double[totalNotes];
        double A2 = 110.0;
        // frequencies[0] = A2;
        for (int i = 0; i < totalNotes; i++){
            double frequency = A2 * Math.pow(2, i/12.0);
            // frequencies[i] = frequency;
            strings[i] = new GuitarString(frequency);
        }
    }

    public void playNote(int pitch) {
        strings[pitch].pluck();
    }

    public boolean hasString(char string) {
        return (string == '2' || string == '4' || string == '5' || string == '7' ||
                string == '8' || string == '9' || string == '-' || string == '=' ||
                string == 'q' || string == 'w' || string == 'e' || string == 'r' ||
                string == 't' || string == 'y' || string == 'u' || string == 'i' ||
                string == 'o' || string == 'p' || string == '[' || string == 'd' ||
                string == 'f' || string == 'g' || string == 'j' || string == 'k' ||
                string == ';' || string == '\'' || string == 'z' || string == 'x' ||
                string == 'c' || string == 'v' || string == 'b' || string == 'n' ||
                string == 'm' || string == ',' || string == '.' || string == '/' ||
                string == ' ');
    }
    
    public void pluck(char string) {
        switch (string){
            case 'q':
                strings[0].pluck();
                break;
            case '2':
                strings[1].pluck();
                break;
            case 'w':
                strings[2].pluck();
                break;
            case 'e':
                strings[3].pluck();
                break;
            case '4':
                strings[4].pluck();
                break;
            case 'r':
                strings[5].pluck();
                break;
            case '5':
                strings[6].pluck();
                break;
            case 't':
                strings[7].pluck();
                break;
            case 'y':
                strings[8].pluck();
                break;
            case '7':
                strings[9].pluck();
                break;
            case 'u':
                strings[10].pluck();
                break;
            case '8':
                strings[11].pluck();
                break;
            case 'i':
                strings[12].pluck();
                break;
            case '9':
                strings[13].pluck();
                break;
            case 'o':
                strings[14].pluck();
                break;
            case 'p':
                strings[15].pluck();
                break;
            case '-':
                strings[16].pluck();
                break;
            case '[':
                strings[17].pluck();
                break;
            case '=':
                strings[18].pluck();
                break;
            case 'z':
                strings[19].pluck();
                break;
            case 'x':
                strings[20].pluck();
                break;
            case 'd':
                strings[21].pluck();
                break;
            case 'c':
                strings[22].pluck();
                break;
            case 'f':
                strings[23].pluck();
                break;
            case 'v':
                strings[24].pluck();
                break;
            case 'g':
                strings[25].pluck();
                break;
            case 'b':
                strings[26].pluck();
                break;
            case 'n':
                strings[27].pluck();
                break;
            case 'j':
                strings[28].pluck();
                break;
            case 'm':
                strings[29].pluck();
                break;
            case 'k':
                strings[30].pluck();
                break;
            case ',':
                strings[31].pluck();
                break;
            case '.':
                strings[32].pluck();
                break;
            case ';':
                strings[33].pluck();
                break;
            case '/':
                strings[34].pluck();
                break;
            case '\'':
                strings[35].pluck();
                break;
            case ' ':
                strings[36].pluck();
        }
        /*
        if (string == 'a') {
            strings[12].pluck();
        } else if (string == 'c') {
            strings[15].pluck();
        }
        */
    }

    public double sample() {
        double res = 0;
        for (GuitarString string: strings){
            res += string.sample();
        }
        return res;
    }

    public void tic() {
        for (GuitarString string: strings){
            string.tic();
        }
    }

    public int time() {
        return -1;  // not implemented
    }
}
