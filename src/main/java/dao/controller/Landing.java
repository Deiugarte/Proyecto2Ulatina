package dao.controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Landing {

    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();
        for (int i = 1; i <= 6; i++) {
            images.add("nature" + i + ".jpg");
        }
        System.out.println(images);
    }

    public List<String> getImages() {
        return images;
    }
}
