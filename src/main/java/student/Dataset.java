package student;

import java.io.File;
import java.util.*;
import java.io.*;
import javax.sound.sampled.*;


public abstract class Dataset {
    protected List<Row> data;
    protected Random rand;

    public void addEach(String path, boolean back) {
        File[] files = new File(path).listFiles();
        for (File file : files) {
            int index = 0;
            if (back) {
                index = data.size();
            }
            data.add(index, (new Row(file)));
        }
    }

    public void addEachToFront(String path) {
        addEach(path, false);
    }

    public void addEachToBack(String path) {
        addEach(path, true);
    }

    public Row getRandomRow() {
        if (rand == null) {
            rand = new Random();
        }
        int index = rand.nextInt(data.size());
        return data.get(index);
    }

    public void sortDataset() {
        Collections.sort(data);
    }


    public static class Row implements Comparable<Row> {

        protected File audioFile;
        protected String catID;
        protected int recordingSessionVocalCounter;

        protected Row(File file) {
            audioFile = file;
            String[] splittedFileName = file.getName().split("_");
            catID = splittedFileName[1];
            String sessionAsString = splittedFileName[splittedFileName.length - 1].replace(".wav", "");
            recordingSessionVocalCounter = Integer.parseInt(sessionAsString);
        }

        public int compareTo(Row other) {
            if (catID.equals(other.catID)) {
                return recordingSessionVocalCounter - other.recordingSessionVocalCounter;
            } else {
                return catID.compareTo(other.catID);
            }
        }

        public void play() {
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            try {
                stream = AudioSystem.getAudioInputStream(audioFile);
                format = stream.getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(stream);
                clip.start();
            } catch (Exception e) {
                System.out.printf("Could not play %s\n", audioFile);
            }
        }
    }
}
