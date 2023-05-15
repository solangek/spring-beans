package hac.springbeans.beans;

import org.springframework.stereotype.Component;

@Component(value="autowiredLabelDependency")
public class Label {
    private String label = "Arbitrary Label";
    public Label() {
    }

    public Label(String l) {
        label = l;
    }
    public String toString() {
        return label;
    }
    public void setLabel(String l) {
        label = l;
    }

}