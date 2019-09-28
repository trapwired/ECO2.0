package ch.tamidosa.eco20;

import android.media.Image;

public class ChildInfo {

    private String sequence = "";
    private String name = "";
    private String source = "";

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSource(String source){ this.source = source; }
    public String getSource(){return this.source;}
}
