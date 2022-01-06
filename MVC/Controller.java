package com.library.MVC;

import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public abstract class Controller {
    private Model model;
    private View view;




    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void updateView(){
        Class<?> model = this.model.getClass();
        HashMap<String,Object> data = new HashMap<>();
        Iterator<String> fields = Arrays.stream(this.model.getProperties()).iterator();
        try {
            while (fields.hasNext()) {
                String field = fields.next();
                String nameOfMethod = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
                System.out.print(field + "=" + model.getMethod(nameOfMethod).invoke(this.model)+" ");
                data.put(field, model.getMethod(nameOfMethod).invoke(this.model));
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public Model getModel() {
        return model;
    }

    public View getView() {
        return view;
    }

}
